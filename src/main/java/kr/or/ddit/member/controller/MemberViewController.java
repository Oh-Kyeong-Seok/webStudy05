package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberViewController {
	@Inject
	private MemberService service;
	
	@RequestMapping("/member/memberView.do")
	public String memberView(Model model, @RequestParam("who") String memId) throws ServletException, IOException {
		
		// 필수파라미터 획득
		MemberVO member = service.retrieveMember(memId);
		
		model.addAttribute("member", member);
		
		// viewName 설정
		return "member/ajax/memberView";
		
	
	}
	
}
