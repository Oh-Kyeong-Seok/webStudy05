package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.member.UserNotFoundException;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdServiceImpl implements ProdService {
	
	@Value("#{appInfo.prodImagesUrl}")
	private String prodImagesUrl;
	
	@Value("#{appInfo.prodImagesUrl}")
	private Resource prodImages;
	
	private File saveFolder;
	@PostConstruct			// 생성자 이후에 모둔 주입이 완료된 다음에 실행
	public void init() throws IOException {			// 예외 던지면 스프링이 받아간다
		saveFolder = prodImages.getFile();		
	}
	
//	@Inject 안쓰려고 위에 @RequiredArgsConstructor 작성하고 아래 final
	private final ProdDAO dao;
	
	@Override
	public ProdVO retrieveProd(String prodId) {
		ProdVO prod = dao.selectProd(prodId);

		return prod;
	}

	@Override
	public void retrieveProdList(PaginationInfo<ProdVO> paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<ProdVO> dataList = dao.selectProdList(paging);
		paging.setDataList(dataList);
	}

	private void processProdImage(ProdVO prod) {
		try {
			prod.saveTo(saveFolder);			
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public ServiceResult createProd(ProdVO prod) {
		int rowcnt = dao.insertProd(prod);
		ServiceResult result = null;	
		if(rowcnt > 0) {
			result = ServiceResult.OK;
			processProdImage(prod);
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
		
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		int rowcnt = dao.insertProd(prod);
		ServiceResult result = null;	
		if(rowcnt > 0) {
			result = ServiceResult.OK;
			processProdImage(prod);
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

}
