package org.zjs.util;
import java.io.IOException;
import java.util.Calendar;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
 
public class ChatbotSend {
	private static Logger logger = LoggerFactory.getLogger(ChatbotSend.class);
    //public static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=d1244e6c6326d4a367ec29d6c2864c0d6f73d862599f148767dbf606b95291c7";
    
    
    
    public static void send(String fileURL, String RobeId) throws ClientProtocolException, IOException {
    	logger.info("fileURL : " + fileURL);
    	String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=" + RobeId;
    	System.out.println("WEBHOOK_TOKEN :" + WEBHOOK_TOKEN);
    	int[] hms = getDate();
    	
    	HttpClient httpclient = HttpClients.createDefault();
         HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
         httppost.addHeader("Content-Type", "application/json; charset=utf-8");
         String textMsg = "{ \"msgtype\": \"markdown\","
         		+ "\"markdown\": {\"title\":\"数据信息\",\r\n \"text\":\"#### 数据分析  \\n >![screenshot]("+fileURL+")\\n  > ###### "+hms[0]+"点"+hms[1]+"分"+hms[2]+"秒,大数据组发布  \"\r\n},}";
         StringEntity se = new StringEntity(textMsg, "utf-8");
         httppost.setEntity(se);
  
         HttpResponse response = httpclient.execute(httppost);
         if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
             String result= EntityUtils.toString(response.getEntity(), "utf-8");
             System.out.println(result);
         }
    }
    
    public static int[] getDate() {
    	Calendar calendar = Calendar.getInstance();
    	int hour = calendar.get(Calendar.HOUR_OF_DAY);
    	int minute = calendar.get(Calendar.MINUTE);
    	int second = calendar.get(Calendar.SECOND);
    	int[] hms = new int[] {hour,minute,second};
    	return hms;
    	
    }
}