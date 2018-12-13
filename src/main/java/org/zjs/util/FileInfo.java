package org.zjs.util;

import org.springframework.beans.factory.annotation.Value;

public class FileInfo {
	@Value("${File_last_Name}")
	private String lastName;
	@Value("File_Save_Path")
	private String fileSavePath;
	@Value("Image_Server_URL")
	private String URL;
	
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFileSavePath() {
		return fileSavePath;
	}
	public void setFileSavePath(String fileSavePath) {
		this.fileSavePath = fileSavePath;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
}
