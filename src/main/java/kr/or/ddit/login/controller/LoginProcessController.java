package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.login.service.AuthenticateServiceImpl;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginProcessController {
	private final AuthenticateService service;
		
	@RequestMapping(value = "/login/loginProcess.do", method = RequestMethod.POST)
	public String loginProcess(
			@RequestParam("memId") String memId
			,@RequestParam("memPass") String memPass
			, HttpSession session
			) throws IOException{
		// 1. 아이디나 비밀번호가 누락된 경우, Bad Request 전송
		// 2. 인증 성공 : 웰컴 페이지로 이동 → "a001"님 로그인 성공이라는 메세지를 웰켐페이지에 alert창으로 출력.
		// 3. 인증 실패 : 로그인 폼으로 이동 → "아이디나 비밀번호 오류"라는 메세지를 로그인 폼에서 alert창으로 출력
		
		MemberVO inputData = new MemberVO();
		inputData.setMemId(memId);
		inputData.setMemPass(memPass);

		String viewName="";

//		4-1. 검증 통과
//			5-1. 인증 여부 판단
			ServiceResult result = service.authenticate(inputData);
			
			switch (result) {
			case OK:
//				6-1. 인증 성공
//				- 웰컴 페이지 이동
				viewName="redirect:/";
				session.setAttribute("authMember", inputData); //session안에도 맵이 존재한다.
				break;
			case INVALIDPASSWORD:	//비번오류
//				6-2. 인증 실패
//					- loginForm으로 이동
				viewName="redirect:/login/loginForm.jsp";
				session.setAttribute("message", "비밀번호 오류");
				
				break;

			default:	// 그런사람없음여
				viewName="redirect:/login/loginForm.jsp";
				session.setAttribute("message", "아직 가입하지 않았거나 이미 탈퇴한 회원");
				break;
			}
		 
		return viewName;
	}
}
