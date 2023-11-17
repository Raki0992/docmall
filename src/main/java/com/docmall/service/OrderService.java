package com.docmall.service;

import com.docmall.domain.OrderVO;

public interface OrderService {
	
	int getOrderSeq();
	
	void order_insert(OrderVO o_vo); // 주문테이블 저장
}
