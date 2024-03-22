package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

//@Controller
@RestController
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
		
	//리스트
	//@ResponseBody
	@RequestMapping(value = "/api/guests", method=RequestMethod.GET)
	public List<GuestbookVo> list() {
		System.out.println("GuestbookController.list()");
		
		List<GuestbookVo> guestbookList = guestbookService.exeGuestList();
		
		System.out.println(guestbookList);
		return guestbookList;

	}
	
	//저장+1개가져오기
	//@ResponseBody
	@RequestMapping(value = "/api/guests", method=RequestMethod.POST)
	public String add(@RequestBody GuestbookVo guestbookVo) {
		System.out.println("GuestbookController.add()");
		System.out.println(guestbookVo);
		
		GuestbookVo guestVo =guestbookService.exeAddandGuest(guestbookVo);
		System.out.println(guestVo);
		return "";
	}
	
	//삭제
	//@ResponseBody
	@DeleteMapping(value = "/api/guests/{no}")
	public String remove(@RequestBody GuestbookVo guestbookVo,
						 @PathVariable(value="no")int no) {
		System.out.println("GuestbookController.remove()");
		
		guestbookVo.setNo(no);
		System.out.println(guestbookVo);
		
		int count = guestbookService.exeRemove(guestbookVo);
		
		String result = "{\"count\":"+count+"}";
		
		return result;
		
	}


	

}