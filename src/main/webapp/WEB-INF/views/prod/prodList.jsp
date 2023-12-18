<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
	<tbody>
		<c:set var="prodList" value="${paging.dataList }" />
		<c:if test="${empty prodList }">
			<tr>
				<td colspan = "8">검색 조건에 맞는 리스트 없음</td>
			</tr>
		</c:if>
		<c:if test="${not empty prodList }">
			<c:forEach items="${prodList }" var="prod">
			<c:url value="/prod/prodView.do" var="prodViewURL">
				<c:param name="what" value="${prod.prodId }" />
			</c:url>
				<tr>
					<td>${prod.rnum }</td>
					<td><a href="${prodViewURL}">${prod.prodName }</a></td>
					<td>${prod.lprod.lprodNm }</td>
					<td>${prod.buyer.buyerName }</td>
					<td>${prod.prodPrice }</td>
					<td>${prod.prodSale }</td>
					<td>${prod.prodMileage }</td>
					<td>${prod.memCount }</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
	<tfoot>
      <tr>
         <td colspan="8">
            ${paging.pagingHTML }
            <div id="searchUI" class='pagination justify-content-center'>
            <form:select path="detailCondition.prodLgu" class="form-select" >
               <option value>상품분류</option>
                  <c:forEach items="${lprodList}" var="lprod">
                     <form:option label="${lprod.lprodNm } " value="${lprod.lprodGu }" />
                  </c:forEach>       
            	</form:select>

              <form:select path="detailCondition.prodBuyer" class="form-select">
	               <option value>제조사</option>
	               <c:forEach items="${buyerList }" var="buyer">
	                  <form:option class="${buyer.buyerLgu }" value="${buyer.buyerId }" label="${buyer.buyerName }" />
	               </c:forEach>
       		  </form:select>
               <input type="text" name="prodName" placeholder="상품명" class="form-seach"/>               
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
<form:form modelAttribute="detailCondition" id = "searchForm" class="border" method="get">
	<form:input path="prodLgu" readonly="readonly" placeholder="prodLgu"/>
	<form:input path="prodBuyer" readonly="readonly" placeholder="prodBuyer"/>
	<form:input path="prodName" readonly="readonly" placeholder="prodName"/>
	
	<input type="text" name="page" readonly="readonly" placeholder="page" />
</form:form>


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
	}).trigger("change");
// 	$(":input[name=prodLgu]").val("${detailCondition.prodLgu}").trigger("change");
// 	$(":input[name=prodBuyer]").val("${detailCondition.prodBuyer}");
// 	$(":input[name=prodName]").val("${detailCondition.prodName}");
	
	
	function fn_paging(page){
		searchForm.page.value = page;
		searchForm.requestSubmit();
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