package kr.or.ddit.buyer.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.paging.BootstrapPaginationRenderer;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PaginationInfo;

/**
 * 	GET / POST
 * 	/buyer/buyerView.do
 * 	/buyer/buyerList.do
 * 	/buyer/buyerInsert.do
 * 	/buyer/buyerUpdate.do
 * 	/buyer/buyerDelete.do
 * 
 * 	Restful URI ( GET / POST / PUT / DELETE ) : 명사(URI), 동사(method)
 * 	/buyer/form (GET) : 제조사 등록 양식
 * 	/buyer (POST) : 제조사 등록
 * -------------------------------------------- C
 * 	/buyer (GET) : 제조사 목록 조회
 * 	/buyer/P10101 (GET) : 제조사 상세 조회
 * -------------------------------------------- R
 * 	/buyer/P10101/form (GET) : 제조사 수정 양식
 * 	/buyer/P10101 (PUT) : 제조사 한 건 수정
 * -------------------------------------------- U
 * 	/buyer/P10101 (DELETE) : 제조사 한 건 삭제
 * -------------------------------------------- D
 *
 */
@Controller
@RequestMapping("/buyer")
public class BuyerRetrieveController {

	@Inject
	private BuyerService service;
	
	@ModelAttribute("buyer")
	public BuyerVO buyer() {
		return new BuyerVO();
	}
	
	@RequestMapping(value = "/buyer/buyerListData.do")
	public String listData(
			@ModelAttribute("detailCondition") BuyerVO detailCondition
			, @RequestParam(value = "page", required = false, defaultValue = "1") int currentPage 
			, Model model) {
		PaginationInfo<BuyerVO> paging = new PaginationInfo<>(5,2);
		
		paging.setCurrentPage(currentPage);
		paging.setDetailCondition(detailCondition);
		
		service.retrieveBuyerList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "buyer/buyerList";
	}
	
	
	@GetMapping
	public String buyerListRestful(
		@ModelAttribute("detailCondition") BuyerVO detailCondition
		, @RequestParam(value = "page", required = false, defaultValue = "1") int currentPage
		, Model model
		) {
		
		PaginationInfo<BuyerVO> paging = new PaginationInfo<>(5,2);
		
		paging.setCurrentPage(currentPage);
		paging.setDetailCondition(detailCondition);
		
		service.retrieveBuyerList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		return "buyer/buyerList";
	}
	
	@GetMapping("{buyerId}")		// 위에 @RequestMapping("/buyer") 작성 했기에 '/buyer' 생략, 그리고 '/' 생략
	public String buyerViewTestful(@PathVariable String buyerId, Model model) {
		BuyerVO buyer = service.retrieveBuyer(buyerId);
		model.addAttribute("buyer", buyer);
		return "buyer/buyerView";
	}
	
	
	
	
	
	
//	@RequestToViewNameTranslator 전략 객체 동작	
//	@GetMapping("/buyer/buyerView.do")
//	@RequestMapping("/buyer/buyerView.do")
//	public void buyerList(@RequestParam(value="what") String what, Model model) {
	public String buyerList(@RequestParam(value="what") String what, Model model) {
//	public ModelAndView buyerList(@RequestParam(value="what") String what) {
		
		
		BuyerVO buyer = service.retrieveBuyer(what);
		
		model.addAttribute("buyer", buyer);
		
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("buyer", buyer);
//		mav.setViewName("buyer/buyerView");
//		return mav;
		
		return "buyer/buyerView";
		
//		String 말고 void 로 return 없이 작성 시에는 @RequestMapping("/buyer/buyerView.do") 의 buyer/buyerView 로 자동 return 됨.  		
	}
}
