<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form id="adrsForm" method="post" action="${pageContext.request.contextPath }/adrs/address">
	<input type="text" name="adrsName" placeholder="이름" />
	<input type="text" name="adrsHp" placeholder="휴대폰" />
	<input type="text" name="adrsAdd" placeholder="주소" />
	<input type="submit" value="등록" />
</form>
<hr>

<table>
	<thead>
		<tr>
			<th>이름</th>
			<th>휴대폰</th>
			<th>주소</th>
		</tr>
	</thead>
	<tbody id="listBody">
		
	</tbody>
</table>


<!-- Modal -->
<div class="modal fade" id="updateModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Address Update Form</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      
      <div class="modal-body">
	      <form id="updateForm" action="${pageContext.request.contextPath }/adrs/address">
	      
	       	<input type="text" name="adrsName" placeholder="이름" /><br><br>
			<input type="text" name="adrsHp" placeholder="휴대폰" /><br><br>
			<input type="text" name="adrsAdd" placeholder="주소" /><br><br>
	      </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
        <button type="button" class="modBtn">수정</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript"src="${pageContext.request.contextPath }/resources/js/app/adrs/address.js"></script>

