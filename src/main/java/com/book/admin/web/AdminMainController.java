package com.book.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.member.dto.MemberDTO;
import com.book.member.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminMainController {
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/") 
	public String index(HttpServletRequest req, HttpServletResponse res, MemberDTO adto, Model adel) {
		HttpSession session = req.getSession();
		String page = null;
		MemberDTO ssKey = null;
		if(session.getAttribute("ssKey")!=null) {
			ssKey = (MemberDTO) session.getAttribute("ssKey");
			if(ssKey.getM_role().equals("admin"))
				page = "admin/index";
			else page = "redirect:/";
		}
		else {
			page = "redirect:/";
		}
		return page;
	}
	
}
