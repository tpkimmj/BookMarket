package com.book.cart.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.cart.dto.CartDTO;
import com.book.cart.service.CartService;
import com.book.member.dto.MemberDTO;
import com.book.member.service.MemberService;
import com.book.product.dto.ProductDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
 
	@Autowired
	CartService cartService;
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/cartList")
	public String cartList(HttpServletRequest request, HttpServletResponse response, MemberDTO mdto, CartDTO cto, Model model) {
		HttpSession session = request.getSession();
		MemberDTO custom = (MemberDTO) session.getAttribute("ssKey");
		String url = null;
		String msg = null;
		String page = null;
	    Map<String, Object> reSet = null;
    	if(custom != null) { //고객정보가 있으므로 누가 장구니를 채우는 것인지 저장
    		cto.setMem_id(custom.getMem_id());
    		cto.setM_role(custom.getM_role());
    		reSet = cartService.getCarts(cto);
    		model.addAttribute("cartList", reSet.get("cartList"));
    		model.addAttribute("cartTot", reSet.get("cartdTot"));
        	model.addAttribute("contentsJsp", "custom/CartList"); 
        	page = "Main"; 
    	}else{ 
    		msg = "로그인 먼저 필요합니다."; 
    		url = "/login";
			model.addAttribute("url", url);
			model.addAttribute("msg", msg);
			page = "MsgPage"; 
		}
    	session.setAttribute("ssKey", custom);
		return page;
	}
	
	@RequestMapping("cartProc")
	public String cartProc(HttpServletRequest request, HttpServletResponse response, CartDTO cto, ProductDTO pdto, Model model) {
        String page="MsgPage";
        String url = null;
        String msg = null;
		HttpSession session = request.getSession();
	    MemberDTO mdto =(MemberDTO) session.getAttribute("ssKey");
	    String flag = request.getParameter("flag");
  	    if(mdto !=null ) {
  	    	//고객정보가 있으므로 누가 장구니를 채우는 것인지 저장
  	    	cto.setMem_id(mdto.getMem_id());	
  	    	cto.setP_no(pdto.getP_no());
  	    	url="cartList";
  	    }else {
  	    	msg = "로그인 먼저 필요합니다.";
  	    	url="/login";
  	    }
  	    //flag에 따라서 service호출
  	    switch (flag) {
			case "add": {
				//장바구니에 기존 상품이 있는지 검사
				int count = cartService.chkCart(cto);
				//상품 없으면 add : 상품 있으면 수량만 증가
				count = count == 0 ? cartService.addCart(cto) : cartService.sumQunt(cto);
				break;
			}
			case "update": {
				cartService.updateCart(cto);
				break;
			}
			case "delete": {
				cartService.deleteCart(cto);
				break;
			}
		}
  	    model.addAttribute("url", url);
  	    model.addAttribute("msg", msg);
  	    session.setAttribute("ssKey", mdto);
		return page;
	}
}
