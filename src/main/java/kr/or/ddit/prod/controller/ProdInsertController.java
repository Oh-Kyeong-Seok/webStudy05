package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@WebServlet("/prod/prodInsert.do")
//@MultipartConfig

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProdInsertController {

	

	
//	@Inject 대신 위에 @RequiredArgsConstructor 이걸 사용 할 수 잇다. 대신 아래 final 적어줘야 한다.
	private final ProdService service;
	@Inject
	private OthersDAO othersDAO;
	
	@ModelAttribute("lprodList")
	public List<LprodVO> lprodList(){
		return othersDAO.selectLprodList();
	}
	
	@ModelAttribute("buyerList")
	public List<BuyerVO> buyerList(){
		return othersDAO.selectBuyerList(null);
	}
	
	@ModelAttribute("prod")
	public ProdVO prod() {
		return new ProdVO();
	}
	
//	private void addRequestAttribute(HttpServletRequest req) {
//		req.setAttribute("lprodList", othersDAO.selectLprodList());
//		req.setAttribute("buyerList", othersDAO.selectBuyerList(null));	//제조사 전체목록조회
//	}
	
	@RequestMapping("/prod/prodInsert.do")
	public String prodForm() {
		
		
		return "prod/prodForm";
	}
	
	@RequestMapping(value = "/prod/prodInsert.do", method = RequestMethod.POST)
	public String prodInsert(
			@Validated(InsertGroup.class) @ModelAttribute("prod") ProdVO prod
			, BindingResult errors // Errors 랑 똑같다
			, Model model
		) throws IOException {
							
		
			
//		Map<String, List<String>> errors = new HashMap<>();
//		model.addAttribute("errors", errors);
//		
//		boolean valid = ValidationUtils.validate(prod, errors, InsertGroup.class);
		
		boolean valid = !errors.hasErrors();
		
		
		String viewName = null;
		if(valid) {
			//통과
			
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case OK:
				viewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
				break;

			default:
				model.addAttribute("message", "서버 오류, 쫌따 다시 해보셈.");
				viewName = "prod/prodForm";
				break;
			}
		} else {
			viewName = "prod/prodForm";
		}

		return viewName;
	}
}
