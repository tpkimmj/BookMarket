package com.book.admin.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.client.service.ClientService;
import com.book.common.dto.PageDTO;
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
	
	@Autowired
	ClientService clientService;
	
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
//	@RequestMapping("/ClientCenter") //고객센터
//	public String ClientCenter(HttpServletRequest request,HttpServletResponse response, MemberDTO mdto, Model model) {
//		String page = null;
//		
//		page = "Main";
//		
//		model.addAttribute("page", page);
//		model.addAttribute("contentsJsp", "admin/ClientCenter");
//		return page;
//	}
	
	@GetMapping("/NoticeWriteForm") //공지사항 양식글
	public String NoticeWriteForm(HttpServletRequest request, HttpServletResponse response, MemberDTO mdto, Model model) {
		String page = null;
		
		page = "Main";
		model.addAttribute("page", page);
		model.addAttribute("contentsJsp", "admin/NoticeWriteForm");
		return page;
	}
	@GetMapping("/submit12") //등록
	public String submit12(HttpServletRequest request, HttpServletResponse response, MemberDTO mdto, Model model) {
		String page = null;
		
		page = "Main";
		model.addAttribute("page", page);
		model.addAttribute("contentsJsp", "admin/ClientCenter");
		return page;
	}
	
}
