package com.book.member.service;

import java.util.Map;

import com.book.common.dto.PageDTO;
import com.book.member.dto.MemberDTO;

public interface MemberService {

	int memberJoin(MemberDTO mdto);

	int idCheck(MemberDTO mvo);

	MemberDTO getMember(MemberDTO mdto);

	Map<String, Object> getMembers(MemberDTO mdto, PageDTO pageDto);

}
