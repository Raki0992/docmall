package com.docmall.service;

import java.util.List;

import com.docmall.domain.MemberVO;
import com.docmall.domain.SimpleMemberVO;
import com.docmall.dto.Criteria;

public interface AdmemberService {

	List<MemberVO> member_list(Criteria cri);
	
	int getTotalCount(Criteria cri);
	
	void member_delete(String mbsp_id);
	
	MemberVO member_edit(String mbsp_id);
	
	void member_edit(MemberVO vo);
	
	void member_insert(SimpleMemberVO vo);
	
}
