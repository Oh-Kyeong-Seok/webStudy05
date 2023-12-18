package kr.or.ddit.prod.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.paging.BootstrapPaginationRenderer;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdListController {	// POJO
	@Inject
	private ProdService service;
	@Inject
	private OthersDAO othersDAO;	// 이거 고정관념가지면 안됨. UI생성용 모델만드는거 아님
	
	@ModelAttribute("lprodList")
	public List<LprodVO> lprodList(){
		return othersDAO.selectLprodList();
	}
	@ModelAttribute("buyerList")
	public List<BuyerVO> buyerList(){
		return othersDAO.selectBuyerList(null);
	}
//  아래쪽을 위처럼 사용 가능하다.	
//	private void addAttribute(Model model) {
//		model.addAttribute("lprodList", othersDAO.selectLprodList());
//		model.addAttribute("buyerList", othersDAO.selectBuyerList(null));	//제조사 전체목록조회
//	}
	
	@RequestMapping(value = "/prod/prodList.do", method = RequestMethod.GET)
	public String prodList(
			@ModelAttribute("detailCondition") ProdVO detailCondition
			, @RequestParam(value = "page", required = false, defaultValue = "1") int currentPage
			, Model model) {
		
		
		listData(detailCondition, currentPage, model);

		return "prod/prodList";
		
	
	}
	
	@RequestMapping(value = "/prod/ajax/prodListUI.do")
	public String UIController(Model model) {
		
		//로지컬뷰를 선택하고 이동
		return "prod/prodListUI";
	
	}
	
	@RequestMapping(value = "/prod/ajax/prodListData.do")
	public String listData(
			@ModelAttribute("detailCondition") ProdVO detailCondition
			, @RequestParam(value = "page", required = false, defaultValue = "1") int currentPage
			, Model model
			) {
		
		PaginationInfo<ProdVO> paging = new PaginationInfo<>(10,5);

		paging.setCurrentPage(currentPage);
		paging.setDetailCondition(detailCondition);
		
		service.retrieveProdList(paging);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		model.addAttribute("paging", paging);
		
		
		return "jsonView";
	
	}
	
}
