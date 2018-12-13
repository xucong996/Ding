package org.zjs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zjs.service.CpRobeService;
import org.zjs.service.GroupSendService;
import org.zjs.service.PageService;

import com.mongodb.MongoWriteException;

@Controller
public class RegisterController {
	
	@Autowired
	private  CpRobeService cpRobeService;
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	private GroupSendService groupSendService;
	
	@RequestMapping("/companyRegister")
	@ResponseBody
	public String companyRegister(@RequestParam String company, @RequestParam String robeId) {
		String msg;
		try {
			msg = cpRobeService.insertOne(company, robeId);
		}catch(MongoWriteException e) {
			return "注册失败！ 这个公司名已经被注册啦，请删除后再注册";
		}
		return msg;
	}
	
	@RequestMapping("/companyCancellation")
	@ResponseBody
	public String companyCancellation(@RequestParam String company) {
		String msg;
		
		msg = cpRobeService.deleteOne(company);
		
		return msg;
	}
	
	
	@RequestMapping("pageRegister")
	@ResponseBody
	public String pageRegister(@RequestParam String page, @RequestParam String url) {
		String msg;
		try {
			
			msg = pageService.insertOne(page, url);
		}catch(MongoWriteException e) {
			return "注册失败！ 这个网页已经被注册啦，请删除后再注册";
		}
		return msg;
	}
	
	@RequestMapping("pageCancellation")
	@ResponseBody
	public String pageCancellation(@RequestParam String page) {
		String msg;
		
		msg = pageService.deleteOne(page);
		
		return msg;
	}
	
	
	@RequestMapping("/addGroupSend")
	@ResponseBody
	public String addGroupSend(String company, String page) {
		
		String msg;
		
		String robeId = cpRobeService.findOneByName(company).getRobeId();
		String url = pageService.findOneByPageName(page).getUrl();
		try {
			msg = groupSendService.registerOne(company, robeId, url);
		}catch(MongoWriteException e) {
			return "这个公司已经在群发列表中啦！";
		}
		return msg + page +"页面截图";
	}
	
	
	
}
