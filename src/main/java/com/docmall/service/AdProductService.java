package com.docmall.service;

import org.springframework.stereotype.Service;

import com.docmall.domain.ProductVO;

@Service
public interface AdProductService {

	void pro_insert(ProductVO vo);
}
