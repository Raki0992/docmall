package com.docmall.controller;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.domain.MemberVO;
import com.docmall.dto.LoginDTO;
import com.docmall.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor	// final 필드만 매개변수가 있는 생성자를 만들어주고(@RequiredArgsConstructor), 스프링에 의하여 생성자를 주입받는다
@Log4j
@RequestMapping("/member/*")
@Controller
public class MemberController {
	private final MemberService memberService;
	
	// 생성자를 통하여 주입받는 필드에 인터페이스를 사용하는 이유? 유지보수
	private final PasswordEncoder passwordEncoder; 
	
	@GetMapping("/join")	// 생성후 member폴더에 join.jsp생성(index.jsp복사)
	public void join() {	// join있는 위치(header.jsp에 주소 입력)
		
		log.info("called... join");
	}  
	
	// 비동기방식. ajax문법으로 호출
	// 아이디 중복체크
	// ResponseEntity클래스 : http entity를 상속받는, 결과 데이터와 HTTP 상태코드를 직접 제어
	// 3가지 구성요소 - HttpStatus, HttpHeaders, HttpBody : idUse
	// ajax기능과 함께 사용
	@GetMapping("/idCheck")
	public ResponseEntity<String> idCheck(String mbsp_id) throws Exception {
		
		log.info("아이디 : " + mbsp_id);
		
		ResponseEntity<String> entity = null;
		
		// 서비스 메소드 호출구문작업.
		String idUse = "";
		if(memberService.idCheck(mbsp_id) != null) {
			idUse = "no";	// 아이디가 존재하여, 사용 불가능
		}else {
			idUse = "yes";	// 아이디가 존재하지 않아, 사용 가능
		}
												// Enumeration of HTTP status codes. 
		entity = new ResponseEntity<String>(idUse, HttpStatus.OK);
		
		return entity;
	}
	
	// 회원정보 저장 -> 다른주소이동(redirect)	redirect시 String 사용
	@PostMapping("/join")
	public String join(MemberVO vo, RedirectAttributes rttr) throws Exception {
		
		log.info("회원정보 : " + vo);
		
		vo.setMbsp_password(passwordEncoder.encode(vo.getMbsp_password()));
		
		log.info("암호화비밀번호 : " + vo.getMbsp_password());
		
		// db저장
		memberService.join(vo);
		return "redirect:/member/login";	// redirect 주소로 인식
	} 
	
	// 로그인 폼 페이지
	@GetMapping("/login")
	public void login() {
		
	}
	
