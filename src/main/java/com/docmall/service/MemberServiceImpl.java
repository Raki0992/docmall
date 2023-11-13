package com.docmall.service;

import org.springframework.stereotype.Service;

import com.docmall.domain.MemberVO;
import com.docmall.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@Service
public class MemberServiceImpl implements MemberService {
	// 자동주입
	// @RequiredArgsConstructor : memberMapper필드를 매개변수로 하는 생성자 메소드가 생성된다.
	// private MemberServiceImpl(MemberMapper memberMapper) {
	//		this.memberMapper = memberMapper;
	//	}
	private final MemberMapper memberMapper;
 
	@Override
	public String idCheck(String mbsp_id) {
		return memberMapper.idCheck(mbsp_id);
	}

	@Override
	public void join(MemberVO vo) {
		 memberMapper.join(vo);
		
	}

	@Override
	public MemberVO login(String mbsp_id) {
		return memberMapper.login(mbsp_id); 
	}

	@Override
	public void modify(MemberVO vo) {
		memberMapper.modify(vo);
	}

	@Override
	public void loginTimeUpdate(String mbsp_id) {
		memberMapper.loginTimeUpdate(mbsp_id);
	}

	@Override
	public void delete(String mbsp_id) {
		memberMapper.delete(mbsp_id); 
	}

	@Override
	public String getIDEmail(String mbsp_id, String mbsp_email) {
		return memberMapper.getIDEmail(mbsp_id, mbsp_email); 
	}

	@Override
	public void changePW(String mbsp_id, String enc_pw) {
		memberMapper.changePW(mbsp_id, enc_pw);
	}

	@Override
	public MemberVO getID(String mbsp_name ,String mbsp_email) {
		return memberMapper.getID(mbsp_name, mbsp_email);
	}

	

	
}
