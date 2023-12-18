package kr.or.ddit.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.filter.wrapper.MemberVOWrapper;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.validate.grouphint.DeleteGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class MemberDeleteController {
	private final MemberService service;
	
	@PostMapping(value = "/member/memberDelete.do")
	public String memberDelete(
			MemberVOWrapper principal
			, MemberVO inputData
			, @RequestParam("password") String password
			, RedirectAttributes redirectAttributes
			, HttpSession session
			){
		
		String memId = principal.getName();
		
//		2. 검증하기(검증대상 ID, PW)
		MemberVO inputdata = new MemberVO();
		inputdata.setMemId(memId);
		inputdata.setMemPass(password);
		
			
		String viewName = null;
			service.retrieveMember(memId);
			ServiceResult result = service.removeMember(inputdata);
			switch (result) {
			case INVALIDPASSWORD:
//					1) INVALIDPASSWORD 
//						mypage 으로 이동 (기존 입력 데이터, 메시지, dispatch)
				
				System.out.println("비밀 번호 오류");
						
				viewName = "redirect:/mypage";
				session.setAttribute("message", "비밀 번호 오류");	// flash attribute
				
				break;
			case OK:
//					2) OK 
//						welcome page로 이동 (redirect)
				viewName = "redirect:/";
		        session.invalidate();
				break;
			default:
//					3) FAIL
//						mypage 으로 이동 (기존 입력 데이터, 메시지, dispatch)
				viewName = "member/mypage";
				session.setAttribute("message", "서버 오류, 쫌따 다시 해보셈.");// flash attribute
				break;
			} 
			
		return viewName;
	
	}

}
