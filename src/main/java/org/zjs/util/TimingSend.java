package org.zjs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zjs.bean.ImageBean;
import org.zjs.pojo.Task;

public class TimingSend {
	
	//是否去关闭定时器
	public static boolean toClose = false;
	private static final long daySpan = 7*24*60*60*1000;
	private static HashMap<String,Timer> TimerMap = new  HashMap<String, Timer>();
	private static Logger logger = LoggerFactory.getLogger(TimingSend.class);
	public static void send(ImageBean imageBean, String phantomjs_path, String phantomjs_js_path, String tt, Task task,List<String> robeIdList) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd "+tt+":00");
		
		//截图并保存图片到本地
		
		//如果这个任务的hashcode已经存在map中说明任务列表中已存在这个任务，直接返回不执行定时任务操作
		if(TimerMap.get(task.toString()) != null) {
			logger.info(task.toString()+"已经在执行中，所以没有加入任务进程");
			return ;
		}
		//设置定时器
		Timer timer = new Timer();
		TimerMap.put(task.toString(), timer);
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				try {
					System.out.println("此处为任务的run方法里面");
					imageBean.updateFileRealName();
					PhantomjsDemo pd = new PhantomjsDemo(imageBean.getRequestURL(), imageBean.getFileRealPath(), phantomjs_path, phantomjs_js_path);
					pd.generateImage();
					System.out.println("发送钉钉辣！");
					
					for(String robeId: robeIdList) {
						ChatbotSend.send(imageBean.getFileURL(),robeId);
					}
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
			
		};
		
		//此段代码为每天早上八点发送钉钉
		/*Date startTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(sdf.format(new Date()));
		if(System.currentTimeMillis() > startTime.getTime())
			startTime = new Date(startTime.getTime() + daySpan);
		LoggerFactory.getLogger(TimingSend.class).info("startTime : " + startTime);
		timer.scheduleAtFixedRate(timerTask, startTime, daySpan);*/
		Date startTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(sdf.format(new Date()));
		startTime = getThisWeekMonday(startTime);
		if(System.currentTimeMillis() > startTime.getTime())
			startTime = new Date(startTime.getTime() + daySpan);
		LoggerFactory.getLogger(TimingSend.class).info("startTime : " + startTime);
		timer.scheduleAtFixedRate(timerTask, startTime, daySpan);
		/*Long delay = (long) 0;
		Long intevalPeriod = (long) (20*1000);
		System.out.println("此处为定时器之前");
		timer.scheduleAtFixedRate(task, delay, inrevalPeriod);*/
	}
	
	public static void closeAllTimer(List<Task> taskList) {
		int count = 0;
		for(Task task : taskList) {
			Timer timer = TimerMap.get(task.toString());
			try {
				timer.cancel();
			}catch(NullPointerException e) {
				LoggerFactory.getLogger(TimingSend.class).info(task.toString()+"这个任务运行状态中！");
			}
			TimerMap.remove(task.toString());
			count++ ;
		}
		LoggerFactory.getLogger(TimingSend.class).info("执行选择关闭，一共传入"+count+"个任务");
		System.gc();
	}

	
	public static HashMap<String, Timer> getTimerMap() {
		return TimerMap;
	}

	public static void setTimerMap(HashMap<String, Timer> timerMap) {
		TimerMap = timerMap;
	}
	
	public static Date getThisWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}
	

	
}
