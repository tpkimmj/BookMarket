package com.book.order.service;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import com.book.member.dto.MemberDTO;
import com.book.order.dto.OrderDTO;

public interface OrderService {

   int insertOrders(Hashtable<Integer, OrderDTO> hCartList);

   Map<String, Object> getOrders(OrderDTO ovo);

   OrderDTO customOrDetail(OrderDTO ovo);

   void updateState(OrderDTO ovo);
   
   boolean deleteOrder(MemberDTO custom);

   void orderStateUpdate(ArrayList<String> tdArr);
   
   
}


