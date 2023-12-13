package com.book.cart.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.book.cart.dto.CartDTO;
import com.book.order.dto.OrderDTO;

@Mapper
public interface CartDAO {

	public List<CartDTO> getCarts(CartDTO cdto);

	public int getTotalCart(Object object);

	public int addCart(CartDTO cto);

	public int updateCart(CartDTO cto);

	public int deleteCart(CartDTO cto);

	public int chkCart(CartDTO cto);

	public int sumQunt(CartDTO cto);

	public List<CartDTO> selectAll(CartDTO cto);

	public int clearCart(List<OrderDTO> list);

}
