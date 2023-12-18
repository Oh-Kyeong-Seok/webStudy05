<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<table class="table table_border">
	<thead>
		<tr>
			<th>일련번호</th>
			<th>회원명</th>
			<th>휴대폰</th>
			<th>이메일</th>
			<th>생일</th>
			<th>거주지역</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="memberList" value="${paging.dataList }"></c:set>
		<c:if test="${empty memberList }">
			<tr>
				<td colspan="5">검색 조건에 맞는 회원 없음.</td>
			</tr>
		</c:if>
		<c:if test="${not empty memberList }">
			<c:forEach items="${memberList }" var="member" >
				<tr data-mem-id="${member.memId }" data-bs-toggle="modal" data-bs-target="#exampleModal">
					<td>${member.rnum}</td>
					<td>${member.memName} [${member.prodCount}]</td>
					<td>${member.memHp}</td>
					<td>${member.memMail}</td>
					<td>${member.memBir}</td>
					<td>${member.memAdd1}</td>
					<td>${member.memMileage}</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7">
				${paging.pagingHTML }
				<div id ="searchUI">
					<form:select path="simpleCondition.searchType">
						<form:option label="전체" value="" />
						<form:option label="이름" value="name" />
						<form:option label="지역" value="address" />
					</form:select>
<!-- 					<select name = "searchType">  -->
<!-- 						<option value>전체</option> -->
<!-- 						<option value="name">이름</option> -->
<!-- 						<option value="address">지역</option> -->
<!-- 					</select> -->
					<form:input type="text" path="simpleCondition.searchWord"/>
					<input type="button" value="검색" id="searchBtn"/>
				</div>
			</td>
		</tr>
	</tfoot>
</table>

<!-- 히든태그 사용 -->
<form:form id = "searchForm" modelAttribute="simpleCondition" method="get" class="border">
	<form:input path="searchType" type="text"/>
	<form:input path="searchWord" type="text"/>
<%-- 	<form:input type="text" name="searchType" /> --%>
<%-- 	<form:input type="text" name="searchWord" /> --%>
	<input type="text" name="page" />
</form:form>


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>

<script>
// 	$(":input[name=searchType]").val("${simpleCondition.searchType}");
// 	$(":input[name=searchWord]").val("${simpleCondition.searchWord}");
	function fn_paging(page){
		searchForm.page.value = page; //함수로 넘어온 page num을 searchForm의 page에 셋팅
		searchForm.requestSubmit();
	}
	
	$(searchUI).on("click","#searchBtn", function(event){
		//button의 부모를 찾고 그중 searchUI를 찾아서 :input select까지포함한 모든 입력태그
		let inputs = $(this).parents("#searchUI").find(":input[name]");	
		$.each(inputs, function(idx, ipt){	//index와 input태그를 하나씩 받아옴
			let name = ipt.name;		// searchType, searchWord 이 있음
			let value = $(ipt).val();	// 검색한 입력키워드가 들어있고
			$(searchForm).find(`:input[name=\${name}]`).val(value);
			$(searchForm).submit();
		});
		
	});
	
	//EDD(Event-Driven-Development) : 이벤트주도형 개발
	$(exampleModal).on("show.bs.modal", function(event){
		let $modal = $(this);
		//이벤트를 발생시키는 객체를 찾아가야함
		let trTag = event.relatedTarget; //→ tr태그
		
		
		let who = $(trTag).data("memId");
		//location.href="${pageContext.request.contextPath}/member/memberView.do?who="+who;
		let url = "${pageContext.request.contextPath}/member/memberView.do?who="+who;
		$.get(url)
			.done(function(resp){
				$modal.find(".modal-body").html(resp);
		});
	}).on("hidden.bs.modal", function(event){
		$(this).find(".modal-body").empty();
	});
</script>