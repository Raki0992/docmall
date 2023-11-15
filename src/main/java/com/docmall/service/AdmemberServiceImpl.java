package com.docmall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.docmall.domain.MemberVO;
import com.docmall.dto.Criteria;
import com.docmall.mapper.AdmemberMapper;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class AdmemberServiceImpl implements AdmemberService {

	private final AdmemberMapper admemberMapper;

	@Override
	public List<MemberVO> member_list(Criteria cri) {
		return admemberMapper.member_list(cri);
	}

	@Override
	public void member_delete(String mbsp_id) {
		admemberMapper.member_delete(mbsp_id);
	}
	

	
}
