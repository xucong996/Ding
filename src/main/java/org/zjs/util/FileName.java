package org.zjs.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FileName {

	public static String getRealName(String lastName) {
		// TODO Auto-generated method stub
		/*String randomUUID = UUID.randomUUID().toString();
		StringBuffer realName = new StringBuffer();
		realName.append(randomUUID).append(lastName);*/
		
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		UUID uuid = UUID.randomUUID();
		String date = simpleDateFormat.format(new Date());
		StringBuffer realName = new StringBuffer();
		realName.append(date).append("&"+uuid.toString()).append(lastName);
		return realName.toString();
	}

}
