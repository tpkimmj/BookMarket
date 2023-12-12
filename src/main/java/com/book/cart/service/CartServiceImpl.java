package com.book.cart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.cart.dao.CartDAO;
import com.book.cart.dto.CartDTO;
import com.book.order.dto.OrderDTO;

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
		return reSet;
	}

	@Override
	public int addCart(CartDTO cto) {
		cto.getP_no();
		cto.getQuantity();
		int result = cartDao.addCart(cto);
		return result; 
	}

	@Override
	public int updateCart(CartDTO cto) {
		cto.getP_no();
		cto.getQuantity();
		int result = cartDao.updateCart(cto); 
	 	return result; 
	}

	@Override
	public int deleteCart(CartDTO cto) {
		return cartDao.deleteCart(cto);
	}

	@Override
	public int chkCart(CartDTO cto) {
		return cartDao.chkCart(cto);
	}

	@Override
	public int sumQunt(CartDTO cto) {
		return cartDao.sumQunt(cto);
	}

	@Override
	public int clearCart(Map<String, Object> reSet) {
		@SuppressWarnings("unchecked")
		List<CartDTO> cartList = (List<CartDTO>) reSet.get("cartList");
		List<OrderDTO> list = new ArrayList<OrderDTO>(cartList.size());
		for(CartDTO cto : cartList) {
			OrderDTO ovo = new OrderDTO();
			ovo.setMem_id(cto.getMem_id());
			ovo.setM_role(cto.getM_role());
			ovo.setP_no(cto.getP_no());
			
			list.add(ovo);
		}
		return cartDao.clearCart(list);
	}

}
