package com.docmall.domain;


// cg_code, cg_parent, cg_name
// @Data 사용 안하면
public class CategoryVO {

	private Integer cg_code;	// 모든 카테고리 코드
	private Integer cg_parent;	// 1차 카테고리 코드
	private String  cg_name;	 
	
	// @Getter, @Setter
	public Integer getCg_code() {
		return cg_code;
	}
	public void setCg_code(Integer cg_code) {
		this.cg_code = cg_code;
	}
	public Integer getCg_parent() {
		return cg_parent;
	}
	public void setCg_parent(Integer cg_parent) {
		this.cg_parent = cg_parent;
	}
	public String getCg_name() {
		return cg_name;
	}
	public void setCg_name(String cg_name) {
		this.cg_name = cg_name;
	}
	
	// @ToString
	@Override
	public String toString() {
		return "CategoryVO [cg_code=" + cg_code + ", cg_parent=" + cg_parent + ", cg_name=" + cg_name + "]";
	}
	
	
	
	
}
