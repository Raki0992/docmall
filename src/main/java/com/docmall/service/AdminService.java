package com.docmall.service;

import java.util.List;

import com.docmall.domain.AdminVO;
import com.docmall.domain.MemberVO;
import com.docmall.dto.Criteria;

public interface AdminService {

	AdminVO admin_ok(String admin_id);
	
	void loginTimeUpdate(String admin_id);
	
	List<MemberVO> member_list(Criteria cri);
}
