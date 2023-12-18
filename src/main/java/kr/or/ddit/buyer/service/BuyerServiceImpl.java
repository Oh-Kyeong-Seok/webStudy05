package kr.or.ddit.buyer.service;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import kr.or.ddit.buyer.dao.BuyerDAO;
import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PaginationInfo;

@Service
public class BuyerServiceImpl implements BuyerService{
	
	@Value("#{appInfo.buyerImages}")
	private Resource buyerImages;
	
	@Inject
	private BuyerDAO dao;
	
	@Override
	public BuyerVO retrieveBuyer(String buyerId) {
		BuyerVO buyer = dao.selectBuyer(buyerId);
		return buyer;
	}
	
	private void processBuyerImage(BuyerVO buyer) {
		try {
			buyer.saveTo(buyerImages.getFile());
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ServiceResult createBuyer(BuyerVO buyer) {			
		int cnt = dao.insertBuyer(buyer);					
		ServiceResult result = null;	
		if(cnt > 0) {
			processBuyerImage(buyer);
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public ServiceResult modifyBuyer(BuyerVO buyer) {	
		int rowcnt = dao.updateBuyer(buyer);
		ServiceResult result = null;	
		if(rowcnt > 0) {
			processBuyerImage(buyer);
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public void retrieveBuyerList(PaginationInfo<BuyerVO> paging) {
		int totalRecord = dao.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<BuyerVO> dataList = dao.selectBuyerList(paging);
		paging.setDataList(dataList);
		
	}



}
