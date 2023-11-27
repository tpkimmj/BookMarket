package com.book.cart.dto;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CartDTO {
	private int rr;
	private int c_no;	//기본키
	
	private int price;
	private int quantity;
	private String c_regdate;
	
	private String mem_id;   //외래키
	private String m_name;   //member
	private String m_email;
	private String m_phone;
	private int zipcode;
	private String address;
	private String address2;
	private String m_role;
	
	private int p_no;   //외래키
	private int stock;
	private int amount;
	private String p_name; //product
	private int startRow = 1;
	private int endRow = 10;
	private int o_no;
}
