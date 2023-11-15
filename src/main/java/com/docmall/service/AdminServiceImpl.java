package com.docmall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.docmall.domain.AdminVO;
import com.docmall.domain.MemberVO;
import com.docmall.dto.Criteria;
import com.docmall.mapper.AdminMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service	// bean 생성 및 등록 : adminServiceImpl
public class AdminServiceImpl implements AdminService {
	
	private final AdminMapper adminMapper;

	@Override
	public AdminVO admin_ok(String admin_id) {
		return adminMapper.admin_ok(admin_id);
	}

	@Override
	public void loginTimeUpdate(String admin_id) {
		adminMapper.loginTimeUpdate(admin_id);
	}


//	public AdminServiceImpl(AdminMapper adminMapper) {
//		this.adminMapper = adminMapper;
//	}
	
	
	
}
