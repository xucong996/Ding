package org.zjs.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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
import org.zjs.util.TimingSend;

@RestController
@PropertySource("classpath:imagePath.properties")

public class TaskListController_day {
	
	@Autowired
	TaskListService taskListService;
	@Autowired
	private CpRobeService cpRobeService;
	@Autowired
	private PageService pageService;
	private Logger logger = LoggerFactory.getLogger(TaskListController_day.class);
	
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
	
	
	
	@RequestMapping("/send_day")
	public String send1(@RequestBody List<Task> list) {
		LoggerFactory.getLogger(TaskListController_day.class).info("进入send()");
		for(Task task : list) {	
			
			String time = task.getTime();
			String requestURL = pageService.findOneByPageName(task.getPage()).getUrl();
			logger.info("请求网址为："+task.getPage()+",url :" + requestURL);
			
			//将filesavePath 和 URL传入imageBean ， 在获取fileRealPath 和 fileURL 更新一下imageBean中的FileRealName之后拼接 返回。
			
	        ImageBean imageBean = new ImageBean(requestURL,fileSavePath,URL,lastName);
			String[] strings = task.getCpName().split(",");
					try {
						List<String> robeIdList = new ArrayList<String>();
						for(int i=0; i<strings.length ; i++) {
							String robeId = cpRobeService.findOneByName(strings[i]).getRobeId();
							logger.info("发送名单中robeId为：" + robeId);
							robeIdList.add(robeId);
						}
						
						logger.info("进行定时发送发送");
						//time:指定发送时间，task：用来映射定时器任务，robeIdList：需要发送的机器人ID集合
						TimingSend.send(imageBean, phantomjs_path, phantomjs_js_path,time,task,robeIdList);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		return "success";
	}
	@RequestMapping("/test_day")
	public String test1(@RequestBody List<Task> list) {
		for(Task task : list) {	
			String requestURL = pageService.findOneByPageName(task.getPage()).getUrl();
			logger.info("请求网址为："+task.getPage()+",url :" + requestURL);
			//将filesavePath 和 URL传入imageBean ， 在获取fileRealPath 和 fileURL 更新一下imageBean中的FileRealName之后拼接 返回。
	        ImageBean imageBean = new ImageBean(requestURL,fileSavePath,URL,lastName);
			String[] strings = task.getCpName().split(",");
			imageBean.updateFileRealName();
			PhantomjsDemo pd = new PhantomjsDemo(imageBean.getRequestURL(), imageBean.getFileRealPath(), phantomjs_path, phantomjs_js_path);
			for(int i=0; i<strings.length ; i++) {
				String robeId = cpRobeService.findOneByName(strings[i]).getRobeId();
				try {
					pd.generateImage();
					logger.info("生成图片方法已经返回");
					ChatbotSend.send(imageBean.getFileURL(),robeId);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (PhantomjsTimeoutException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				logger.info("发送到机器人：" + strings[i] + "robeId : " + robeId);
			}
		}
		return "测试发送成功";
	}
	
	@RequestMapping("/RunningTaskList")
	public Set<Task> runningTask(){
		Map<Task, ScheduledFuture<?>> futureMap = DynamicTask.getFutureMap();
		Set<Task> keySet = futureMap.keySet();
		return keySet;
	}
}
