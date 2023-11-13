package com.docmall.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.domain.AdminVO;
import com.docmall.domain.MemberVO;
import com.docmall.dto.AdminLoginDTO;
import com.docmall.dto.Criteria;
import com.docmall.service.AdminService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@Log4j	// log.info("");
@RequestMapping("/admin/*")
@Controller	// 클라이언트의 요청을 담당하는 기능.	bean으로 생성 및 등록 : adminController
public class AdminController {

	private final AdminService adminService;
	
	private final PasswordEncoder passwordEncoder; 
	
	// 관리자 로그인 폼 페이지
	@GetMapping("intro")
	public String adminLogin() {
		log.info("관리자 로그인 페이지");
		
		return "/admin/adLogin";
	}
		
	// 관리자 로그인 인증		MemberController에 login 참조
	@PostMapping("/admin_ok")
	public String admin_ok(AdminVO vo, HttpSession session, RedirectAttributes rttr, AdminLoginDTO dto) throws Exception {
	
		log.info("관리자 로그인 : " + vo);
	
	AdminVO db_vo = adminService.admin_ok(vo.getAdmin_id());
	
	String url = "";
	String msg = "";
	
	// 아이디가 일치하면	 	adLogin.jsp와 msg일치시키기
	if(db_vo != null) {
		// 사용자가 입력한 비밀번호(평문텍스트)와 db에서 가져온 암호화된 비밀번호 일치여부 검사
		if(passwordEncoder.matches(vo.getAdmin_pw(), db_vo.getAdmin_pw()))	{
			// 로그인 성공결과로 서버측의 메모리를 사용하는 세션형태작업
			session.setAttribute("adminStatus", db_vo);
			
			// 로그인 시간 업데이트
			adminService.loginTimeUpdate(vo.getAdmin_id());
			
			url = "/admin/admin_menu";	// 관리자 메뉴페이지 주소
	}else {
		url = "/admin/intro";	// 로그인 폼 주소
		msg = "failPW"; 
		rttr.addFlashAttribute("msg", msg); // 로그인 폼 jsp파일에서 사용목적
	}
}else {
	// 아이디가 일치하지 않으면
	url = "/admin/intro";	// 로그인 폼 주소
	msg = "failID";
	rttr.addFlashAttribute("msg", msg);	// 로그인 폼 jsp파일에서 사용목적
}

	
	return "redirect:" + url;
}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/admin/intro";	// 로그인페이지 주소
	}
	
	// 관리자 메뉴 페이지
	@GetMapping("/admin_menu")
	public void admin_menu() {
		
	}
	
	// 회원관리 페이지
	@GetMapping("/member_list")
	public void member_list(MemberVO vo, Criteria cri, Model model) throws Exception {
		log.info("회원관리");
		
		List<MemberVO> member_list = adminService.member_list(cri);
		
		model.addAttribute("member_list", member_list);
		
		
	}
	
	
	
}
