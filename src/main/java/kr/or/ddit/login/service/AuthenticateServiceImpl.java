package kr.or.ddit.login.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements AuthenticateService {
	private final MemberDAO memberDAO;
	
	@Override
	public ServiceResult authenticate(MemberVO inputData) {
		MemberVO saved = memberDAO.selectMemberForAuth(inputData);
		ServiceResult result = null;
		if(saved!=null) {
			String inputpass = inputData.getMemPass();
			String savedPass = saved.getMemPass();
			if(savedPass.equals(inputpass)) {
				try {
					BeanUtils.copyProperties(inputData, saved);
					result = ServiceResult.OK;
				} catch (IllegalAccessException | InvocationTargetException e) {
					// MemberVO가 잘못만들어짐
					throw new RuntimeException(e);
				}
				
			} else {
				result = ServiceResult.INVALIDPASSWORD;
			}
		} else {
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}

}
