package com.book.order.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.book.member.dto.MemberDTO;
import com.book.order.dto.OrderDTO;
import com.book.product.dto.ProductDTO;

public interface OrderService {

	Map<String, Object> getOrders(OrderDTO ovo);

	OrderDTO customOrDetail(OrderDTO ovo);

	void updateState(OrderDTO ovo);
   
	boolean deleteOrder(MemberDTO custom);

	void orderStateUpdate(ArrayList<String> tdArr);
	
	MemberDTO getMember(MemberDTO mdto);

	ProductDTO getProduct(String parameter);
	
	int orderCancle(OrderDTO odto);

	int insertOrders(Map<String, Object> reSet);

	Map<String, Object> payOrders(OrderDTO ovo);

	List<Map<String, Object>> getpayOrders(OrderDTO ovo);

	void orderupdateSate(OrderDTO odto);

	void minstateupdate(OrderDTO odto);

	void minstockst(int quantity);

	void mistateupdate(OrderDTO odto);

	void ordCancleMgt(OrderDTO dList);
	
	List<OrderDTO> getpayOrd(OrderDTO odto);

	List<OrderDTO> orderCancleList(OrderDTO odto);
}

