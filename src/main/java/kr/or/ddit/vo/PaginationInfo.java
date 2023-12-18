package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import kr.or.ddit.paging.BootstrapPaginationRenderer;
import kr.or.ddit.paging.DefaultPaginationRenderer;
import kr.or.ddit.paging.PaginationRender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 페이징 처리와 관련된 모든속성을 가진 자바빈
 * 처음에 int타입으로 기본적으로 필요한 9개 property생성.
 * 아직 totalRecord를 받아오기 전이라서 7개의 정보만 담고있음
 *  
 */
@Getter
@ToString
@NoArgsConstructor	//기본생성자 생성
@JsonIgnoreProperties("renderer")
public class PaginationInfo<T> implements Serializable{
	
	public PaginationInfo(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}

	private int totalRecord;	// select쿼리필요
	private int currentPage;	// request paramater
	
	// 개발자가 정하는 영역
	private int screenSize = 10;
	private int blockSize = 5;
	
	// 연산식 필요
	private int totalPage;
	private int startRow ;
	private int endRow;
	private int startPage;
	private int endPage ;
	
	private List<T> dataList;	// 다른곳에서도 사용하기위해서!
	
	private SearchVO simpleCondition;	// 단순 키워드 검색조건
	private T detailCondition; // 상세 검색 조건
	

//	private transient PaginationRender renderer = new DefaultPaginationRenderer();
	private PaginationRender renderer = new BootstrapPaginationRenderer();
	
	public void setDetailCondition(T detailCondition) {
		this.detailCondition = detailCondition;
	}
	
	public void setSimpleCondition(SearchVO simpleCondition) {
		this.simpleCondition = simpleCondition;
	}
	
	public void setRenderer(PaginationRender renderer) {
		this.renderer = renderer;
	}
	
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = (totalRecord +(screenSize - 1)) / screenSize;	
		//totalPage는 setter가 있으면 안됨
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		endRow = currentPage * screenSize;
		startRow = endRow - (screenSize - 1);
		endPage = blockSize * ((currentPage + (blockSize - 1)) / blockSize);
		startPage = endPage - (blockSize - 1);
		// endRow, startRow, endPage,startPage는 setter가 있으면 안됨
	}
	
	public int getEndPage() {
		//endPage가 totalPage보다 크면 endPage 작으면 totalPage
		return endPage < totalPage ? endPage : totalPage;
	}
	
	public String getPagingHTML(){
		return renderer.renderPagination(this);
	}
	
}
