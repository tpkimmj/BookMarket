package com.book.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.book.member.dto.MemberDTO;
import com.book.order.dto.OrderDTO;

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
}
