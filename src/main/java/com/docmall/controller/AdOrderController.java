package com.docmall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docmall.domain.OrderDetailVO;
import com.docmall.domain.OrderVO;
import com.docmall.domain.ReviewVO;
import com.docmall.dto.Criteria;
import com.docmall.dto.PageDTO;
import com.docmall.service.AdOrderService;
import com.docmall.util.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequiredArgsConstructor
@RequestMapping("/admin/order/*")
@Controller
public class AdOrderController {
	
	// 메인 및 썸네일 이밎업로드 폴더경로 주입작업
		@Resource(name = "uploadPath")	// servlet-context.xml의 bean이름 참조
		private String uploadPath;
	
	private final AdOrderService adOrderService;

	// 상품리스트	(목록과 페이지)
		@GetMapping("/order_list")
		public void order_list(Criteria cri, Model model) throws Exception {
			
			cri.setAmount(2);	// 10 -> 2 한페이지에 출력할 데이터 수 변경
			
			List<OrderVO> order_list = adOrderService.order_list(cri);
			model.addAttribute("order_list", order_list);
			
			int totalCount = adOrderService.getTotalCount(cri);
			model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
		}
		
		// 상품리스트에서 보여줄 이미지. <img src="매핑주소">
		@ResponseBody
		@GetMapping("/imageDisplay")	//		/admin/product/imageDisplay?dateFolderName=값1&fileName=값2
		public ResponseEntity<byte[]> imageDisplay(String dateFolderName, String fileName) throws Exception {
			
			log.info("이미지");
			
			return FileUtils.getFile(uploadPath + dateFolderName, fileName); 
		}
		
		// 주문목록 핸들바
		@GetMapping("/order_detail_info/{ord_code}")
		public ResponseEntity<List<OrderDetailVO>> list(@PathVariable("ord_code") Integer ord_code, @PathVariable("page") Integer page) throws Exception {
			// 클래스명 = 주문상세 + 상품테이블 조인한 결과를 담는 클래스
			
			ResponseEntity<List<OrderDetailVO>> entity = null;
			
			return entity;
		}
		
		
		
		
		
		
}










