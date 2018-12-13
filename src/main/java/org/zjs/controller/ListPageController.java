package org.zjs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zjs.bean.EasyUIBean;
import org.zjs.pojo.CpRobe;
import org.zjs.pojo.Page;
import org.zjs.service.CpRobeService;
import org.zjs.service.PageService;

@RestController
public class ListPageController {
	
	@Autowired
	CpRobeService cpRobeService;
	@Autowired
	PageService pageService;
	
	@RequestMapping("/companyList")
	public List<EasyUIBean> getCompanyList() {
		
		List<CpRobe> list = cpRobeService.findAll();
		
		List<EasyUIBean> EUList = new ArrayList<EasyUIBean>();

		for(CpRobe a : list) {

			EUList.add(new EasyUIBean(a.getCompany(),a.getCompany()));
		}
		return EUList;
	}
	
	@RequestMapping("/robeList")
	public List<CpRobe> getcpRobeList() {
		List<CpRobe> list = cpRobeService.findAll();
		return list;
	}
	
	@RequestMapping("/pageList")
	public List<EasyUIBean> getPageList(HttpServletResponse response) {
		List<Page> list = pageService.findAll();
		List<EasyUIBean> EUList = new ArrayList<EasyUIBean>();
		

		for(Page a : list) {

			EUList.add(new EasyUIBean(a.getPage(),a.getPage()));
		}
		//response.setHeader("Access-Control-Allow-Origin", "*");
		return EUList;
	}
}
