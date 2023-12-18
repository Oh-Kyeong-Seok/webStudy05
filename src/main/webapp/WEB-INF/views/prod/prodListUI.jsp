<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<a href="<c:url value='/prod/prodInsert.do' />" class="btn btn-primary">신규상품등록</a>


<table class="table table_border">
	<thead>
		<tr>
			<th>일련번호</th>
			<th>상품명</th>
			<th>상품분류명</th>
			<th>제조사명</th>
			<th>판매가</th>
			<th>세일가</th>
			<th>마일리지</th>
			<th>구매자수</th>
		</tr>
	</thead>
	<tbody id="listBody">
		
	</tbody>
	<tfoot>
      <tr>
         <td colspan="8">
         <span id="pagingArea"></span>
            <div id="searchUI" class='pagination justify-content-center'>
            <select name="prodLgu">
               <option value>상품분류</option>
                  <c:forEach items="${lprodList}" var="lprod">
                     <option label="${lprod.lprodNm } " value="${lprod.lprodGu }" />
                  </c:forEach>
            </select>
               <select name="prodBuyer">
                  <option value>제조사</option>               
                  <c:forEach items="${buyerList}" var="buyer">
                     <option class="${buyer.buyerLgu }" label="${buyer.buyerName }" value="${buyer.buyerId }" />
                  </c:forEach>
               </select>
               <input type="text" name="prodName" placeholder="상품명" />               
               <input type="button" value="검색" id="searchBtn" />
            </div>
         </td>

      </tr>
	</tfoot>
</table>
<div>
	<a href = "${pageContext.request.contextPath }/prod/prodInsert.do">상품추가</a>
</div>



<!-- 히든태그 사용 -->
<h4>전송UI</h4>
<form id = "searchForm" class="boader" action="<c:url value='/prod/ajax/prodListData.do' />">
	<input type="text" name="prodLgu" readonly="readonly" placeholder="prodLgu" />
	<input type="text" name="prodBuyer" readonly="readonly" placeholder="prodBuyer"  />
	<input type="text" name="prodName" readonly="readonly" placeholder="prodName"  />
	<input type="text" name="page" readonly="readonly" placeholder="page" />
</form>  

<script>
	$("select[name=prodLgu]").on("change", function(event){
		let lgu = $(this).val();
		let options = $("select[name=prodBuyer]").find("option");
		
		$(options).hide();
		$(options).filter((i,e)=>i==0).show();
		
		if(lgu) {
			$(options).filter(`.\${lgu}`).show();
		} else {
			$(options).show();
		}
		
		// 이 코드로 사용하면 서버부하가 발생함 -> filter를 사용할것!
// 		$("select[name=prodBuyer]").find("option").hide();
// 		$("select[name=prodBuyer]").find("option:first").show();
// 		if(lgu){
// 			$("select[name=prodBuyer]").find(`option.\${lgu}`).show();
// 		} else {
// 			$("select[name=prodBuyer]").find(`option`).show();
// 		}
	});
// 	$(":input[name=prodLgu]").val("${paging.detailCondition.prodLgu}").trigger("change");
// 	$(":input[name=prodBuyer]").val("${paging.detailCondition.prodBuyer}");
// 	$(":input[name=prodName]").val("${paging.detailCondition.prodName}");
	
	$(searchForm).on("submit", function(event){
		event.preventDefault();
		let cPath = document.body.dataset.contextPath;
		let url = this.action;
		let data = $(this).serialize();
		
		$.getJSON(`\${url}?\${data}`)
			.done(function(resp){
				let dataList = resp.paging.dataList;
				trTag = "";
				if(dataList ?. length > 0 ){
					$.each(dataList, function(idx, list){
						let prodViewURL = `\${cPath}/prod/prodView.do?what=\${list.prodId}`;
						trTag += `
							<tr data-prod-id="\${list.prodId}">
								<td>\${list.rnum}</td>
								<td><a href="\${prodViewURL}">\${list.prodName}</a></td>
								<td>\${list.lprod.lprodNm}</td>
								<td>\${list.buyer.buyerName}</td>
								<td>\${list.prodPrice}</td>
								<td>\${list.prodSale}</td>
								<td>\${list.prodMileage}</td>
								<td>\${list.memCount}</td>
							</tr>
						`;
					});
					$(pagingArea).html(resp.paging.pagingHTML);
				} else {
					trTag += `
						<tr>
							<td colspan = '8'>검색 조건에 맞는 리스트 없음</td>
						</tr>
					`;
					$(pagingArea).empty();
				}
				$(listBody).html(trTag);
			}); 
			
	}).submit();
	
	function  fn_paging(page){
		searchForm.page.value = page;
		searchForm.requestSubmit();
		searchForm.page.value = "";
	}
	
	$(searchUI).on("click", "#searchBtn", function(event){
		let inputs = $(this).parents("#searchUI").find(":input[name]");
		$.each(inputs, function(idx, ipt){
			let name = ipt.name;
			let value = $(ipt).val();
			$(searchForm).find(`:input[name=\${name}]`).val(value);
			$(searchForm).submit();
		});
	});
	
</script>