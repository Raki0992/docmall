package com.docmall.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docmall.domain.OrderDetailInfoVO;
import com.docmall.domain.OrderDetailProductVO;
import com.docmall.domain.OrderVO;
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
		public void order_list(Criteria cri,@ModelAttribute("start_date") String start_date, @ModelAttribute("end_date") String end_date, Model model) throws Exception {
			
			cri.setAmount(2);	// 10 -> 2 한페이지에 출력할 데이터 수 변경
			
			List<OrderVO> order_list = adOrderService.order_list(cri, start_date, end_date);
			model.addAttribute("order_list", order_list);
			
			int totalCount = adOrderService.getTotalCount(cri, start_date, end_date);
			model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
		} 
		
		// 상품리스트에서 보여줄 이미지. <img src="매핑주소">
		@ResponseBody
		@GetMapping("/imageDisplay")	//		/admin/product/imageDisplay?dateFolderName=값1&fileName=값2
		public ResponseEntity<byte[]> imageDisplay(String dateFolderName, String fileName) throws Exception {
			
			log.info("이미지");
			
			return FileUtils.getFile(uploadPath + dateFolderName, fileName); 
		}
		
		// 주문목록 핸들바(주문상세 방법1)	주문상세정보가 클라이언트 json형태로 변환되어 보내진다. pom.xml에 jackson-databind 라이브러리 백그라운드로 작동되어 json형태로 변환됨)
		@GetMapping("/order_detail_info1/{ord_code}")
		public ResponseEntity<List<OrderDetailInfoVO>> order_detail_list1(@PathVariable("ord_code") Long ord_code, Criteria cri) throws Exception {
			// 클래스명 = 주문상세 + 상품테이블 조인한 결과를 담는 클래스
			
			ResponseEntity<List<OrderDetailInfoVO>> entity = null;
			
			List<OrderDetailInfoVO> OrderDetailList =  adOrderService.OrderDetailInfo1(ord_code);
			
			// 브라우저에서 상품이미지 출력시 역슬래시 사용
			OrderDetailList.forEach(vo -> {
				vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/")); 
			});
			
			entity = new ResponseEntity<List<OrderDetailInfoVO>>(OrderDetailList, HttpStatus.OK);
			
			return entity;
		}
		
		// 주문상세내역에서 개별상품삭제
		@GetMapping("/order_product_delete")
		public String order_product_delete(Criteria cri, Long ord_code, Integer pro_num) throws Exception {

			// 주문상세 개별 삭제
			adOrderService.order_product_delete(ord_code, pro_num);
			
			return "redirect:/admin/order/order_list" + cri.getListLink();
		}
		
		// 주문상세 방법2	파일명 길어서 String return으로 교체
		@GetMapping("/order_detail_info2/{ord_code}")
		 public String order_detail_list2(@PathVariable("ord_code") Long ord_code, Criteria cri, Model model) throws Exception {
			
			List<OrderDetailProductVO> orderProductList = adOrderService.OrderDetailInfo2(ord_code);
			
			// 브라우저에서 상품이미지 출력시 역슬래시 사용
//			orderProductList.forEach(vo -> {
//							vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/")); 
//						});
			// 클래스안에 클래스가 존재하기때문에 타고 들어가서 적용
			orderProductList.forEach(vo -> {
				vo.getProductVO().setPro_up_folder(vo.getProductVO().getPro_up_folder().replace("\\", "/"));
			}); 
			
			
			model.addAttribute("orderProductList", orderProductList);
			return "/admin/order/order_detail_product";
		}
		
}










