package kr.or.ddit.paging;

import kr.or.ddit.vo.PaginationInfo;

public class BootstrapPaginationRenderer implements PaginationRender {
	private final String STARTPATTERN = "<li class='page-item %s'>";
	private final String STARTCURRNETPATTERN = "<li class='page-item %s' aria-current='page'>";
	private final String ENDPATTERN = "</li>";
	
	private final String ATAGPATTERN = "<a class='page-link' href='javascript:;' onclick='fn_paging(%d);'>%s</a>";
		
	@Override
	public String renderPagination(PaginationInfo<?> paging) {
		int startPage = paging.getStartPage();
		int currentPage = paging.getCurrentPage();
		int endPage = paging.getEndPage();
		int totalPage = paging.getTotalPage();
		StringBuffer html = new StringBuffer();
		
		html.append("<nav aria-label='...'>");
		html.append("<ul class='pagination'>");
		
		if(startPage == 1) {
			html.append(
			String.format(STARTPATTERN, "disabled")
		);
		}else {
			html.append(
			String.format(STARTPATTERN, "")
			);
		}

		html.append(
			String.format(ATAGPATTERN, startPage-1, "Previous")
		);
		
		html.append(ENDPATTERN);
		
		for(int page = startPage; page <= endPage; page++) {
			//현재 페이지일경우 
			if(page == currentPage) {
				html.append(
					String.format(STARTCURRNETPATTERN, "active")
				);
			} else {
				html.append(
					String.format(STARTPATTERN, "")
				);
			}
			html.append(
				String.format(ATAGPATTERN, page, page)
			);
			html.append(ENDPATTERN);
		}
		
		if(endPage < totalPage) {
			html.append(
				String.format(STARTPATTERN,"")
			);
			
		} else {
			html.append(
				String.format(STARTPATTERN,"disabled")
			);
		}
		html.append(
			String.format(ATAGPATTERN, endPage+1, "Next")
		);
		
		html.append(ENDPATTERN);
		html.append("</ul></nav>");
		
		return html.toString();
		
	}

}
