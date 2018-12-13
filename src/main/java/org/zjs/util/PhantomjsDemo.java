package org.zjs.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zjs.exception.PhantomjsTimeoutException;



public class PhantomjsDemo {
	
	private String url;
	private String target;
	private String phantomjs_path;
	private String phantomjs_js_path;
	private Logger logger = LoggerFactory.getLogger(PhantomjsDemo.class);
	
	
	public PhantomjsDemo(String url, String target, String phantomjs_path, String phantomjs_js_path) {
		this.url = url;
		this.target = target;
		this.phantomjs_path = phantomjs_path;
		this.phantomjs_js_path = phantomjs_js_path;
	}
	
	
	
	public void generateImage() throws IOException, PhantomjsTimeoutException {
		String BLANK = "  ";
		logger.info("访问url地址为：" + url);
		logger.info("图片储存路径为：" +target);
		logger.info("phantomjs.exe路径为：" + phantomjs_path);
		logger.info("phantomjs.js路径为：" + phantomjs_js_path);
		
		//String phantomJS = webappRoot +"src/main/webapp/js/phantomJS.js";
		String phantomJS = phantomjs_js_path;
		
		Process process = Runtime.getRuntime().exec(
        		phantomjs_path + BLANK //你的phantomjs.exe路径
                + phantomJS + BLANK //就是上文中那段javascript脚本的存放路径
                + url + BLANK //你的目标url地址
                + target);//你的图片输出路径
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String tmp = "";
        int count = 0;
        while ((tmp = reader.readLine()) != null) {
        	System.out.println("截图进行了"+ (count++) +"秒");
            try {
            	System.out.println(tmp);
				Thread.sleep(1000);
				if(count > 90) {
					throw new PhantomjsTimeoutException();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        process.destroy();
        System.out.println("process已经销毁");
        reader.close();
        System.out.println("reader 已经关闭");
    }




	
	
}

