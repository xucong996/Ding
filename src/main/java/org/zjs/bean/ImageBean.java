package org.zjs.bean;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zjs.util.FileName;

public class ImageBean {
	
	//因为requestURL和FileRealPath 都依赖于FileRealName; 
	//之后是同一个FileRealName的时候，浏览器才能顺利浏览图片。

	//每次get  requestURL和fileRealPath都用该从新设置一下fileRealName,确保每次生成图图片的时候名字不相同
	private String requestURL;
	private String lastName;
	private String fileSavePath;
	private String url;
	
	private String fileRealName = null;
	private Logger logger = LoggerFactory.getLogger(ImageBean.class);
	//更新文件名
	public void updateFileRealName() {
		fileRealName = FileName.getRealName(lastName);
		logger.info("fileRealName:" + fileRealName);
	}
	//获得文件真实路径
	public String getFileRealPath() {
		String fileRealPath  = fileSavePath+fileRealName;
		logger.info("fileRealPath:" + fileRealPath);
		return fileRealPath;
	}
	//获得访问图片url地址
	public String getFileURL() {
		String fileURL = url+fileRealName;
		logger.info("fileURL" + fileURL);
		return fileURL;
	}
	
	
	
	public ImageBean(String requestURL, String fileSavePath, String url,String lastName) {
		super();
		this.requestURL = requestURL;
		this.fileSavePath = fileSavePath;
		this.url = url;
		this.lastName = lastName;
	}
	
	
	
	
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRequestURL() {
		return requestURL;
	}
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	public String getFileSavePath() {
		return fileSavePath;
	}
	public void setFileSavePath(String fileSavePath) {
		this.fileSavePath = fileSavePath;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
