package com.book.order.dto;

import lombok.Data;

@Data
public class OrderDTO {
   private int rr;
   private int o_no;   //기본키
   private int price;
   private int quantity;
   private String o_regdate;
	/* private String state; */
   private int state;
   private String mem_id;   //외래키
   private String m_name;   //member
   private String m_role;
   private int p_no;   //외래키
   private int stock;
   private int amount;
   private String p_name; //product
   private int startRow = 1;
   private int endRow = 10;

}