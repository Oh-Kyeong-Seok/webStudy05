package kr.or.ddit.buyer.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import lombok.extern.slf4j.Slf4j;

// /buyer/P10101/form
@Slf4j
@Controller
@RequestMapping("/buyer/{buyerId}")
public class BuyerModifyController {
	
	@Inject
	private BuyerService service;
	
	@Inject
	private OthersDAO othersDAO;
	
	@ModelAttribute("lprodList")
	public List<LprodVO> lprodList(){
		return othersDAO.selectLprodList();
	}
	
	@ModelAttribute("buyer")
	public BuyerVO buyer() {
		return new BuyerVO();
	}
	
	
	
	@GetMapping("form")
	public String buyerUpdateForm(@PathVariable String buyerId, Model model) {
		BuyerVO buyer = service.retrieveBuyer(buyerId);
		if(buyer==null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 제조사 없음.");
		model.addAttribute("buyer", buyer);
		return "buyer/buyerEdit";
	}
	
	@PutMapping
	public String buyerUpdate(
			@Validated(UpdateGroup.class) @ModelAttribute("buyer") BuyerVO buyer
			, Errors errors
			, Model model
			) {
		log.info("★☆★☆★☆★☆★☆★☆★☆ BUYER UPDATE PUT REQUEST ★☆★☆★☆★☆★☆★☆");
		
		boolean valid = !errors.hasErrors();				
		
		String viewName = null;
		
		if(valid) {
			ServiceResult result = service.modifyBuyer(buyer);
			
			switch (result) {
			case OK:
				viewName = "redirect:/buyer/{buyerId}";
				break;

			default:
				model.addAttribute("message", "서버 오류, 쫌따 다시 해보셈.");
				viewName = "buyer/buyerEdit";
				break;
			}
		} else {
			viewName = "buyer/buyerEdit";
		}
		
		return viewName;
	}
}
