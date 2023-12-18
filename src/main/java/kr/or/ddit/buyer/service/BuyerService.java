package kr.or.ddit.buyer.service;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PaginationInfo;

public interface BuyerService {
	/**
	 * 제조사 상세 조회
	 * @param buyerId
	 * @return 존재하지 않으면, null 반환
	 */
	public BuyerVO retrieveBuyer(String buyerId);
	
	/**
	 * 생성
	 * @param buyer
	 * @return OK, FAIL
	 */
	public ServiceResult createBuyer(BuyerVO buyer);
	
	/**
	 *  수정
	 * @param buyer
	 * @return
	 */
	public ServiceResult modifyBuyer(BuyerVO buyer);
	
	/**
	 * 페이징 처리기반의 목록조회
	 * 페이징 처리의 결과로 totalRecord/dataList를 프로퍼티를 만들어줘야 함.
	 * @param currentPage를 비롯한 프로퍼티를 가진 {@link PaginationInfo}
	 */
	public void retrieveBuyerList(PaginationInfo<BuyerVO> paging);
}
