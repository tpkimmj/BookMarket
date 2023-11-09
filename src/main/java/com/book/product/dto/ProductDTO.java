package com.book.product.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductDTO {
	private String flag;
	private String searchText;
	private int start;
	private int end;
	private int rr;
	private int p_no;
	private int stock;
	private int price;
	private String state;
	private String p_name;
	private String detail;
	private String pr_date;
	private String image;
	private String path;
	private String writer;
	
}
