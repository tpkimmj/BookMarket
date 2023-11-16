package com.book.chat.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.book.member.dto.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
public class ChatController {
	
	@RequestMapping("/mychatt")
	public ModelAndView chatt(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDTO mdto = (MemberDTO) session.getAttribute("ssKey");
		ModelAndView mv = new ModelAndView();
		if(mdto != null && mdto.getM_role().equals("admin")) {
			mv.setViewName("admin/AdminChat");
		} else {
			mv.setViewName("custom/UserChat");
		}
		session.setAttribute("ssKey", mdto); 
		return mv;
	}

}