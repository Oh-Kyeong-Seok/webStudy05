package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberInsertController {
	private final MemberService service;
	
	@ModelAttribute("member")
	public MemberVO member() {
		return new MemberVO();
	}
	
	@RequestMapping("/member/memberInsert.do")
	public String memberForm() {
		return "member/memberForm";
	}

	@RequestMapping(value = "/member/memberInsert.do", method = RequestMethod.POST)
	public String insertProcess(
			HttpServletRequest req
			, @Validated(InsertGroup.class) @ModelAttribute("member") MemberVO member
			, Errors errors
			) throws IOException{

		
		
//		Map<String, List<String>> errors = new HashMap<>();
//		req.setAttribute("errors", errors);
////		3. 검증 (대상 : MemberVO)
//		boolean valid = ValidationUtils.validate(member, errors, InsertGroup.class);
		
		boolean valid = !errors.hasErrors();
		
		String viewName = null;
		if(valid) {
//			통과
//				4. createMember 등록 처리
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
//					1) PKDUPLICATED 
//						memberInsert 으로 이동 (기존 입력 데이터, 메시지, dispatch)
				req.setAttribute("message", "아이디 중복");
				viewName = "member/memberForm";
				break;
			case OK:
//					2) OK 
//						welcome page로 이동 (redirect)
				viewName = "redirect:/";
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
