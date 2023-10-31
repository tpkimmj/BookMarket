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
	
	@Transactional
	public int memDelete(MemberDTO custom) throws Exception{
		int redata = 0;
//		boolean r = orderService.deleteOrder(custom);
//		if(r) {
//			redata = memberService.memDelete(custom);
//		}else {
//			redata = 0;
//		}
		redata = memberService.memDelete(custom);
		return redata;
	}

}
