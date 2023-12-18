package kr.or.ddit.adrs.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.adrs.service.AddressService;
import kr.or.ddit.adrs.service.AddressServiceImpl;
import kr.or.ddit.vo.AddressVO;

//@WebServlet({"/adrs/address","/adrs/address/*"})
/**
 *  /adrs/address (GET) 주소 조회
 *  /adrs/address (POST) 주소 추가
 *  /adrs/address/23 (GET)	특정 주소 조회
 *  /adrs/address/23 (POST)  특정 주소 추가
 *  /adrs/address/23 (DELETE)  특정 주소 삭제
 *
 */
@Controller
@RequestMapping("/adrs/address")
public class AddressDataController {
	@Inject
	private AddressService service;
	
	@GetMapping
	public String adrsList(Model model, Principal principal) {
		
		
		String memId = principal.getName();
		List<AddressVO> adrslist = service.retriveAddressList(memId);
		
		model.addAttribute("adrslist", adrslist);
		
		return "jsonView";
	}
	
//	private ObjectMapper mapper = new ObjectMapper();
	
	private boolean validate(AddressVO vo, Map<String, String> errors) {
		boolean vaild = true;
		if(StringUtils.isBlank(vo.getAdrsName())) {
			errors.put("adrsName", "이름 누락");
			vaild = false;
		}
		if(StringUtils.isBlank(vo.getAdrsHp())) {
			errors.put("adrsHp", "휴대폰 번호 누락");
			vaild = false;
		}
		return vaild;
	}
	
	@PostMapping
	public String adrsInsert(
			@Valid @RequestBody AddressVO vo
			, Errors error
			, Model model
			, Principal principal){
//		try(
//			// 역직렬화	
//			InputStream is = req.getInputStream();
//		){
//			// 언마샬링 
//			AddressVO vo = mapper.readValue(is, AddressVO.class);
			model.addAttribute("originalData", vo);
			
			vo.setMemId(principal.getName());
			
			// 검증 - 이름, 핸드폰 필수데이터
			boolean valid = !error.hasErrors();
			
			boolean success = false;
			String message = null;
			if(valid) {
				if(service.createAddress(vo)) {
					success = true;
				} else {
					message = "등록실패";
				}
			} else {
				message = "필수 파라미터 누락";
			}
			model.addAttribute("success", success);
			model.addAttribute("message", message);
			
//		}
		
		return "jsonView";
		
	}
	
	@DeleteMapping("{adrsNo}")
	public String adrsDelete(@PathVariable int adrsNo, Model model){
		
//		String uri = StringUtils.substringAfter(req.getRequestURI(), req.getContextPath());
//		int lastIdx = uri.lastIndexOf("/");
//		int uriLen = uri.length();
//		int baseLen = "/adrs/address".length();
//		
//		boolean valid = lastIdx >= baseLen && lastIdx < uriLen - 1;
//		String adrsNoPart = adrsNoPart = uri.substring(lastIdx+1);
//		//if (valid) {
//		//	adrsNoPart = uri.substring(lastIdx+1); // '/'이후꺼를 자름
//		//	valid = StringUtils.isNumeric(adrsNoPart);
//		//}
//		int adrsNo = -1;
//		try{
//			adrsNo = Integer.parseInt(adrsNoPart);
//			
//		} catch (NumberFormatException e) {
//			valid = false;
//		}
		
//		if(!valid) {
//			resp.sendError(400,"주소록 번호가 없음");
//			return null;
//		}
		boolean success = service.removeAddress(adrsNo);
		model.addAttribute("success", success);
		if(!success) {
			model.addAttribute("message", "삭제 실패");
		}
	
		return "jsonView";
		

	}

	
//	@RequestMapping(value = "/adrs/address", method = RequestMethod.PUT)
//	public String adrsUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		try(
//			// 역직렬화	
//			InputStream is = req.getInputStream();
//		){
//			// 언마샬링 
//			AddressVO vo = mapper.readValue(is, AddressVO.class);
//			req.setAttribute("updatelData", vo);
//			Map<String, String> errors = new HashMap<>();
//			req.setAttribute("errors", errors);
//			
//			// 검증 - 이름, 핸드폰 필수데이터
//			boolean valid = validate(vo, errors);
//			boolean success = false;
//			String message = null;
//			if(valid) {
//				if(service.modifyAddress(vo)) {
//					success = true;
//				} else {
//					message = "수정실패";
//				}
//			} else {
//				message = "필수 파라미터 누락";
//			}
//			req.setAttribute("success", success);
//			req.setAttribute("message", message);
//			
//		}
//		
//		
//		return "jsonView";
		

	
	
	
	}
	
	
