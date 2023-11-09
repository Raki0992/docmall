package com.docmall.service;

import org.apache.ibatis.annotations.Param;

import com.docmall.domain.MemberVO;

public interface MemberService{
	String idCheck(String mbsp_id);
	
	void join(MemberVO vo);
	
	MemberVO login(String mbsp_id);
	
	void modify(MemberVO vo);
	
	void loginTimeUpdate(String mbsp_id);
	
	void delete(String mbsp_id);
	
	String getIDEmail(@Param("mbsp_id") String mbsp_id, @Param("mbsp_email") String mbsp_email);
	
	void changePW(@Param("mbsp_id") String mbsp_id, @Param("enc_pw") String enc_pw);
	
	String getID(@Param("mbsp_name") String mbsp_name, @Param("mbsp_email") String mbsp_email);
} 
