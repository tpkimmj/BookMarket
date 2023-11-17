package com.book.order.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.book.cart.service.CartService;
import com.book.member.dto.MemberDTO;
import com.book.order.dto.OrderDTO;
import com.book.order.service.OrderService;
import com.book.order.wrapper.OrderWrapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	
private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
//트랜잭션을 처리 하는 로직
   /* 주문이 성공하면 주문테이블에 해당 주문이 저장되고
    * 상품테이블에 해당 상품의 재고가 수량만큼 줄어야 함
    */
   @Autowired
   private OrderService orderService;
   
   //서로 다른 업무에 관련하여 트랜잭션 서비스를 하기위해 사용하는 서비스를 Wrapper
   @Autowired
   private OrderWrapper orderWrapper;
   
   @Autowired
   private CartService cartService;
   
   @RequestMapping("/orderProc")
   public String orderProc(HttpServletRequest request, HttpServletResponse response, OrderDTO ovo, Model model) {
      HttpSession session = request.getSession();
      MemberDTO custom = (MemberDTO) session.getAttribute("ssKey");
      //장바구니
      Hashtable<Integer, OrderDTO> hCartList = cartService.getCartList();
      //주문하고 재고 하나 줄고
      HashMap<String, Object> map = orderWrapper.orderProc(ovo, hCartList);
      //model
      model.addAttribute("msg", map.get("msg"));
      model.addAttribute("url", map.get("url"));
      session.setAttribute("ssKey", custom);
      session.setAttribute("hCartList", hCartList);
      return "MsgPage";
   }
   @RequestMapping("/orderList")
   public String orderList(HttpServletRequest request, HttpServletResponse response, OrderDTO ovo, Model model) {
      HttpSession session = request.getSession();
      MemberDTO custom = (MemberDTO) session.getAttribute("ssKey");
      String url=null;
      String msg = null;
      String page = null;
      Map<String, Object> reSet = null;
      //해당되는 고객의 주문 정보만 갖고 와야 함
      if(custom != null) {
         //고객정보가 있으므로 누가 장바구니를 채우는 것인지 저장
         ovo.setMem_id(custom.getMem_id());
         ovo.setM_role(custom.getM_role());
         //주문 내역 가져오는 service호출
         reSet= orderService.getOrders(ovo);
         model.addAttribute("orderList", reSet.get("orderList"));
         model.addAttribute("orderTot", reSet.get("orderTot"));
         model.addAttribute("contentsJsp", "custom/OrderList");
         page = "Main";
      }else {
         //먼저 로그인 페이지로 전송
         msg = "로그인 먼저 필요합니다.";
         url = "/login";
         model.addAttribute("msg", msg);
         model.addAttribute("url", url);
         page = "MsgPage";
      }
      session.setAttribute("ssKey", custom);
      return page;
   }
   @RequestMapping("/customOrDetail")
   public String customOrDetail(HttpServletRequest request, HttpServletResponse response, OrderDTO ovo, Model model) {
	   String page = null;
	   logger.info("ovo==="+ovo);
	   HttpSession session = request.getSession();
	   MemberDTO custom = (MemberDTO) session.getAttribute("ssKey");
	   if(custom!=null) {
		   ovo = orderService.customOrDetail(ovo);
		   model.addAttribute("odto", ovo);
		   model.addAttribute("contentsJsp", "custom/OrderDetail");
		   page="Main";
	   }else {
		   String msg = "세션이 종료되었습니다. 재로그인 필요합니다.";
		   model.addAttribute("msg", msg);
		   model.addAttribute("url", "/login");
		   page="MsgPage";
	   }
	   session.setAttribute("ssKey", custom);
	return page;
   }
   @RequestMapping("/orderMgt")
   public String orderMgt(HttpServletRequest request, HttpServletResponse response, OrderDTO ovo, Model model) {
      HttpSession session = request.getSession();
      MemberDTO admin = (MemberDTO) session.getAttribute("ssKey");
      String url=null;
      String msg = null;
      String page = null;
      Map<String, Object> reSet = null;
      //해당되는 고객의 주문 정보만 갖고 와야 함
      if(admin != null) {
         //고객정보가 있으므로 누가 장바구니를 채우는 것인지 저장
         ovo.setM_role(admin.getM_role());
         //주문 내역 가져오는 service호출
         reSet= orderService.getOrders(ovo);
         model.addAttribute("orderList", reSet.get("orderList"));
         model.addAttribute("orderTot", reSet.get("orderTot"));
         model.addAttribute("contentsJsp", "./OrderList");
         page = "admin/Main";
      }else {
         //먼저 로그인 페이지로 전송
         msg = "로그인 먼저 필요합니다.";
         url = "/login";
         model.addAttribute("msg", msg);
         model.addAttribute("url", url);
         page = "MsgPage";
      }
      session.setAttribute("ssKey", admin);
      return page;
   }
   @RequestMapping("/orDetailMgt")
   public String orDetailMgt(HttpServletRequest request, HttpServletResponse response, OrderDTO ovo, Model model) {
	   String page = null;
	   logger.info("ovo==="+ovo);
	   HttpSession session = request.getSession();
	   MemberDTO admin = (MemberDTO) session.getAttribute("ssKey");
	   if(admin!=null) {
		   ovo = orderService.customOrDetail(ovo);
		   model.addAttribute("odto", ovo);
		   model.addAttribute("contentsJsp", "./OrderDetail");
		   page="admin/Main";
	   }else {
		   String msg = "세션이 종료되었습니다. 재로그인 필요합니다.";
		   model.addAttribute("msg", msg);
		   model.addAttribute("url", "/login");
		   page="MsgPage";
	   }
	   session.setAttribute("ssKey", admin);
	return page;
   }
   @RequestMapping("/updateOrder")
   public String updateOrder(HttpServletRequest request, HttpServletResponse response, OrderDTO ovo, Model model) {
      HttpSession session = request.getSession();
      MemberDTO admin = (MemberDTO) session.getAttribute("ssKey");
      String url=null;
      String msg = null;
      String page = null;
      Map<String, Object> reSet = null;
      //해당되는 고객의 주문 정보만 갖고 와야 함
      if(admin != null) {
    	  orderService.updateState(ovo);
         page = "redirect:/orderMgt";
      }else {
         //먼저 로그인 페이지로 전송
         msg = "로그인 먼저 필요합니다.";
         url = "/login";
         model.addAttribute("msg", msg);
         model.addAttribute("url", url);
         page = "MsgPage";
      }
      session.setAttribute("ssKey", admin);
      return page;
   }
   @RequestMapping("/orderMgtProc")
   @ResponseBody
   public void orderMgtProc(HttpServletRequest request, HttpServletResponse response, OrderDTO odto,@RequestParam(value="tdArr[]") ArrayList<String> tdArr, Model model) {
     
	   HttpSession session = request.getSession();
	   MemberDTO admin = (MemberDTO) session.getAttribute("ssKey");
	   if(admin!=null && admin.getM_role().equals("admin")) {
		   try {
		    	 orderService.orderStateUpdate(tdArr);
		     }catch(Exception e) {    	 
		     }
	   }
	   try {
    	 orderService.orderStateUpdate(tdArr);
     }catch(Exception e) {
    	 
     	}
   }
}