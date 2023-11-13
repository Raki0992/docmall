package com.docmall.domain;

import lombok.Data;

@Data
public class CartVO {
/*
 * 		CART_CODE        NUMBER,
        PRO_NUM         NUMBER          NOT NULL,
        MBSP_ID         VARCHAR2(15)    NOT NULL,
        CART_AMOUNT      NUMBER          NOT NULL,
 */
	
	private Long cart_code;
	
	private Integer pro_num;
	private String mbsp_id;
	
	private int cart_amount;
	
}
