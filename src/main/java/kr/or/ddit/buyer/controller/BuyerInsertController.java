package kr.or.ddit.buyer.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;

@Controller
@RequestMapping("/buyer")
public class BuyerInsertController {
	
	@Inject
	private BuyerService service;
	
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
	
	@ModelAttribute("buyer")
	public BuyerVO buyer() {
		return new BuyerVO();
	}
	
	
	@GetMapping("form")
	public String buyerInsertForm() {
		return "buyer/buyerForm";
	}
	
	@PostMapping
	public String buyerInsert(
			@Validated(InsertGroup.class) @ModelAttribute("buyer") BuyerVO buyer
			, Errors errors
			, Model model
			) {

		boolean valid = !errors.hasErrors();				
		
		String viewName = null;
		if(valid) {
			//통과
			
			ServiceResult result = service.createBuyer(buyer);	
			
			System.out.println("%%%%%%%%%%%" + result);
			
			switch (result) {
			case OK:
				viewName = "redirect:/buyer/"+buyer.getBuyerId();
				break;

			default:
				model.addAttribute("message", "서버 오류, 쫌따 다시 해보셈.");
				viewName = "buyer/buyerForm";
				break;
			}
		} else {
			viewName = "buyer/buyerForm";
		}

		return viewName;
	}
}
