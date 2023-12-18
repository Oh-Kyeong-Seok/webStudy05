package kr.or.ddit.filter.wrapper;

import java.security.Principal;

import kr.or.ddit.vo.MemberVO;

// 어뎁터는 기본생성자를 가지면 안됨
public class MemberVOWrapper implements Principal{

	private MemberVO adaptee;

	public MemberVOWrapper(MemberVO adaptee) {
		super();
		this.adaptee = adaptee;
	}

	@Override
	public String getName() {
		return adaptee.getMemId();	//식별자로 사용할 이름을 가져옴
	}
	
	
	public MemberVO getRealUser() {
		return adaptee;
	}
}
