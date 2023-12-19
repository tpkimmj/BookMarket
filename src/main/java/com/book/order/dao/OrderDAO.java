package com.book.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.book.member.dto.MemberDTO;
import com.book.order.dto.OrderDTO;
import com.book.product.dto.ProductDTO;

@Mapper
public interface OrderDAO {

	int insertOrders(List<OrderDTO> list);

	int getTotalOrder(Object object);

	List<OrderDTO> getOrders(OrderDTO odto);

	OrderDTO customOrDetail(OrderDTO ovo);

	Object updateState(OrderDTO ovo);

	List<OrderDTO> getOrderOfMember(MemberDTO custom);

	void deleteOrder(MemberDTO custom);

	void updateOrderState(List<OrderDTO> list);
	
	MemberDTO getMember(MemberDTO mdto);

	ProductDTO getProduct(String parameter);
	
	// 주문 취소	
	int orderCancle(OrderDTO odto);
	// 주문 상품 정보
	List<OrderDTO> getOrderItemInfo(String memid);
	// 주문 정보
	OrderDTO getOrder(String memid);
	
	int getOrderCnt();

	List<OrderDTO> getOrderList(OrderDTO odto);

	Map<String, Object> payOrders(OrderDTO ovo);

	List<Map<String, Object>> getpayOrders(OrderDTO ovo);

	List<Map<String, Object>> payCancle(OrderDTO ovo);

	void orderupdateSate(OrderDTO odto);

	void minstateupdate(OrderDTO odto);

	void minstockst(int quantity);

	void mistateupdate(OrderDTO odto);

	void ordCancleMgt(OrderDTO dList);

	List<OrderDTO> getpayOrd(OrderDTO odto);

	List<OrderDTO> orderCancleList(OrderDTO odto);
}