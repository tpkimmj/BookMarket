package com.book.cart.service;

import java.util.Hashtable;

import org.springframework.stereotype.Service;

import com.book.order.dto.OrderDTO;

@Service
public class CartServiceImpl implements CartService {
	
	private Hashtable<Integer, OrderDTO> hCartList;

	@Override
	public void setCartList(Hashtable<Integer, OrderDTO> hCartList) {
		this.hCartList=hCartList;
	}

	@Override
	public Hashtable<Integer, OrderDTO> getCartList() {
		return hCartList;
	}

	@Override
	public Hashtable<Integer, OrderDTO> addCart(OrderDTO ovo) {
		 int p_no = ovo.getP_no(); int quantity = ovo.getQuantity();
		 if(quantity>0) { 
			 if(hCartList.containsKey(p_no)) { 
				 OrderDTO tempvo = (OrderDTO)hCartList.get(p_no); 
				 quantity+=tempvo.getQuantity();
		 if(tempvo.getStock()<quantity) { 
			 tempvo.setQuantity(tempvo.getQuantity());
		 }else {
			 tempvo.setQuantity(quantity); 
			 } 
		 hCartList.put(p_no, tempvo);
		 }else {
			 hCartList.put(p_no, ovo);
		 } 
			 } 
		 return hCartList; 
	}
	

	@Override
	public Hashtable<Integer, OrderDTO> updateCart(OrderDTO ovo) {
		 int p_no =ovo.getP_no(); int quantity=ovo.getQuantity();
		 if(hCartList.containsKey(p_no)) { 
			 OrderDTO tempvo = (OrderDTO)hCartList.get(p_no); 
			 //재고가 주문수량보다 적을경우
			 if(tempvo.getStock()<quantity) {
				 tempvo.setQuantity(tempvo.getQuantity());
			 }else {
				 tempvo.setQuantity(quantity); 
			 } 
			 tempvo.setQuantity(quantity);
			 hCartList.put(p_no, tempvo); 
	 	} 
	 	return hCartList; 
	 }
	

	@Override
	public Hashtable<Integer, OrderDTO> deleteCart(OrderDTO ovo) {
		int p_no = ovo.getP_no();
		hCartList.remove(p_no);
		return hCartList;
	}
}
