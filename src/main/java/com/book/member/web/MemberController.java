package com.book.member.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.book.member.dto.MemberDTO;
import com.book.member.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/join") //회원가입
	public String join(HttpServletRequest req, HttpServletResponse res, Model model, MemberDTO mdto) {
		return "custom/JoinForm";
	}

	@RequestMapping("/joinProc") //회원가입동작
	public String joinProc(HttpServletRequest req, HttpServletResponse res, Model model, MemberDTO mdto) {
		int r = memberService.memberJoin(mdto);
		return "admin/MemList";
	}
	
	@RequestMapping("idCheck") //id확인
	@ResponseBody
	public int idCheck(HttpServletRequest req, HttpServletResponse res, Model model, MemberDTO mdto) {
		int cnt = memberService.idCheck(mdto);
		return cnt;
	}
	
	@RequestMapping("/login") //로그인 페이지
	public String login(HttpServletRequest request, HttpServletResponse response, MemberDTO mdto, Model model) {
		String page = null;
		String contentsJsp = null;
		
		page = "Main";
		contentsJsp = "custom/login";
		
		model.addAttribute("page", page);
		model.addAttribute("contentsJsp", contentsJsp);
		return page;
	}
	
	@RequestMapping("/loginProc") //로그인 동작
	public String loginProc(HttpServletRequest req, HttpServletResponse res, MemberDTO mdto, Model model) {
		// 세션 정보 (ssKey) - 회원정보
		HttpSession session = req.getSession();
		MemberDTO sdto = memberService.getMember(mdto);
		String url="/";
		String msg;
		if(sdto!=null) {
			// 회원 맞음
			if(sdto.getM_role().equals("admin")) {
				// 관리자용 페이지로 url
				url="/admin/";
			} else {
				url="/";
			}
			MemberDTO ssKey = new MemberDTO();
			ssKey.setMem_id(sdto.getMem_id());
			ssKey.setM_passwd(sdto.getM_passwd());
			ssKey.setM_name(sdto.getM_name());
			ssKey.setM_role(sdto.getM_role());
			msg = sdto.getM_name()+"님 반갑습니다!!";
			session.setAttribute("ssKey", ssKey);
		} else { 
			msg = "아이디 또는 패스워드가 맞지 않습니다.";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "MsgPage";
	}
	
	@RequestMapping("/logoutProc")
	public String logoutProc(HttpServletRequest request, HttpServletResponse response, MemberDTO mdto, Model model) {
		// 세션 정보 (ssKey) - 회원정보
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/info") //고객자신 정보 보기, 로그인 안하면 로그인 하도록 동작
	public String info(HttpServletRequest req, HttpServletResponse res, Model model) {
		// info
		// 세션 키에 데이터를 갖고 있고 그 데이터를 불러와서 해당되는 고객의 정보를 가져오고
		HttpSession session = req.getSession();
		MemberDTO custom = (MemberDTO) session.getAttribute("ssKey");
		// 데이터베이스에서 해당되는 mdto에 있는 정보를 가지고 해당 고객 정보 가져오기
		// 저장해서 화면에서 가져올 수 있도록 한다.
		// 회원정보를 갖고 있는 페이지 (jsp를 따로 만들기(MemberInfo.jsp)
		String page = null;
		String msg = null;
		String url = null;
		if(custom!=null) {
			MemberDTO mdto = memberService.getMember(custom);
			model.addAttribute("mdto", mdto);
			model.addAttribute("contentsJsp", "custom/MemInfo");
			page = "Main";
		}else {
			msg = "로그인 먼저 필요합니다.";
			url = "/login";
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			page = "MsgPage";
		}
		session.setAttribute("ssKey", custom);
		return page;
	}
	
}
