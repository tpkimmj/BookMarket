package com.book.member.service;

import java.util.Map;

import com.book.common.dto.PageDTO;
import com.book.member.dto.MemberDTO;

public interface MemberService {

	int memberJoin(MemberDTO mdto) throws Exception ;

	int idCheck(MemberDTO mvo) throws Exception;

	MemberDTO getMember(MemberDTO mdto) throws Exception;

	Map<String, Object> getMembers(MemberDTO mdto, PageDTO pageDto) throws Exception;

	int updatePasswd(MemberDTO mdto)throws Exception;

	String searchId(MemberDTO mdto)throws Exception;

	int memDelete(MemberDTO custom)throws Exception;

	int memUpProc(MemberDTO mdto)throws Exception;

}
