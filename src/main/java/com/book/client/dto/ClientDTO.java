package com.book.client.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientDTO {
	private int cno;
	private int readcount;
	private String subject;
	private String content;
	private String writer ;
	private String regdate;
	private String mem_id;
	private char state;
}
