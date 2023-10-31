package com.book.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.common.dto.PageDTO;
import com.book.member.dao.MemberDAO;
import com.book.member.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDAO memberDao;
	
	@Override
	public int memberJoin(MemberDTO mdto) throws Exception {

		return memberDao.memberJoin(mdto);
	}

	@Override //회원가입시 id중복 확인
	public int idCheck(MemberDTO mdto) throws Exception {
		return memberDao.idCheck(mdto);
	}

	@Override //고객이 자신 정보 확인
	public MemberDTO getMember(MemberDTO mdto) throws Exception {
		return memberDao.getMember(mdto);
	}

	@Override //관리자에서 회원리스트 보기
	public Map<String, Object> getMembers(MemberDTO mdto, PageDTO pageDto) throws Exception {
		Map<String, Object> reSet = new HashMap<String, Object>();
		// 전체 카운트 갖고오기
		int memberTot = memberDao.memberTot();
		List<MemberDTO> members = memberDao.getMembers(mdto);
		reSet.put("memberTot", memberTot);
		reSet.put("members", members);
		return reSet;
	}

	@Override
	public int updatePasswd(MemberDTO mdto) throws Exception{
		return memberDao.updatePasswd(mdto);
	}

	@Override
	public String searchId(MemberDTO mdto) throws Exception{
		return memberDao.searchId(mdto);
	}

	@Override
	public int memDelete(MemberDTO mdto) throws Exception {
		return memberDao.memDelete(mdto);
	}

	@Override
	public int memUpProc(MemberDTO mdto) throws Exception {
		return memberDao.memUpProc(mdto);
	}

}
