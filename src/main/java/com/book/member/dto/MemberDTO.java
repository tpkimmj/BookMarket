package com.book.member.dto;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MemberDTO {
	private String mem_id;
	private String m_passwd;
	private String m_name;
	private String m_email;
	private String m_phone;
	private int zipcode;
	private String address;
	private String address2;
	private String m_job;
	private String m_role;
	private String m_regdate;
}
