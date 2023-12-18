<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<form method="post" action="<c:url value='/buyer' />" enctype="multipart/form-data">
	<table class="table table-bordered">
		<c:set value="${buyer }" var="buyer"></c:set>
		
	   <tr>
	      <th>사업자등록증사본</th>
	      <td>
	      <input type="hidden" value="${buyer.buyerId }" name="buyerId">
	      	<input type="file" name="buyerImage" class="form-control" accept="image/*"/>
	      </td>
	   </tr>
	   <tr>
	      <th>제조사명</th>
	      <td><input type="text" name="buyerName" class="form-control"></td>
	   </tr>
		<tr>
		   <th><label for="buyerLgu">상품분류</label></th>
		   <td>
         	<select name="buyerLgu">
               <option value>상품분류</option>
                  <c:forEach items="${lprodList}" var="lprod">
                     <option label="${lprod.lprodNm } " value="${lprod.lprodGu }" />
                  </c:forEach>
            </select>

            </td>
		</tr>
	   <tr>
	      <th>은행</th>
	      <td><input type="text" name="buyerBank" class="form-control"></td>
	   </tr>
	   <tr>
	      <th>계좌</th>
	      <td><input type="text" name="buyerBankno" class="form-control"></td>
	   </tr>
	   <tr>
	      <th>계좌주</th>
	      <td><input type="text" name="buyerBankname" class="form-control"></td>
	   </tr>
	   <tr>
	      <th>우편번호</th>
	      <td><input type="text" name="buyerZip" class="form-control"></td>
	   </tr>
	   <tr>
	      <th>주소1</th>
	      <td><input type="text" name="buyerAdd1" class="form-control"></td>
	   </tr>
	   <tr>
	      <th>주소2</th>
	      <td><input type="text" name="buyerAdd2" class="form-control"></td>
	   </tr>
	   <tr>
	      <th>전번</th>
	      <td><input type="text" name="buyerComtel" class="form-control"></td>
	   </tr>
	   <tr>
	      <th>팩스</th>
	      <td><input type="text" name="buyerFax" class="form-control"></td>
	   </tr>
	   <tr>
	      <th>메일</th>
	      <td><input type="text" name="buyerMail" class="form-control"></td>
	   </tr>
	   <tr>
	      <th>담당자</th>
	      <td><input type="text" name="buyerCharger" class="form-control"></td>
	   </tr>
	   <tr>
	      <th>내선번호</th>
	      <td><input type="text" name="buyerTelext" class="form-control"></td>
	   </tr>
	   <tr>
	   		<td>
	   			<input type="submit" value="추가">
	   		</td>
	   </tr>
	</table>
</form>

