package kr.or.ddit.prod.service;

import static org.junit.jupiter.api.Assertions.*;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import kr.or.ddit.AbstractRootContextTest;

class ProdServiceImplTest extends AbstractRootContextTest{

	@Inject
	private ProdService service;
	
	@Test
	void testRetrieveProd() {
		service.retrieveProd("P101000001");
	}

	@Test
	void testRetrieveProdList() {
		fail("Not yet implemented");
	}

	@Test
	void testCreateProd() {
		fail("Not yet implemented");
	}

	@Test
	void testModifyProd() {
		fail("Not yet implemented");
	}

	@Test
	void testProdServiceImpl() {
		fail("Not yet implemented");
	}

}
