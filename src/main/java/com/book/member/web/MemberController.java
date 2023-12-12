package com.book.member.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.book.common.dto.PageDTO;
import com.book.member.dto.MemberDTO;
import com.book.member.service.MemberService;
import com.book.wrapper.MemberWrapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberWrapper memberWrapper;
	
	@RequestMapping("/join") //회원가입
	public String join(HttpServletRequest req, HttpServletResponse res, Model model, MemberDTO mdto) {
		String page = null;
		
		page = "Main";
		
		model.addAttribute("page", page);
		model.addAttribute("contentsJsp", "custom/JoinForm");
		return page;
	}

	@RequestMapping("/joinProc") //회원가입동작
	public String joinProc(HttpServletRequest req, HttpServletResponse res, Model model, MemberDTO mdto) {
		String msg = null;
		try {
			memberService.memberJoin(mdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		msg = "회원가입을 환영합니다!";
		
		model.addAttribute("msg", msg);
		return "MsgPage";
	}
	
	@RequestMapping("idCheck") //id확인
	@ResponseBody
	public int idCheck(HttpServletRequest req, HttpServletResponse res, Model model, MemberDTO mdto) {
		int cnt = 0;
		try {
			cnt = memberService.idCheck(mdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	@RequestMapping("/login") //로그인 페이지
	public String login(HttpServletRequest request, HttpServletResponse response, MemberDTO mdto, Model model) {
		String page = null;
		
		page = "Main";
		
		model.addAttribute("page", page);
		model.addAttribute("contentsJsp", "custom/login");
		return page;
	}
	
	@RequestMapping("/loginProc") //로그인 동작
	public String loginProc(HttpServletRequest req, HttpServletResponse res, MemberDTO mdto, Model model) {
		// 세션 정보 (ssKey) - 회원정보
		HttpSession session = req.getSession();
		MemberDTO sdto;
		String url="/";
		String msg = null;
		try {
			sdto = memberService.getMember(mdto);
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
				url = "/login";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "MsgPage";
	}
	
	@RequestMapping("/logoutProc") //로그아웃 동작
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
			MemberDTO mdto = null;
			try {
				mdto = memberService.getMember(custom);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
	
	@RequestMapping("/memberMgt") //회원전체 목록 보기(관리자용)
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
		Map<String, Object> reSet = null;
		try {
			reSet = memberService.getMembers(mdto, pageDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("memberTot", reSet.get("memberTot"));
		model.addAttribute("members", reSet.get("members"));
		model.addAttribute("PageDto", reSet.get("PageDto"));
		model.addAttribute("contentsJsp", "/MemList");
		return page;
	}
	
	@RequestMapping("/memSearch") //아이디찾기, 비미번호 변경 페이지로 이동
	public String memSearch(HttpServletRequest req, HttpServletResponse res, MemberDTO mdto, Model model) {
		return "custom/SearchPage";
	}
	
	@RequestMapping("/memSearchProc")
	public String memSearchProc(HttpServletRequest req, HttpServletResponse res, MemberDTO mdto, Model model) {
		// 회원이 맞으면 비밀번호 새로 설정 페이지
		// 회원정보가 맞지 않습니다. msg 회원가입으로 
		return "memSearch";
	}
	
	@RequestMapping("/searchProc")//아이디찾기, 비밀번호변경
	public String searchProc(HttpServletRequest req, HttpServletResponse res, MemberDTO mdto, Model model) {
		int r = 0;
		String id = null;
		String msg = null;
		String url = "/";
		if(mdto!=null) {
			if(mdto.getMem_id()!=null) { // 비밀번호 설정
				try {
					r = memberService.updatePasswd(mdto);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(r>0) {
					msg = "비밀번호가 변경되었습니다.";
				}else { msg = "비밀번호 변경오류입니다. \\n 관리자에게 문의하세요.";
					}
			} else { // 아이디 찾아서 리턴
				try {
					id = memberService.searchId(mdto);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(id!=null) msg = "회원아이디: "+id;
				else msg = "회원정보가 없습니다.";
				url = "memSearch";
			}
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "MsgPage";
	}
	
	@RequestMapping("/pwCheck") //비밀번호 확인 팝업창
	public String pwCheck(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		session.getAttribute("ssKey");
		
		return "custom/PwCheck";
	}
	
	@RequestMapping("/memUpForm") //회원정보 수정 화면 전환
	public String memUpForm(HttpServletRequest req, HttpServletResponse res, Model model) {
		// session정보 갖고오기
		HttpSession session = req.getSession();
		MemberDTO custom = (MemberDTO) session.getAttribute("ssKey");
		// 세션정보를 기준으로 회원 정보 가져오기
		String page = null;
		String msg = null;
		String url = null;
		if(custom!=null) {
			MemberDTO mdto = null;
			try {
				mdto = memberService.getMember(custom);
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("mdto", mdto);
			model.addAttribute("contentsJsp", "custom/MemUpForm");
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
	
	@RequestMapping("/memUpProc") //회원정보 수정 동작
	public String memUpProc(HttpServletRequest req, HttpServletResponse res, Model model, MemberDTO mdto) {
		// session정보 갖고오기
		HttpSession session = req.getSession();
		MemberDTO custom = (MemberDTO) session.getAttribute("ssKey");
		// 세션정보를 기준으로 회원 정보 가져오기
		String page = null;
		String msg = null;
		String url = null;
		if(custom!=null) {
			int r = 0;
			try {
				r = memberService.memUpProc(mdto);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(r>0) {
				msg = "회원정보가 수정되었습니다."; //\\n 재로그인이 필요합니다.";
				//session.invalidate(); 재로그인 하기위함
			}
			else msg = "수정되지 않았습니다. \\n 관리자에게 문의바랍니다.";
			url = "/";
		}else {
			msg = "로그인 먼저 필요합니다.";
			url = "/login";
		}
		page = "MsgPage";
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return page;
	}

	@RequestMapping("/memdelete") //회원탈퇴
	public String memdelete(HttpServletRequest req, HttpServletResponse res, Model model, MemberDTO mdto) {
		// session정보 갖고오기
		HttpSession session = req.getSession();
		MemberDTO custom = (MemberDTO) session.getAttribute("ssKey");
		// 세션정보를 기준으로 회원 정보 가져오기
		String page = null;
		String msg = null;
		String url = null;
		if(custom!=null) {
			int r = 0;
			try {
				r = memberWrapper.memDelete(custom);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(r>0) {
				msg = "회원이 탈퇴 처리 되었습니다.";
				session.invalidate();
			}
			else {
				msg = "탈퇴할 수 없습니다. \\n 관리자에게 문의바랍니다.";
			}
			url = "/";
		}else {
			msg = "로그인 먼저 필요합니다.";
			url = "/login";
		}
		page = "MsgPage";
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return page;
	}
}
