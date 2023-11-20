package com.book.chat.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.book.chat.dto.ChatDTO;
import com.book.chat.service.ChatService;
import com.book.member.dto.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
public class ChatController {
	
	@Autowired
	ChatService chatService;
	
	@RequestMapping("/mychatt")
	public ModelAndView chatt(HttpServletRequest request, HttpServletResponse response, ChatDTO chdto, Model model) {
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
	
	
	 @RequestMapping("/createChat") 
	 public int createChat(HttpServletRequest request, HttpServletResponse response, ChatDTO chdto) {
	 return chatService.createChat(chdto); 
	 }
	 
	 @RequestMapping("/deleteChat") 
	 public int deleteChat(HttpServletRequest request, HttpServletResponse response, ChatDTO chdto) {
		 return chatService.deleteChat(chdto); 
	 }
	 
	 @RequestMapping("/deleteAdminChat") 
	 public int deleteAdminChat(HttpServletRequest request, HttpServletResponse response, ChatDTO chdto) {
		 return chatService.deleteAdminChat(chdto); 
	 }
	 
	 @ResponseBody
	 @RequestMapping("/getChat") 
	 public List<Map<String, Object>> getChat(HttpServletRequest request, HttpServletResponse response, ChatDTO chdto) {
		 return chatService.getChat(chdto); 
	 }
	 
	 @ResponseBody
	 @RequestMapping("/getAdminChat") 
	 public List<Map<String, Object>> getAdminChat(HttpServletRequest request, HttpServletResponse response, ChatDTO chdto) {
		 return chatService.getAdminChat(chdto); 
	 }
	 
}