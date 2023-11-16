package com.docmall.mapper;

import java.util.List;

import com.docmall.domain.MemberVO;
import com.docmall.domain.SimpleMemberVO;
import com.docmall.dto.Criteria;

public interface AdmemberMapper {

	List<MemberVO> member_list(Criteria cri);
	
	int getTotalCount(Criteria cri);
	
	void member_delete(String mbsp_id);
	
	MemberVO member_edit(String mbsp_id);
	
	void member_edit_ok(MemberVO vo);
	
	void member_insert(SimpleMemberVO vo);
}
