package org.zjs.controller;

import java.util.List;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zjs.bean.TestBean;


@RestController
public class testController {
	

	
	
	
	
	
	@RequestMapping("/rows")
	public String testRows(@RequestBody List<TestBean> rows) throws JSONException{
		
		return "success";
	}
	
	
}
