package kr.or.ddit.filter.wrapper;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import kr.or.ddit.vo.MemberVO;

public class PrincipalHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private HttpServletRequest request;
	
	// HttpServletRequest request → 원본요청이면서 어댑터키
	public PrincipalHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	@Override
	public Principal getUserPrincipal() {
		HttpSession session =  request.getSession(false);
		MemberVO authMember = null;
		if(session != null && !session.isNew()) {
			//request.getSession();
			authMember = (MemberVO)getSession().getAttribute("authMember");		
		}
		if(session!=null) {
			MemberVOWrapper principal = new MemberVOWrapper(authMember);
			return principal;
		} else {
			return super.getUserPrincipal();
		}
	}
	
}
