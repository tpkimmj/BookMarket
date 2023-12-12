package com.book.client.web;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.client.dto.ClientDTO;
import com.book.client.service.ClientService;
import com.book.common.dto.PageDTO;
import com.book.member.dto.MemberDTO;
import com.book.member.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ClientController {
	@Autowired
	MemberService memberService;
	
	@Autowired
	ClientService clientService;
	
	private static final Logger logger= LoggerFactory.getLogger(ClientController.class);
	
	@RequestMapping("/NoticeWriteForm") //공지사항 양식글
	public String NoticeWriteForm(HttpServletRequest request, HttpServletResponse response, MemberDTO mdto, Model model) {
		String page = null;
		
		page = "Main";
		model.addAttribute("page", page);
		model.addAttribute("contentsJsp", "admin/NoticeWriteForm");
		return page;
	}	
	
	@RequestMapping("/ClientCenter") //공지사항 리스트
	 public String ClientCenter(HttpServletRequest request, 
			   HttpServletResponse response, 
			   Model model, PageDTO pageDto,
			   ClientDTO cdto) {
		
	    Map<String, Object> reSet = clientService.getClients(cdto);
	    HttpSession session = request.getSession();
	    String contentsJsp = null;
	    String page = null;
	    MemberDTO mdto = (MemberDTO) session.getAttribute("ssKey");
	    if(mdto!=null && mdto.getM_role().equals("admin")) {
	    	page = "admin/Main";
	    	contentsJsp = "/ClientCenter";
	    }else {//고객, 방문자
	        page = "Main";
	        contentsJsp = "custom/ClientCenter";
	    }
	    @SuppressWarnings("unchecked")
	    List<ClientDTO> cList = (List<ClientDTO>) reSet.get("cList"); 
	       
	    session.setAttribute("ssKey", mdto);
	    model.addAttribute("pageDto", reSet.get("pageDto"));
	    model.addAttribute("totCnt", reSet.get("totCnt"));
	    model.addAttribute("cList", cList);
	    model.addAttribute("contentsJsp", contentsJsp);
	    return page;
	}
	
	@RequestMapping("noticeProc")// 공지사항 등록
	public String noticeProc(HttpServletRequest request, HttpServletResponse response,
			ClientDTO cdto,Model model,
			MemberDTO mdto){
		String flag = request.getParameter("flag");
		HttpSession session = request.getSession();
		String contentsJsp = null;
		String page = null;
		Map<String, Object> reSet = clientService.getClients(cdto);
	    mdto = (MemberDTO) session.getAttribute("ssKey");
	    logger.info("====>"+cdto);
		String msg = null;
		String url = null;
	    if(mdto!=null && mdto.getM_role().equals("admin")){
			switch (flag) {
				case "insert":{
					clientService.generateClient(cdto);
					page = "redirect:/ClientCenter";
					break;
				}
	            case "update":{
					//수정용
					page = "MsgPage";
					logger.info("cdto==>"+cdto);
					int r = clientService.updateProc(cdto);
					if(r>0) msg = "수정이 완료되었습니다.";
					else msg = "수정을 실패했습니다."; 
					break;
	            }
	            case "delete":{
					//삭제용
					page = "MsgPage";
	                int r = clientService.deleteProc(cdto);
	                logger.info("cdto==>"+cdto);
	                if(r>0) msg = "삭제가 완료되었습니다.";
	                else msg = "삭제을 실패했습니다."; 
	                break;
	            }
			}
			url = "ClientCenter";
			if(url!=null) model.addAttribute("url", url);
			if(msg!=null) model.addAttribute("msg", msg);
	    	}else {
	    		page = "Main";
	    		contentsJsp = "./custom/ClientCenter";
	    	}
	    
	    session.setAttribute("ssKey", mdto);
	    model.addAttribute("cdto", cdto);
	    model.addAttribute("cList", reSet.get("ClientCenter"));
	    model.addAttribute("contentsJsp", contentsJsp);
		return page;
	}
	@RequestMapping("/noticeDetail") //게시된 공지사항 보기
	public String noticeDetail(HttpServletRequest request, HttpServletResponse response, 
			   ClientDTO cdto, Model model, PageDTO pageDto) {
		HttpSession session = request.getSession();
		String contentsJsp = null;
		String page = null;
		MemberDTO mdto = (MemberDTO) session.getAttribute("ssKey");
		if(mdto!=null && mdto.getM_role().equals("admin")) {
			ClientDTO notice = clientService.getClient(cdto);
			model.addAttribute("cdto", notice);
			page = "admin/Main";
			contentsJsp = "./Notice";
		}else {
			//고객용에서만 조회수가 증가
			clientService.getClients(cdto);
			
			ClientDTO dto = clientService.getClient(cdto);
			model.addAttribute("cdto", dto);
			page = "Main";
			contentsJsp = "./custom/Notice";
		}
		session.setAttribute("ssKey", mdto);
		model.addAttribute("contentsJsp", contentsJsp);
	      
		return page;
	}
	@RequestMapping("/noticeUpForm") //공지사항 수정
	public String noticeUpForm(HttpServletRequest request, HttpServletResponse response, ClientDTO cdto, Model model, PageDTO pageDto) {
		HttpSession session = request.getSession();
		String contentsJsp = null;
		String page = null;
		MemberDTO mdto = (MemberDTO) session.getAttribute("ssKey");
	      
		if(mdto!=null && mdto.getM_role().equals("admin")) {
			ClientDTO notice = clientService.getClient(cdto);
			model.addAttribute("cdto", notice);
			page = "admin/Main";
			contentsJsp = "./NoticeUpForm";
		}else {
			page = "Main";
			contentsJsp = "./custom/ClientCenter";
	    }
		session.setAttribute("ssKey", mdto);
		session.setAttribute("notice", cdto);
		model.addAttribute("contentsJsp", contentsJsp);
		return page;
	}
}
