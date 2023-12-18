package kr.or.ddit.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController  {
	
	@RequestMapping("/index.do")
	public String index(Model model) {	
		//모델만들기
		String title = "컨트롤러에서 만든 Model 타이틀";
		
		//모델공유
		model.addAttribute("title", title);
		
		
		// 뷰를 선택하고 이동
		return "index";
		
	}
}
