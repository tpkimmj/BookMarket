package com.book.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.book.member.dto.MemberDTO;

@Mapper
public interface MemberDAO {

	public int memberJoin(MemberDTO mdto);

	public int memberTot();

	public List<MemberDTO> getMembers(MemberDTO mdto);

	public int idCheck(MemberDTO mdto);

	public MemberDTO getMember(MemberDTO mdto);

	public int updatePasswd(MemberDTO mdto);

	public String searchId(MemberDTO mdto);

	public int memDelete(MemberDTO mdto);

	public int memUpProc(MemberDTO mdto);

}
