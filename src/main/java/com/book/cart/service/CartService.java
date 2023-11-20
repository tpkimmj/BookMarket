package com.book.cart.service;

import java.util.Hashtable;

import com.book.order.dto.OrderDTO;



public interface CartService {

	void setCartList(Hashtable<Integer, OrderDTO> hCartList);
	
	Hashtable<Integer, OrderDTO> addCart(OrderDTO ovo);
	
	Hashtable<Integer, OrderDTO> updateCart(OrderDTO ovo);
	
	Hashtable<Integer, OrderDTO> deleteCart(OrderDTO ovo);
	
	Hashtable<Integer, OrderDTO> getCartList();

}
