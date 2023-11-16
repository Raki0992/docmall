package com.docmall.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.docmall.kakaopay.ApproveResponse;
import com.docmall.kakaopay.ReadyResponse;

// 카카오페이 API서버에 실제요청하는 작업
/*
 * HttpURLConnection 을 이용한 HTTP통신방법
 * RestTemplate를 이용한 HTTP통신방법(스프링에서 권장)  https://minkwon4.tistory.com/178
 * RestTemplate은 Spring 3.0 부터 지원하는 템플릿으로 Spring에서 HTTP 통신을 RESTful 형식에 맞게 손쉬운 사용을 제공해주는 템플릿이다. 
 */

@Service
public class KakaoPayServiceImpl {
	
	// HttpHeaders 클래스 : 헤더정보를 구성할 때 사용
/*	2) 헤더정보
* Authorization: KakaoAK ${SERVICE_APP_ADMIN_KEY}
* Content-type: application/x-www-form-urlencoded;charset=utf-8
*/
	
	private HttpHeaders getHeaderInfo() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "KakaoAK ae96154bef11e6a6bce8463b7f6c8545");
		headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		return headers;
	}
	
/*
 * 결제 준비하기 - 첫번째 요청
 * 1) 요청주소 : https://kapi.kakao.com/v1/payment/ready
 * POST : v1/payment/ready
 * Host : kapi.kakao.com
 * 2) 헤더정보
 * Authorization: KakaoAK ${SERVICE_APP_ADMIN_KEY}
 * Content-type: application/x-www-form-urlencoded;charset=utf-8
 */
	public ReadyResponse payReady(Long ord_code, String itemName, int quantity, String mbsp_id, int totalAmount) {
		
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		
		parameters.add("cid", "TC0ONETIME"); // 가맹점 코드, 10자	servlet-context.xml에 upload폴더처럼 관리
		parameters.add("partner_order_id", String.valueOf(ord_code)); // 가맹점 주문번호(쇼핑몰 상품주문번호), 최대 100자
		parameters.add("partner_user_id", mbsp_id); // 가맹점 회원 id, 최대 100자
		parameters.add("item_name", itemName); // 상품명, 최대 100자	ex) A상품외 2건
		parameters.add("quantity", String.valueOf(quantity)); // 상품 수량
		parameters.add("total_amount", String.valueOf(totalAmount)); // 상품 총액
		parameters.add("tax_free_amount", "0"); // 상품 비과세 금액
		
		parameters.add("approval_url", "http://localhost:9090/user/order/orderApproval"); // 결제 성공 시 redirect url, 최대 255자	servlet-context.xml에 upload폴더처럼 관리
		parameters.add("cancel_url", "http://localhost:9090/user/order/orderCancel"); // 결제 취소 시 redirect url, 최대 255자		servlet-context.xml에 upload폴더처럼 관리
		parameters.add("fail_url", "http://localhost:9090/user/order/orderFail"); // 결제 실패 시 redirect url, 최대 255자		servlet-context.xml에 upload폴더처럼 관리
		
		// https://jung-story.tistory.com/132
		
		// 헤더와 파라미터정보를 구성하는 작업
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String,String>>(parameters, this.getHeaderInfo());
		// kakao API 서버와 통신
		RestTemplate template = new RestTemplate();
		
		String kakaoReadyUrl = "https://kapi.kakao.com/v1/payment/ready"; 
		// 응답받은 데이터
		ReadyResponse readyResponse = template.postForObject(kakaoReadyUrl, requestEntity, ReadyResponse.class);
		
		return readyResponse;
	}
	
	/*
	 * 2) 결제 요청하기 - 두번째 요청
	 * 요청주소 : https://kapi.kakao.com/v1/payment/approve
	 * POST : v1/payment/approve
	 * Host : kapi.kakao.com
	 * 2) 헤더정보
	 * Authorization: KakaoAK ${SERVICE_APP_ADMIN_KEY}
	 * Content-type: application/x-www-form-urlencoded;charset=utf-8
	 */
	
	public ApproveResponse payApprove(Long ord_code, String tid, String pgToken, String mbsp_id) {
		
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		
		parameters.add("cid", "TC0ONETIME"); // 가맹점 코드, 10자
		parameters.add("tid", tid); // 결제 고유번호, 결제 준비 API 응답에 포함
		parameters.add("partner_order_id", String.valueOf(ord_code)); // 가맹점 주문번호, 결제 준비 API 요청과 일치해야 함
		parameters.add("partner_user_id", mbsp_id); // 가맹점 회원 id, 결제 준비 API 요청과 일치해야 함
		parameters.add("pg_token", pgToken); // 결제승인 요청을 인증하는 토큰 사용자 결제 수단 선택 완료 시, approval_url로 redirection해줄 때 pg_token을 query string으로 전달
		
		// 헤더와 파라미터정보를 구성하는 작업
				HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String,String>>(parameters, this.getHeaderInfo());
				// kakao API 서버와 통신
				RestTemplate template = new RestTemplate();
				
				String kakaoApproveUrl = "https://kapi.kakao.com/v1/payment/approve"; 
				// 응답받은 데이터
				ApproveResponse approveResponse = template.postForObject(kakaoApproveUrl, requestEntity, ApproveResponse.class);
				
				return approveResponse;
	} 
	
	
	
	
	
	
	
	
}












