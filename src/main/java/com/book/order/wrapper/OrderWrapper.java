package com.book.order.wrapper;

import java.util.HashMap;
import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.order.dto.OrderDTO;
import com.book.order.service.OrderService;
import com.book.product.service.ProductService;

@Service("orderWrapper")
public class OrderWrapper {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	public HashMap<String,Object> orderProc(OrderDTO ovo, Hashtable<Integer, OrderDTO> hCartList) {
		String url;
		String msg;
		int r= orderService.insertOrders(hCartList);
		if(r>0){
				msg = "주문완료했습니다";
				url = "orderList";
				hCartList.clear();
			}else {
				msg = "주문실패";
				url = "cartList";
			}
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("url", url);
		map.put("msg", msg);
		map.put("hCartList", hCartList);
		return map;
		}
}
