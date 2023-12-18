package kr.or.ddit.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.SearchVO;

/**
 *	목록조회 : /member/memberList.do
 *	마이페이지 : /mypage
 *	정보수정 : /member/memberUpdate.do
 *	탈퇴 : /member/memberDelete.do
 *	가입 : /member/memberInsert.do
 *	
 *	상세조회 : /member/memberView.do?who=a001 쿼리스트링이용 누구인지를 전달
 *
 */
//@WebServlet("/member/memberList.do")
@Controller
public class MemberListController {
	@Inject
	private MemberService service;
	
	@RequestMapping("/member/memberList.do")
	public String memberList(
			@RequestParam(value = "searchType", required = false) String searchType
			, @RequestParam(value = "searchWord", required = false) String searchWord
			, @RequestParam(value = "page", required = false, defaultValue = "1") int currentPage
			, Model model
			, @ModelAttribute("simpleCondition") SearchVO simpleCondition
			) {
		
		// Controller단에서 PaginationInfo를 생성
		PaginationInfo<MemberVO> paging = new PaginationInfo<>(5, 2);
		paging.setSimpleCondition(simpleCondition);	//키워드 검색 조건
		
		// request의 parameter로 받은 page정보를 넘김 → 이때 PaginationInfo의 property는 5개
		paging.setCurrentPage(currentPage);
		
		service.retrieveMemberList(paging);	//이단계에서 totalRecode 생성
		//paging.setDataList(memberList); 	//여기서 10번쨰 프로퍼티가 결정!
		
		model.addAttribute("paging", paging);	// 최종모델은 paging이 되어야함.
		
		return "member/memberList";
	
	}

}
