package kr.or.ddit.member.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.login.service.AuthenticateService;
import kr.or.ddit.login.service.AuthenticateServiceImpl;
import kr.or.ddit.member.UserNotFoundException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PaginationInfo;

@Service
public class MemberServiceImpl implements MemberService {
	@Inject
	private MemberDAO dao;
	@Inject
	private AuthenticateService authService;

	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		//중복여부 판단
		if(dao.selectMember(member.getMemId())==null) {
			// 성공실패 여부 판단
			int rowcnt = dao.insertMember(member);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
			
		} else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}

	@Override
	public MemberVO retrieveMember(String memId) {
		MemberVO member = dao.selectMember(memId);
		if(member==null) 
			throw new UserNotFoundException(memId);
		return member;
	}

	@Override
	public List<MemberVO> retrieveMemberList(PaginationInfo paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);		// 이단계에서 totalRecord를 받아옴
		List<MemberVO> dataList = dao.selectMemberList(paging);
		paging.setDataList(dataList);
		return dataList;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		MemberVO inputData = new MemberVO();	//인증용도로만 사용
		inputData.setMemId(member.getMemId());
		inputData.setMemPass(member.getMemPass());
		
		ServiceResult authenticated = authService.authenticate(inputData);
		ServiceResult result = null;
		
		if(authenticated==ServiceResult.OK) {
			//인증성공
			int rowcnt = dao.updateMember(member);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL; 
		} else {
			// 인증실패
			result = ServiceResult.INVALIDPASSWORD;
		}
		
		return result;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {

		ServiceResult result = authService.authenticate(member);
		
		if(result==ServiceResult.OK) {
			// 인증성공
			int rowcnt = dao.deleteMember(member.getMemId()); 
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		} else {
			// 인증실패
			result = ServiceResult.INVALIDPASSWORD;
		}

		return result;
	}

}
