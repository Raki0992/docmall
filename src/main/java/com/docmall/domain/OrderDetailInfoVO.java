package com.docmall.domain;

import lombok.Data;

// 관리자기능
// 주문상세정보 저장목적
// OT.ord_code, OT.PRO_NUM, OT.DT_AMOUNT, 
// P.pro_num, P.PRO_NAME, P.PRO_PRICE, P.PRO_UP_FOLDER, P.PRO_IMG

@Data
public class OrderDetailInfoVO {

	private Long 	ord_code;
	private Integer pro_num;
	private int 	dt_amount;
	private String  pro_name;
	private int 	pro_price;
	
	private int 	ord_price;	// 주문금액(pro_price * dt_amount)	 OT.DT_AMOUNT * P.PRO_PRICE as ORD_PRICE
	
	private String	pro_up_folder;	// 스프링에서 별도로 처리
	private String	pro_img;	// 스프링에서 별도로 처리
	
}
