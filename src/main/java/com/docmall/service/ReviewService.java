package com.docmall.service;

import java.util.List;

import com.docmall.domain.ReviewVO;
import com.docmall.dto.Criteria;

public interface ReviewService {

	void review_insert(ReviewVO vo);
	
	List<ReviewVO> list(Integer pro_num, Criteria cri);	// 검색기능 사용안함
	
	int listCount(Integer pro_num);
	
	void delete(Long rew_num);
}
