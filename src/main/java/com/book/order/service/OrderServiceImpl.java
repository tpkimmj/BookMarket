package com.book.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.member.dto.MemberDTO;
import com.book.order.dao.OrderDAO;
import com.book.order.dto.OrderDTO;

@Service
public class OrderServiceImpl implements OrderService {
   @Autowired
   OrderDAO orderDao;
   
   @Override
   public int insertOrders(Hashtable<Integer, OrderDTO> hCartList) {
      //키를 받아서 해당 키 만큼 주문내역을 리스트로 받아서 데이터베이스 리스트로 저장
      //결제를 하고 ok 되면
      Set<Integer> keys = hCartList.keySet();
      List<OrderDTO> list = new ArrayList<OrderDTO>(keys.size());
      Iterator<Integer> iterKeys = keys.iterator();
      while(iterKeys.hasNext()) {
         list.add(hCartList.get(iterKeys.next()));
      }
      return orderDao.insertOrders(list);
   }

   @Override
   public Map<String, Object> getOrders(OrderDTO odto) {
      int orderTot = 0;
      if(odto.getM_role().equals("admin")) {
    	  orderTot=orderDao.getTotalOrder(null);
      }else {
    	  orderTot=orderDao.getTotalOrder(odto.getMem_id());
      }
      List<OrderDTO> orderList = orderDao.getOrders(odto);
      Map<String, Object> reSet = new HashMap<String, Object>();
      reSet.put("orderTot", orderTot);
      reSet.put("orderList", orderList);
      return reSet;
   }

   @Override
   public OrderDTO customOrDetail(OrderDTO ovo) {
   	
   	return orderDao.customOrDetail(ovo);
   }

   @Override
   public void updateState(OrderDTO ovo) {
	  orderDao.updateState(ovo);
   }

@Override
public boolean deleteOrder(MemberDTO custom) {
	// 1. 사용자에 해당하는 모든 구매 이력을 갖고 와서 state가 5보다 작으면 안된다.(삭제 불가)
	// 2. 그렇지 않으면 모두 삭제하고 return true
	// 3. 조회해서 갖고와서 비즈 로직에서 비교 후 가능한 경우만 삭제
	List<OrderDTO> orderList = orderDao.getOrderOfMember(custom);
	boolean reData = false;
	if(orderList.size()>0) {
		//삭제하고 
		reData = false;
	}else {
		//삭제하고 리턴
		try {
		orderDao.deleteOrder(custom);
		reData= true;
		}catch(Exception e) {
			reData = false;
		}
	} 
	return reData;
}

@Override
public void orderStateUpdate(ArrayList<String> tdArr) {
	List<OrderDTO> list = new ArrayList<OrderDTO>();
	for(int i = 0; i<tdArr.size(); i+=4) {
		OrderDTO odto = new OrderDTO();
		int n=0, no=0;
		String mid=null;
		n = tdArr.get(i).indexOf(":");
		no = Integer.parseInt(tdArr.get(i).substring(n+1));
		odto.setO_no(no);
		
		n = tdArr.get(i+1).indexOf(":");
		no = Integer.parseInt(tdArr.get(i+1).substring(n+1));
		odto.setP_no(no);
		
		n = tdArr.get(i+2).indexOf(":");
		mid = tdArr.get(i+2).substring(n+1);
		odto.setMem_id(mid);
		
		n = tdArr.get(i+3).indexOf(":");
		no = Integer.parseInt(tdArr.get(i+3).substring(n+1));
		odto.setState(no);
		list.add(odto);
	}
	orderDao.updateOrderState(list);
		
	}

}
	