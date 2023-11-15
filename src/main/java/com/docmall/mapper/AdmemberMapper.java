package com.docmall.mapper;

import java.util.List;

import com.docmall.domain.MemberVO;
import com.docmall.dto.Criteria;

public interface AdmemberMapper {

	List<MemberVO> member_list(Criteria cri);
	
	void member_delete(String mbsp_id);
}
