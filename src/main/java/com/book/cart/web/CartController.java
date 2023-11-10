package com.book.cart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.cart.service.CartService;
import com.book.common.dto.PageDTO;
import com.book.member.dto.MemberDTO;
import com.book.order.dto.OrderDTO;
import com.book.product.dto.ProductDTO;

import java.util.Hashtable;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
 
	private static final Logger logger =
			LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	CartService cartService;
	
	
	@RequestMapping("/cartList")
	public String cartList(HttpServletRequest request,
			HttpServletResponse response,
			OrderDTO ovo,
			Model model) {
		HttpSession session = request.getSession();
		MemberDTO mdto = (MemberDTO) session.getAttribute("ssKey");
		if(mdto == null) {
		}
		Hashtable<Integer, OrderDTO> hCartList = (Hashtable<Integer, OrderDTO>)session.getAttribute("hCartList");
		logger.info("a==>"+hCartList);
		
		 return "custom/CartList";
	}
	@RequestMapping("cartProc")
	public String cartProc(HttpServletRequest request,
						   HttpServletResponse response,
						   OrderDTO ovo, ProductDTO pdto,
						   Model model) {
        String page="MsgPage";
        String url = null;
        String msg = null;
		HttpSession session = request.getSession();
	    MemberDTO mdto =(MemberDTO) session.getAttribute("ssKey");
	    //데이터베이스에 저장하지 않고 session의 해시테이블로 처리
	    @SuppressWarnings("unchecked")
	    Hashtable<Integer, OrderDTO> hCartList =
	    (Hashtable<Integer, OrderDTO>) session.getAttribute("hCartList");
	    
	    if(hCartList==null)
	    	hCartList = new Hashtable<>();
	    //서비스를 호출해서 먼저 저장해 두기로 함
	    cartService.setCartList(hCartList);
	    String flag = request.getParameter("flag");
  	    if(mdto !=null ) {
  	      //고객정보가 있으므로 누가 장구니를 채우는 것인지 저장
  	      ovo.setMem_id(mdto.getMem_id());	
  	      url="cartList";
  	    }else {
         msg = "로그인 먼저 필요합니다.";
         url="/login";
  	    }
  	    //flag에 따라서 service호출
  	    switch (flag) {
			case "add": {
				System.out.println(ovo);
				hCartList = cartService.addCart(ovo);
				break;
			}
			case "update": {
				hCartList = cartService.updateCart(ovo);
				break;
			}
			case "delete": {
				hCartList = cartService.deleteCart(ovo);
				break;
			}
		}
  	     model.addAttribute("url", url);
  	     model.addAttribute("msg", msg);
  	     session.setAttribute("hCartList", hCartList);
  	     session.setAttribute("ssKey", mdto);
		return page;
	}
	
	
	
	
	
	
	
	
	
}
