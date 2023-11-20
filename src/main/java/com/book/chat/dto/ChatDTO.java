package com.book.chat.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ChatDTO {
	private String ch_key;
	private String ch_name;
	private String ch_msg;
	private String ch_user;
	private String ch_date;
}
