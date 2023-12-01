package com.book.wrapper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.cart.service.CartService;
import com.book.order.dto.OrderDTO;
import com.book.order.service.OrderService;
import com.book.product.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service("orderWrapper")
public class OrderWrapper {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartService cartService;
	
	public HashMap<String, Object> orderProc(HttpServletRequest request, HttpServletResponse response, OrderDTO ovo, Map<String, Object> reSet) {
		String url;
		String msg;
		
		int r= orderService.insertOrders(reSet);
		if(r>0){
			productService.updateStocks(reSet);
			msg = "주문완료했습니다";
			url = "cartPayment";
			//cartService.clearCart(reSet);
		}else {
			msg = "주문실패";
			url = "cartList";
		}
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("url", url);
		map.put("msg", msg);
		map.put("reSet", reSet);
		return map;
	}
}
