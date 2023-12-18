package kr.or.ddit.prod.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;

@Controller
public class ProdListController_Case2 {

	// spring의 AOP 방법론으로 해결 예정.
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
//	private void addAttribute(HttpServletRequest req) {
//		req.setAttribute("lprodList", othersDAO.selectLprodList());
//		req.setAttribute("buyerList", othersDAO.selectBuyerList(null));	//제조사 전체목록조회
//	}
	
}
