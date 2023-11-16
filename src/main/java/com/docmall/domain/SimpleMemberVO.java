package com.docmall.domain;

import lombok.Data;

@Data
public class SimpleMemberVO {
/*
 * CREATE TABLE SIMPLEMEMBER_TBL (
    SIMP_ID             VARCHAR2(15),
    SIMP_NAME           VARCHAR2(30)            NOT NULL,
    SIMP_EMAIL          VARCHAR2(50)            NOT NULL,
    SIMP_ADDR           VARCHAR2(100)            NOT NULL,
    SIMP_PHONE          VARCHAR2(15)            NOT NULL,
);
 */
	private String SIMP_ID;
	private String SIMP_NAME;
	private String SIMP_EMAIL;
	private String SIMP_ADDR;
	private String SIMP_PHONE;
}
