package com.docmall.mapper;

import java.util.List;

import com.docmall.domain.AdminVO;
import com.docmall.domain.MemberVO;
import com.docmall.dto.Criteria;

public interface AdminMapper {

	AdminVO admin_ok(String admin_id);
	
	void loginTimeUpdate(String admin_id);
	
	void member_list(MemberVO vo);
	

}
