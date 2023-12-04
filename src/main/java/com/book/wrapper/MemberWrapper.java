package com.book.wrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.book.member.dto.MemberDTO;
import com.book.member.service.MemberService;

@Service("memberWrapper")
public class MemberWrapper {
	@Autowired
	MemberService memberService;
	
	@Transactional //회원탈퇴 , 밑에 주석된부분은 추후 주문내역과 조인한뒤에 활성화 하면 됨.
	public int memDelete(MemberDTO custom) throws Exception{
		int redata = 0;
		redata = memberService.memDelete(custom);
		return redata;
	}
}
