package kr.or.ddit.member;

/**
 * 아이디 조회한 사용자가 존재하지 않는 경우
 *
 */
public class UserNotFoundException extends RuntimeException{

	// 다른용도로 래핑하는 용도로 사용하지 않겠다
	public UserNotFoundException(String memId) {
		super(String.format("%s에 해당하는 사용자가 없음", memId));
	}
	
}
