package kr.or.ddit.member.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberUpdateController {
	@Inject
	private MemberService service;
	
	@RequestMapping("/member/memberUpdate.do")
	public String updateForm(HttpServletRequest req, Principal principal){
		// 모델생성
		String memId = principal.getName();
		
		MemberVO member = service.retrieveMember(memId);
		
		req.setAttribute("member", member);
		
		// view를 선택하고 이동
		return "member/memberForm";
	}
	
	@RequestMapping(value = "/member/memberUpdate.do", method = RequestMethod.POST)
	public String updateProcess(
			HttpServletRequest req
			, @Validated(UpdateGroup.class) @ModelAttribute("member") MemberVO member
			, Errors errors
			) throws IOException {
		
		boolean valid = !errors.hasErrors();
		
		String viewName = null;
		if(valid) {
//			통과
//				4. modifyMember 수정 처리
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
//					1) INVALIDPASSWORD 
//						memberInsert 으로 이동 (기존 입력 데이터, 메시지, dispatch)
				req.setAttribute("message", "비밀번호 오류");
				viewName = "member/memberForm";
				break;
			case OK:
//					2) OK 
//						mypage로 이동 (redirect)
				viewName = "redirect:/mypage";
				break;
			default:
//					3) FAIL
//						memberInsert 으로 이동 (기존 입력 데이터, 메시지, dispatch)
				req.setAttribute("message", "서버 오류, 쫌따 다시 해보셈.");
				viewName = "member/memberForm";
				break;
			}
		}else {
//			불통
//				memberInsert 으로 이동 (기존 입력 데이터, 검증 결과 메시지들.., dispatch)
			viewName = "member/memberForm";
		}

		return viewName;
	}
		

}
