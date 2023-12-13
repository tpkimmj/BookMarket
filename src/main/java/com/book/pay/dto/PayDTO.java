package com.book.pay.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PayDTO {
	private String transactionid;
	private int o_no;
	private String authdatetime;
	private int amount;
	private String cardname;
	private int cardno;
	private int quota;
}
