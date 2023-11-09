package com.book.client.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.member.dto.MemberDTO;
import com.book.member.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ClientController {
	@Autowired
	MemberService memberService;
	
//	@GetMapping("/ClientCenter") //고객센터
//	public String ClientCenter(HttpServletRequest request, HttpServletResponse response, MemberDTO mdto, Model model) {
//		String page = null;
//		
//		page = "Main";
//		
//		model.addAttribute("page", page);
//		model.addAttribute("contentsJsp", "custom/ClientCenter");
//		return page;
//	}
	
	@RequestMapping("/AdminClientCenter") //관리자 고객센터
	public String AdminClientCenter(HttpServletRequest request, HttpServletResponse response, MemberDTO mdto, Model model) {
		String page = null;
		
		page = "Main";
		
		model.addAttribute("page", page);
		model.addAttribute("contentsJsp", "admin/ClientCenter");
		return page;
	}
}
