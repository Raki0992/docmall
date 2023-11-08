package com.docmall.mapper;

import org.springframework.web.bind.annotation.RequestParam;

import com.docmall.domain.MemberVO;

public interface MemberMapper {
// interface는 자동으로 public제공하기 때문에 사용안해도 됨
	
	String idCheck(String mbsp_id);
	
	void join(MemberVO vo);
	
	MemberVO login(String mbsp_id);		// 비밀번호 사용안함 암호화복호화
	
	void modify(MemberVO vo);
	
	void loginTimeUpdate(String mbsp_id);
	
	void delete(String mbsp_id);
	
	String getIDEmail(@RequestParam("mbsp_id") String mbsp_id, @RequestParam("mbsp_email") String mbsp_email);
}
