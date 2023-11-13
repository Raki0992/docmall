package com.docmall.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docmall.domain.ProductVO;
import com.docmall.dto.Criteria;
import com.docmall.dto.PageDTO;
import com.docmall.service.UserProductService;
import com.docmall.util.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequiredArgsConstructor
@RequestMapping("/user/product")
@Controller
public class UserProductController {
	private final UserProductService userProductService;
	
	// 메인 및 썸네일 이밎업로드 폴더경로 주입작업
		@Resource(name = "uploadPath")	// servlet-context.xml의 bean이름 참조
		private String uploadPath;
	
	// 매핑주소1: /user/product/pro_list?cg_code=2차카테고리코드
	/*
	@GetMapping("/pro_list")
	public void pro_list(Criteria cri, @RequestParam("cg_code") Integer cg_code) throws Exception {
	} */
	
	// 매핑주소2: /user/product/pro_list
	@GetMapping("/pro_list")
	public String pro_list(Criteria cri,@ModelAttribute("cg_code") Integer cg_code,@ModelAttribute("cg_name") String cg_name, Model model) throws Exception {
		
		cri.setAmount(4);	// 10 -> 8 한페이지에 출력할 데이터 수 변경
		
		List<ProductVO> pro_list = userProductService.pro_list(cg_code, cri);
		
		// 날짜폴더의 역슬래시를 슬래시로 바꾸는 작업.	이유? 역슬래시로 되어있는 정보가 스프링으로 보내는 요청데이터에 사용되면 에러발생.
		// 스프링에서 처리 안하면, 자바스크립트에서 처리 할 수도 있다.
		pro_list.forEach(vo-> {
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/")); 
		});
		model.addAttribute("pro_list", pro_list);	// 목록
		
		int totalCount = userProductService.getTotalCount(cg_code);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount));	// 페이징 정보
		
		return "/user/product/pro_list";
	}
	
	// 상품리스트에서 보여줄 이미지. <img src="매핑주소">
		@ResponseBody
		@GetMapping("/imageDisplay")	//		/admin/product/imageDisplay?dateFolderName=값1&fileName=값2
		public ResponseEntity<byte[]> imageDisplay(String dateFolderName, String fileName) throws Exception {
			
			log.info("이미지");
			
			return FileUtils.getFile(uploadPath + dateFolderName, fileName); 
		}
	
	
	
}
















