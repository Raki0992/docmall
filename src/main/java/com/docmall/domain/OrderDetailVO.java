package com.docmall.domain;

import lombok.Data;

@Data
public class OrderDetailVO {
	/*
	 * ORDETAIL_TBL(
        ORD_CODE        NUMBER      NOT NULL REFERENCES ORDER_TBL(ORD_CODE),
        PRO_NUM         NUMBER      NOT NULL REFERENCES PRODUCT_TBL(PRO_NUM),
        DT_AMOUNT       NUMBER      NOT NULL,
        DT_PRICE        NUMBER      NOT NULL,  -- 역정규화
        PRIMARY KEY (ORD_CODE ,PRO_NUM) 
);
	 */
	private Long ord_code;
	private Integer pro_num;
	private int dt_amount;
	private int dt_price;
}
