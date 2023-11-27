package com.book.cart.service;

import java.util.Map;

import com.book.cart.dto.CartDTO;

public interface CartService {

	Map<String, Object> getCarts(CartDTO cto);

	int addCart(CartDTO cto);

	int updateCart(CartDTO cto);

	int deleteCart(CartDTO cto);

	int chkCart(CartDTO cto);

	int sumQunt(CartDTO cto);

	int clearCart(Map<String, Object> reSet);


}
