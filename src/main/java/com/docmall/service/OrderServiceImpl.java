package com.docmall.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.docmall.domain.OrderVO;
import com.docmall.domain.PaymentVO;
import com.docmall.mapper.OrderMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

	private final OrderMapper ordermapper;
 
	@Override
	public int getOrderSeq() {
		return ordermapper.getOrderSeq();
	}

	@Transactional	// 트랜젝션 적용(중요)
	@Override
	public void order_insert(OrderVO o_vo, PaymentVO p_vo) {
		ordermapper.order_insert(o_vo); 
		ordermapper.order_detail_insert(o_vo.getOrd_code(), o_vo.getMbsp_id());
		ordermapper.cart_del(o_vo.getMbsp_id());
		ordermapper.payment_insert(p_vo);
	}
	
	
}












