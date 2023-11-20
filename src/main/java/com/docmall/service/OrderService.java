package com.docmall.service;

import com.docmall.domain.OrderVO;
import com.docmall.domain.PaymentVO;

public interface OrderService {
	
	int getOrderSeq();
	
	void order_insert(OrderVO o_vo, PaymentVO p_vo); // 주문테이블 저장
}
