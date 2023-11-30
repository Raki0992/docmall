package com.docmall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.docmall.domain.OrderDetailInfoVO;
import com.docmall.domain.OrderDetailProductVO;
import com.docmall.domain.OrderVO;
import com.docmall.dto.Criteria;
import com.docmall.mapper.AdOrderMapper;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class AdOrderServiceImpl implements AdOrderService {
	
	private final AdOrderMapper adOrderMapper;

	@Override
	public List<OrderVO> order_list(Criteria cri, String start_date, String end_date) {
		return adOrderMapper.order_list(cri, start_date, end_date); 
	}

	@Override
	public int getTotalCount(Criteria cri, String start_date, String end_date) {
		return adOrderMapper.getTotalCount(cri, start_date, end_date);
	}

	@Override
	public List<OrderDetailInfoVO> OrderDetailInfo1(Long ord_code) {
		return adOrderMapper.OrderDetailInfo1(ord_code);
	}

	@Override
	public void order_product_delete(Long ord_code, Integer pro_num) {
		adOrderMapper.order_product_delete(ord_code, pro_num);
	}

	@Override
	public List<OrderDetailProductVO> OrderDetailInfo2(Long ord_code) {
		return adOrderMapper.OrderDetailInfo2(ord_code);
	}



	
}
