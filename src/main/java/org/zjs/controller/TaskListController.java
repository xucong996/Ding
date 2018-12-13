package org.zjs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zjs.bean.ImageBean;
import org.zjs.exception.PhantomjsTimeoutException;
import org.zjs.pojo.Task;
import org.zjs.service.CpRobeService;
import org.zjs.service.PageService;
import org.zjs.service.TaskListService;
import org.zjs.util.ChatbotSend;
import org.zjs.util.PhantomjsDemo;

@RestController
@PropertySource("classpath:imagePath.properties")
public class TaskListController {
	
	@Autowired
	TaskListService taskListService;
	@Autowired
	private CpRobeService cpRobeService;
	@Autowired
	private PageService pageService;
	private Logger logger = LoggerFactory.getLogger(TaskListController.class);
	
	@Value("${File_last_Name}")
	public String lastName;
	@Value("${File_Save_Path}")
	public String fileSavePath;
	@Value("${File_Image_URL}")
	public String URL;
	@Value("${Phantomjs_path}")
	public String phantomjs_path;
	@Value("${Phantomjs_js_path}")
	public String phantomjs_js_path;
	
	@RequestMapping("/properties")
	public String tt(@PathVariable String isTest) {
		logger.info("lastName:" + lastName + ", fileSavePath:" + fileSavePath + ",URL:" + URL + ", phantomjs_path:" + phantomjs_path + ", phantomjs_js_path" + phantomjs_js_path);
		return "123";
	}
	
	@RequestMapping("/accept")
	public String accept(@RequestBody List<Task> rows) {
		logger.info(rows.toString());
		String result = taskListService.saveTask(rows);
		return result;
	}
	
	@RequestMapping("/getAll")
	public List<Task> getAllTask(){
		List<Task> list = taskListService.getAllTask();
		
		return list;
	}
	
	@RequestMapping("/send")
	public String send(@RequestBody List<Task> list) {
		LoggerFactory.getLogger(TaskListController.class).info("进入send()");
		for(Task task : list) {	
			
			String time = task.getTime();
			String requestURL = pageService.findOneByPageName(task.getPage()).getUrl();
			logger.info("请求网址为："+task.getPage()+",url :" + requestURL);
			
			//将filesavePath 和 URL传入imageBean ， 在获取fileRealPath 和 fileURL 更新一下imageBean中的FileRealName之后拼接 返回。
			
	        ImageBean imageBean = new ImageBean(requestURL,fileSavePath,URL,lastName);
			String[] strings = task.getCpName().split(",");
					List<String> robeIdList = new ArrayList<String>();
					for(int i=0; i<strings.length ; i++) {
						String robeId = cpRobeService.findOneByName(strings[i]).getRobeId();
						logger.info("发送名单中robeId为：" + robeId);
						robeIdList.add(robeId);
					}
					
					logger.info("进行定时发送发送");
					//time:指定发送时间，task：用来映射定时器任务，robeIdList：需要发送的机器人ID集合
					//TimingSend.send(imageBean, phantomjs_path, phantomjs_js_path,time,task,robeIdList);
					DynamicTask.startTest(imageBean, phantomjs_path, phantomjs_js_path,time,task,robeIdList);
				
			}
		return "success";
	}
	@RequestMapping("/test")
	public String test(@RequestBody List<Task> list) {
		for(Task task : list) {	
			String requestURL = pageService.findOneByPageName(task.getPage()).getUrl();
			logger.info("请求网址为："+task.getPage()+",url :" + requestURL);
			//将filesavePath 和 URL传入imageBean ， 在获取fileRealPath 和 fileURL 更新一下imageBean中的FileRealName之后拼接 返回。
	        ImageBean imageBean = new ImageBean(requestURL,fileSavePath,URL,lastName);
			String[] strings = task.getCpName().split(",");
			imageBean.updateFileRealName();
			PhantomjsDemo pd = new PhantomjsDemo(imageBean.getRequestURL(), imageBean.getFileRealPath(), phantomjs_path, phantomjs_js_path);
			
			
			try {
				pd.generateImage();
			} catch (IOException | PhantomjsTimeoutException e1) {
				// TODO Auto-generated catch block
				logger.info("生成图片时间超时，换一个试试吧");
			}
			
			
			for(int i=0; i<strings.length ; i++) {
				String robeId = cpRobeService.findOneByName(strings[i]).getRobeId();
				try {
					
					logger.info("生成图片方法已经返回");
					ChatbotSend.send(imageBean.getFileURL(),robeId);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				logger.info("发送到机器人：" + strings[i] + "robeId : " + robeId);
			}
		}
		return "测试发送成功";
	}
}
