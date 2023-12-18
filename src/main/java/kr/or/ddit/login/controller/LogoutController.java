package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {
	
	@RequestMapping(value = "/login/logout.do", method = RequestMethod.POST)
	public String logoutProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session  = req.getSession(false);	// 최초요청이거나 tracking mode가 없거나
		
		// 검증작업
		if(session==null || session.isNew()) { //정상적인 로그아웃 user가 아님
			resp.sendError(400,"로그인 하지도 않았는뒈!!!");
			return null;
		}
			
		// authId 지우고 
		// session.removeAttribute("authId");
		
		// 세션만료
		session.invalidate();
		
		// 웰컴페이지 이동(리다이렉트로 이동)
		return"redirect:/";
	
	}
}
