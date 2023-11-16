package com.docmall.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.domain.MemberVO;
import com.docmall.domain.SimpleMemberVO;
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
		
		// 상품수정 페이지
		@GetMapping("/member_edit")
		public void member_edit(@ModelAttribute("cri") Criteria cri, String mbsp_id, Model model) throws Exception {
			
			MemberVO memberVO = admemberService.member_edit(mbsp_id);	// 선택한 상품 정보
			
			model.addAttribute("MemberVO", memberVO);
			
		}
		
		// 회원수정
		@PostMapping("/member_edit")
		public String member_edit(Criteria cri, MemberVO vo, RedirectAttributes rttr) throws Exception {
			
			admemberService.member_edit(vo);
			
			return "redirect:/admin/member/member_edit";
		}
		
		
		// 회원등록 폼
		@GetMapping("member_insert")
		public void member_insert() {
			
		}
		
		@PostMapping("/member_insert")
		public String member_insert(SimpleMemberVO vo, RedirectAttributes rttr) throws Exception {
			
			log.info("회원정보" + vo);
			
			admemberService.member_insert(vo);
			
			
		return "redirect:/admin/member/member_list";
		}
}
