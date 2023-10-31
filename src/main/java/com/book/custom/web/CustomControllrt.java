package com.book.custom.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.member.dto.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CustomControllrt {
	
	@RequestMapping("/")
	public String index(HttpServletRequest req) {
		MemberDTO ssKey = null;
		String page = null;
		HttpSession session = req.getSession();
		if(session.getAttribute("ssKey")!=null) {
			ssKey = (MemberDTO) session.getAttribute("ssKey");
			if(ssKey.getM_role().equals("admin"))
				page = "redirect:/admin/";
			else page = "index";
		}
		else {
			page = "index";
		}
		return page;
	}
}