	// 1) 로그인 인증확인  -> 메인페이지(/) 주소이동.	2) 로그인 인증실패 -> 로그인 폼 주소로 이동
	// String mbsp_id, String mbsp_password 파라미터로 사용
	@PostMapping("/login")
	public String login(LoginDTO dto, RedirectAttributes rttr, HttpSession session) throws Exception {
		
		log.info("로그인 : " + dto);
		
		MemberVO db_vo = memberService.login(dto.getMbsp_id());
		
		String url = "";
		String msg = "";
		
		// 아이디가 일치하면	 
		if(db_vo != null) {
			// 사용자가 입력한 비밀번호(평문텍스트)와 db에서 가져온 암호화된 비밀번호 일치여부 검사
			if(passwordEncoder.matches(dto.getMbsp_password(), db_vo.getMbsp_password()))	{
				// 로그인 성공결과로 서버측의 메모리를 사용하는 세션형태작업
				session.setAttribute("loginStatus", db_vo);
				
				// 로그인 시간 업데이트
				memberService.loginTimeUpdate(dto.getMbsp_id());
				url = "/";	// 메인페이지 주소
		}else {
			url = "/member/login";	// 로그인 폼 주소
			msg = "비밀번호가 일치하지 않습니다."; 
			rttr.addFlashAttribute("msg", msg); // 로그인 폼 jsp파일에서 사용목적
			
		}
	}else {
		// 아이디가 일치하지 않으면
		url = "/member/login";	// 로그인 폼 주소
		msg = "아이디가 존재하지 않습니다.";
		rttr.addFlashAttribute("msg", msg);	// 로그인 폼 jsp파일에서 사용목적
	}
	
		
		return "redirect:" + url;
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	// 회원수정페이지로 이동전 인증 확인 폼
	@GetMapping("/confirmPw")
	public void confirmPw() {
		log.info("회원수정 전 confirm 확인");
	}
 	
	// 회원수정페이지로 이동전 비밀번호 확인(로그인폼 사용해서 수정)
	@PostMapping("/confirmPw")
	public String confirmPw(LoginDTO dto, RedirectAttributes rttr) throws Exception {
		
		log.info("회원수정전 인증확인 : " + dto);
		
		MemberVO db_vo = memberService.login(dto.getMbsp_id());
		
		String url = "";
		String msg = "";
		
		// 아이디가 일치하면	 
		if(db_vo != null) {
			// 사용자가 입력한 비밀번호(평문텍스트)와 db에서 가져온 암호화된 비밀번호 일치여부 검사
			if(passwordEncoder.matches(dto.getMbsp_password(), db_vo.getMbsp_password()))	{
				url = "/member/modify";	// 회원수정폼 주소
		}else {
			url = "/member/confirmPw";	// 비밀번호확인(confirmPw) 폼 주소
			msg = "비밀번호가 일치하지 않습니다."; 
			rttr.addFlashAttribute("msg", msg); // 로그인 폼 jsp파일에서 사용목적
			
		}
	}else {
		// 아이디가 일치하지 않으면
		url = "/member/confirmPw";	// 로그인 폼 주소
		msg = "아이디가 존재하지 않습니다.";
		rttr.addFlashAttribute("msg", msg);	// 로그인 폼 jsp파일에서 사용목적
	}
		
		return "redirect:" + url;
	}
	
	// 회원수정 폼 : 인증 사용자의 회원가입정보를 뷰(View)에 출력
	@GetMapping("/modify")
	public void modify(HttpSession session, Model model) throws Exception {	// Model model 사용해서 jsp파일에 보여주기 가능
		String mbsp_id = ((MemberVO) session.getAttribute("loginStatus")).getMbsp_id();	// MemberVO에 있는 mbsp_id 가져오기
		
		MemberVO db_vo = memberService.login(mbsp_id);
		model.addAttribute("memberVO", db_vo);
		
	}
	
	@PostMapping("/modify")
	public String modify(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		log.info("정보수정 :" + vo);
		
		// 로그인시 인증목적으로 세션작업을 한 정보에서 아이디를 받아온다.
		MemberVO db_vo = (MemberVO) session.getAttribute("loginStatus");
		
		String mbsp_id = db_vo.getMbsp_id();
		
		vo.setMbsp_id(mbsp_id);
		
		memberService.modify(vo);
		
		// header.jsp에서 전자우편이 수정된 내용으로 반영이 안되기 때문
		db_vo.setMbsp_email(vo.getMbsp_email());	// 수정후, 반영시키기
		session.setAttribute("loginStatus", db_vo);
		
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/";
	}
	
	// 마이페이지
	@GetMapping("/mypage")
	public void mypage(HttpSession session, Model model) throws Exception {
		
		String mbsp_id = ((MemberVO) session.getAttribute("loginStatus")).getMbsp_id();
	      
	      
	      MemberVO db_vo = memberService.login(mbsp_id);
	      model.addAttribute("memberVO", db_vo);
	}
	
	// 회원탈퇴 폼
	@GetMapping("/delConfirmPw")
	public void delConfirmPw() {
		
	}
	
	// 회원탈퇴
	@PostMapping("/delete")
	public String delete(LoginDTO dto, RedirectAttributes rttr, HttpSession session) throws Exception {
		
		MemberVO db_vo = memberService.login(dto.getMbsp_id());
		
		String url = "";
		String msg = "";
		
		// 아이디가 일치하면	 
		if(db_vo != null) {
			// 사용자가 입력한 비밀번호(평문텍스트)와 db에서 가져온 암호화된 비밀번호 일치여부 검사
			if(passwordEncoder.matches(dto.getMbsp_password(), db_vo.getMbsp_password()))	{
				url = "/";	// 메인페이지 주소
				session.invalidate();// 세션소멸

				// 회원탈퇴
				memberService.delete(dto.getMbsp_id());
				
		}else {
			url = "/member/delConfirmPw";	// 비밀번호 확인(confrim) 폼 주소
			msg = "비밀번호가 일치하지 않습니다."; 
			rttr.addFlashAttribute("msg", msg); // 로그인 폼 jsp파일에서 사용목적
			
		}
	}else {
		// 아이디가 일치하지 않으면
		url = "/member/delConfirmPw";	// 로그인 폼 주소
		msg = "아이디가 존재하지 않습니다.";
		rttr.addFlashAttribute("msg", msg);	// 로그인 폼 jsp파일에서 사용목적
	}
		return "redirect:/";
	}
	
	
	
}

















