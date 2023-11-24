package com.book.cart.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.cart.dao.CartDAO;
import com.book.cart.dto.CartDTO;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	CartDAO cartDao;

	@Override
	public Map<String, Object> getCarts(CartDTO cto) {
		int cartTot = 0;
		if(cto.getM_role().equals("admin")) {
			cartTot = cartDao.getTotalCart(null);
		}else {
			cartTot = cartDao.getTotalCart(cto.getMem_id());
		}
		List<CartDTO> cartList = cartDao.getCarts(cto);
		Map<String, Object> reSet = new HashMap<String, Object>();
		reSet.put("cartTot", cartTot);
		reSet.put("cartList", cartList);
		System.out.println("===="+cartList);
		return reSet;
	}

	@Override
	public int addCart(CartDTO cto) { //상품아이디 멤버아이디 수량 파라미터를 카트디티오또는리스트
		int p_no = cto.getP_no();
		int quantity = cto.getQuantity();
		int result = cartDao.addCart(cto);
		System.out.println("++++++++"+quantity);
//		if(quantity>0) { 
//			if(cartList.containsKey(p_no)) { 
//				CartDTO tempvo = (CartDTO)cartList.get(p_no); 
//				quantity+=tempvo.getQuantity();
//				if(tempvo.getStock()<quantity) { 
//					tempvo.setQuantity(tempvo.getQuantity());
//				}else {
//					tempvo.setQuantity(quantity); 
//				}
//				tempvo.setQuantity(quantity);
//				cartList.put(p_no, tempvo);
//			}else {
//				cartList.put(p_no, cto);
//			} 
//		} 
		return result; 
	}

	@Override
	public int updateCart(CartDTO cto) {
		int p_no =cto.getP_no();
		int quantity=cto.getQuantity();
		int result = cartDao.updateCart(cto); 
//		 if(cartList.contains(p_no)) { 
//			 CartDTO tempvo = (CartDTO)cartList.get(p_no); 
//			 //재고가 주문수량보다 적을경우
//			 if(tempvo.getStock()<quantity) {
//				 tempvo.setQuantity(tempvo.getQuantity());
//			 }else {
//				 tempvo.setQuantity(quantity); 
//			 } 
//			 tempvo.setQuantity(quantity);
//			 cartList.add(p_no, cto); 
//	 	} 
	 	return result; 
	}

	@Override
	public int deleteCart(CartDTO cto) {
		return cartDao.deleteCart(cto);
	}
}
