package kr.or.ddit.adrs.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.or.ddit.adrs.dao.AddressDAO;
import kr.or.ddit.adrs.dao.AddressDAOImpl;
import kr.or.ddit.vo.AddressVO;

class AddressServiceImplTest {
	private AddressService service = new AddressServiceImpl();
	
	@Test
	void testCreateAddress() {
		
		AddressVO vo = new AddressVO();
		vo.setMemId("a001");
		vo.setAdrsName("테스트");
		vo.setAdrsHp("000-0000-0000");
		vo.setAdrsAdd("대전 오류");
		boolean result = service.createAddress(vo);
		
		assertEquals(true, result);
	
	}

	@Test
	void testRetriveAddressList() {
		// 예외가 던져지지 않을꺼다라는걸 예상
		List<AddressVO> adrslist = assertDoesNotThrow(()->{
			return service.retriveAddressList("b001");
		});
		// null이 아닐꺼다 예상
		assertNotNull(adrslist);
		
	}

	@Test
	void testModifyAddress() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveAddress() {
		boolean result = service.removeAddress(1);
		assertNotEquals(true, result);
	
	}

}
