package com.neu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.beans.Lesson;
import com.neu.service.BackGetLessonOrderService;

@Controller
public class BackGetLessonOrderHandler {
	@Autowired
	private BackGetLessonOrderService backGetLessonOrderService;
	
	@RequestMapping(value = "/back/getLessonOrder")
	@ResponseBody
	public List<Lesson> getLessonOrder(@RequestParam int currectPage, 
									   @RequestParam String orderKey,
									   @RequestParam String orderStatus,
									   @RequestParam String orderStartTime,
									   @RequestParam String orderEndTime) throws Exception{
		System.out.println("ceshi");
		int pageStart = (currectPage - 1) * 20;
		int pageNumber = 20;
		return backGetLessonOrderService.getLessonOrder(pageStart, pageNumber, orderKey, orderStatus, orderStartTime, orderEndTime);
	}
	
	@RequestMapping(value = "/back/getLessonNumber")
	@ResponseBody
	public int getLessonNumber() throws Exception{
		int allPageNumber = backGetLessonOrderService.getLessonNumber();
		if(allPageNumber % 20 == 0) {
			return allPageNumber / 20;
		}else {
			return (allPageNumber / 20) + 1;
		}
	}
	
	@RequestMapping(value = "/back/set")
	@ResponseBody
	public void set(@RequestParam int order_id, @RequestParam int s_status) throws Exception{
		System.out.println(order_id);
		String set_status = "";
		if(s_status == 1) {
			set_status = "已退款";
		}else if(s_status == 2) {
			set_status = "已核销";
		}else {
			System.out.println(s_status);
		}
		backGetLessonOrderService.set(order_id, set_status);
	}
	
}
