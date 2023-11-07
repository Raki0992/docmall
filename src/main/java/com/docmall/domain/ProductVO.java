package com.docmall.domain;

import java.util.Date;

import lombok.Data;

/*
 * CREATE TABLE PRODUCT_TBL(
        PRO_NUM             NUMBER  CONSTRAINT  PK_PRO_NUM         PRIMARY KEY,
        CG_CODE            NUMBER            NULL,
        PRO_NAME            VARCHAR2(50)            NOT NULL,
        PRO_PRICE           NUMBER                  NOT NULL,
        PRO_DISCOUNT        NUMBER                  NOT NULL,
        PRO_PUBLISHER       VARCHAR2(50)            NOT NULL,
        PRO_CONTENT         VARCHAR2(4000)  /* CLOB                   NOT NULL,       -- 내용이 4000BYTE 초과여부판단?
        PRO_UP_FOLDER       VARCHAR2(50)             NOT NULL,
        PRO_IMG             VARCHAR2(50)             NOT NULL,  -- 날짜폴더경로가 포함하여 파일이름저장
        PRO_AMOUNT          NUMBER                  NOT NULL,
        PRO_BUY             CHAR(1)                 NOT NULL,
        PRO_DATE            DATE DEFAULT SYSDATE    NOT NULL,
        PRO_UPDATEDATE      DATE DEFAULT SYSDATE    NOT NULL,
        FOREIGN KEY(CG_CODE) REFERENCES CATEGORY_TBL(CG_CODE)
        );

pro_num, cg_code, pro_name, pro_price, pro_discount, pro_publisher, pro_content, pro_up_folder, pro_img, pro_amount, pro_buy, pro_date, pro_updatedate
 */
@Data
public class ProductVO {

	private Integer pro_num;
	
	private Integer cg_code;	// 2차 카테고리 코드
	private String  pro_name;
	private int 	pro_price;
	private int 	pro_discount;
	private String 	pro_publisher;
	private String	pro_content;
	
	private String	pro_up_folder;	// 스프링에서 별도로 처리
	private String	pro_img;	// 스프링에서 별도로 처리
	
	private int 	pro_amount;
	private String  pro_buy;
	
	private Date	pro_date;
	private Date	pro_updatedate;
	
	// private MultipartFile uploadFile
}














