package kr.or.ddit.prod.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdViewController { //POJO
	@Inject
	private ProdService service;
	
	@RequestMapping(value = "/prod/prodView.do", method = RequestMethod.GET)
	public String prodView (@RequestParam("what") String prodId, HttpServletRequest req) {
		
		ProdVO prod = service.retrieveProd(prodId);
		req.setAttribute("prod", prod);
		
		return "prod/ProdView";
	
	}

}
