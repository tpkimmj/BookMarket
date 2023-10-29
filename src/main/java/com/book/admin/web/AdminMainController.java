package com.book.admin.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping("/memberMgt") //회원전체 목록 보기
	public String memberMgt(HttpServletRequest req, HttpServletResponse res, MemberDTO mdto, Model model, PageDTO pageDto) {
		MemberDTO ssKey = null;
		String page = null;
		HttpSession session = req.getSession();
		if(session.getAttribute("ssKey")!=null) {
			ssKey = (MemberDTO) session.getAttribute("ssKey");
			if(ssKey.getM_role().equals("admin"))
			page = "admin/Main";
			else page = "redirect:/";
		}
		else {
			page = "redirect:/";
		}
		Map<String, Object> reSet = memberService.getMembers(mdto, pageDto);
		model.addAttribute("memberTot", reSet.get("memberTot"));
		model.addAttribute("members", reSet.get("members"));
		model.addAttribute("PageDto", reSet.get("PageDto"));
		model.addAttribute("contentsJsp", "admin/MemList");
		return page;
	}
}
