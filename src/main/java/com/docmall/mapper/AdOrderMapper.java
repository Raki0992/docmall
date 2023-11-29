package com.docmall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.docmall.domain.OrderDetailInfoVO;
import com.docmall.domain.OrderDetailProductVO;
import com.docmall.domain.OrderVO;
import com.docmall.dto.Criteria;

public interface AdOrderMapper {

	List<OrderVO> order_list(Criteria cri);
	
	int getTotalCount(Criteria cri);
	
	List<OrderDetailInfoVO> OrderDetailInfo1(Long ord_code);
	
	// 기존클래스 2개 합쳐서 사용(OrderDetailVO, ProductVO)필드 사용 	mybatis의  resultMap 사용
	List<OrderDetailProductVO> OrderDetailInfo2(Long ord_code);
	
	void order_product_delete(@Param("ord_code") Long ord_code, @Param("pro_num") Integer pro_num);
}
