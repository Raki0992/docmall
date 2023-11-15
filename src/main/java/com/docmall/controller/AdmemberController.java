package com.docmall.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.domain.MemberVO;
import com.docmall.dto.Criteria;
import com.docmall.dto.PageDTO;
import com.docmall.service.AdProductService;
import com.docmall.service.AdmemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@Log4j
@RequestMapping("/admin/member/*")
@Controller
public class AdmemberController {

	private final AdmemberService admemberService;
	
	private final AdProductService adProductService;
	
	// 회원목록 페이지
		@GetMapping("/member_list")
		public void member_list(Criteria cri, Model model) throws Exception {
			log.info("회원관리");
			// 출력할 데이터 수 변경안하면 10	 (Criteria)
			cri.setAmount(2);
			
			List<MemberVO> member_list = admemberService.member_list(cri);
			
			model.addAttribute("member_list", member_list);
			
			int totalCount = adProductService.getTotalCount(cri);
			model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
			
		}
		
		// 회원삭제
		@PostMapping("/member_delete")
		public String member_delete(Criteria cri, String mbsp_id) throws Exception {
			
			admemberService.member_delete(mbsp_id);
			
			return "redirect:/admin/member_list" + cri.getListLink();
		}
}
