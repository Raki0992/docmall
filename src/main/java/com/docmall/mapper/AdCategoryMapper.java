package com.docmall.mapper;

import java.util.List;

import com.docmall.domain.CategoryVO;

public interface AdCategoryMapper {

	List<CategoryVO> getFirstCategoryList();
	
	// 1차 카테고리 받아오기
	List<CategoryVO> getSecondCategoryList(Integer cg_parent_code);
}
 