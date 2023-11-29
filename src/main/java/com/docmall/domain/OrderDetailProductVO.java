package com.docmall.domain;

import lombok.Data;

@Data
public class OrderDetailProductVO {

	// 기존클래스를 필드로 사용 	mybatis에서는 resultMap 사용해야 한다.
	private OrderDetailVO orderDetailVO;	// <collection> 으로 표현	
	private ProductVO productVO;	// <collection> 으로 표현		
	
	//<collection property="orderDetailVO" resultMap="orderDetailMap"></collection>
	//<collection property="productVO" resultMap="productMap"></collection>
/*	
	<resultMap type="com.docmall.domain.orderDetailVO" id="orderDetailMap">
	<id property="ord_code" column="ORD_CODE"/>
	<id property="pro_num" column="PRO_NUM"/>
	<result property="dt_amount" column="DT_AMOUNT"/>
	<result property="dt_price" column="DT_PRICE"/>
	</resultMap>
	
	<resultMap type="com.docmall.domain.productVO" id="productMap">
	<id property="pro_num" column="PRO_NUM"/>
	<result property="cg_code" column="CG_CODE"/>
	<result property="pro_name" column="PRO_NAME"/>
	<result property="pro_price" column="PRO_PRICE"/>
	<result property="pro_discount" column="PRO_DISCOUNT"/>
	<result property="pro_publisher" column="PRO_PUBLISHER"/>
	<result property="pro_content" column="PRO_CONTENT"/>
	<result property="pro_up_folder" column="PRO_UP_FOLDER"/>
	<result property="pro_img" column="PRO_IMG"/>
	<result property="pro_amount" column="PRO_AMOUNT"/>
	<result property="pro_buy" column="PRO_BUY"/>
	<result property="pro_date" column="PRO_DATE"/>
	<result property="pro_updatedate" column="PRO_UPDATEDATE"/>
	</resultMap>
*/
}
