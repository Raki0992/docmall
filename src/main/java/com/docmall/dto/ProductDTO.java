package com.docmall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor	// 모든 필드를 대상으로 매개변수가 있는 생성자메소드 생성
public class ProductDTO {
	
	private Integer pro_num;
	
	private int 	pro_price;
	
	private String  pro_buy;
}
