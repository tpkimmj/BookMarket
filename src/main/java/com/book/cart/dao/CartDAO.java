package com.book.cart.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.book.cart.dto.CartDTO;

@Mapper
public interface CartDAO {

	public List<CartDTO> getCarts(CartDTO cdto);

	public int getTotalCart(Object object);

	public int addCart(CartDTO cto);

	public int updateCart(CartDTO cto);

	public int deleteCart(CartDTO cto);

}
