<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style>
   th{
      text-align: center;
   }
   td{
      padding-bottom: 2px;
   }
</style>

<form:form enctype="multipart/form-data" method="post" modelAttribute="prod">
   <form:hidden path="prodId"/>
   <table class="col-md-6">
      <tr>
         <th><label for="prodName">상품명</label></th>
         <td>
            <form:input type="text" path="prodName" class="form-control" required="true"/>
  
            <form:errors path="prodName" element="span" cssClass="error"></form:errors>
         </td>
      </tr>
      <tr>
         <th><label for="prodLgu">상품분류</label></th>
         <td>
            <form:select path="prodLgu" items="${lprodList }" itemLabel="lprodNm" itemValue="lprodGu" />
            <form:errors path="prodLgu" element="span" cssClass="error"/>
         </td>
      </tr>
      <tr>
         <th><label for="prodBuyer">제조사</label></th>
         <td>
         <form:select path="prodBuyer">
               <option value>제조사</option>
               <c:forEach items="${buyerList }" var="buyer">
                  <form:option class="${buyer.buyerLgu }" value="${buyer.buyerId }" label="${buyer.buyerName }" />
               </c:forEach>
         </form:select>
            <form:errors path="prodBuyer" element="span" cssClass="error"/>
            </td>
      </tr>
      <tr>
         <th><label for="prodCost">구매가</label></th>
         <td>
            <form:input type="number" path="prodCost" class="form-control" required="true"/>
            <form:errors path="prodCost" element="span" cssClass="error"/>
         </td>
      </tr>
           <tr>
         <th>판매가</th>
         <td>
<%--             <input type="number" name="prodPrice" class="form-control" required value="${prod.prodPrice}"> --%>
			<form:input type="number" path="prodPrice" class="form-control" required="true"/>
			<form:errors path="prodPrice" element="span" cssClass="error"/>
         </td>
      </tr>
      <tr>
         <th>세일가</th>
         <td>
<%--          <input type="number" name="prodSale" class="form-control" required value="${prod.prodSale}"> --%>
			<form:input type="number" path="prodSale" class="form-control" required="true"/>
			<form:errors path="prodSale" element="span" cssClass="error"/>
         </td>
      </tr>
      <tr>
         <th>요약정보</th>
         <td>
<%--             <input type="text" name="prodOutline" class="form-control" required value="${prod.prodOutline}"> --%>
            <form:input type="text" path="prodOutline" class="form-control" required="true"/>
            <form:errors path="prodOutline" element="span" cssClass="error"/>
         </td>
      </tr>
      <tr>
         <th>상세정보</th>
         <td>
<%--             <input type="text" name="prodDetail" class="form-control" value="${prod.prodDetail}"> --%>
            <form:input type="text" path="prodDetail" class="form-control"/>
            <form:errors path="prodDetail" element="span" cssClass="error"/>
         </td>
      </tr>
      <tr>
         <th>이미지</th>
         <td>
            <input type="file" name="prodImage" id ="prodImage" accept="image/*" class="form-control">            
            <form:errors path="prodImg" element="span" cssClass="error"/>
         </td>
      </tr>
      <tr>
         <th>총재고</th>
         <td>
<%--             <input type="number" name="prodTotalstock" class="form-control" required value="${prod.prodTotalstock}"> --%>
            <form:input type="number" path="prodTotalstock" class="form-control" required="true"/>
            <form:errors path="prodTotalstock" element="span" cssClass="error"/>
         </td>
      </tr>
      <tr>
         <th>입고일</th>
         <td>
<%--             <input type="date" name="prodInsdate" class="form-control" value="${prod.prodInsdate}"> --%>
            <form:input type="date" path="prodInsdate" class="form-control"/>
            <form:errors path="prodInsdate" element="span" cssClass="error"/>
         </td>
      </tr>
      <tr>
         <th>적정재고</th>
         <td>
<%--             <input type="number" name="prodProperstock" class="form-control" required value="${prod.prodProperstock}"> --%>
            <form:input type="number" path="prodProperstock" class="form-control" required="true"/>
            <form:errors path="prodProperstock" element="span" cssClass="error"/>
         </td>
      </tr>
      <tr>
         <th>크기</th>
         <td>
<%--             <input type="text" name="prodSize" class="form-control" value="${prod.prodSize}"> --%>
            <form:input type="text" path="prodSize" class="form-control"/>
            <form:errors path="prodSize" element="span" cssClass="error"/>
         </td>
      </tr>
      <tr>
         <th>색상</th>
         <td>
<%--             <input type="text" name="prodColor" class="form-control" value="${prod.prodColor}"> --%>
            <form:input path="prodColor" class="form-control"/>
            <form:errors path="prodColor" element="span" cssClass="error"/>
         </td>
      </tr>
      <tr>
         <th>배송방법</th>
         <td>
<%--             <input type="text" name="prodDelivery" class="form-control" value="${prod.prodDelivery}"> --%>
            <form:input type="text" path="prodDelivery" class="form-control"/>
            <form:errors path="prodDelivery" element="span" cssClass="error"/>
         </td>
      </tr>
      <tr>
         <th>단위</th>
         <td>
<%--             <input type="text" name="prodUnit" class="form-control" value="${prod.prodUnit}"> --%>
         <form:input type="text" path="prodUnit" class="form-control"/>
           <form:errors path="prodUnit" element="span" cssClass="error"/>
         </td>
      </tr>
      <tr>
         <th>입고량</th>
         <td>
<%--             <input type="number" name="prodQtyin" class="form-control" value="${prod.prodQtyin}"> --%>
            <form:input type="number" path="prodQtyin" class="form-control"/>
            <form:errors path="prodQtyin" element="span" cssClass="error"/>
         </td>
      </tr>
      <tr>
         <th>판매량</th>
         <td>
<%--             <input type="number" name="prodQtysale" class="form-control" value="${prod.prodQtysale}"> --%>
            <form:input type="number" path="prodQtysale" class="form-control"/>
            <form:errors path="prodQtysale" element="span" cssClass="error"/>
         </td>
      </tr>
      <tr>
         <th>마일리지</th>
         <td>
<%--             <input type="number" name="prodMileage" class="form-control" value="${prod.prodMileage}"> --%>
         <form:input type="number" path="prodMileage" class="form-control"/>
           <form:errors path="prodMileage" element="span" cssClass="error"/>
         </td>
      </tr>
      <tr>
      <td colspan="2"><input type="submit" value="전송"
         class="btn btn-primary" /> <input type="reset" value="취소"
         class="btn btn-warning" /></td>
      </tr>

   </table>
</form:form>


<script>
let $prodBuyer = $("select[name=prodBuyer]");
$("select[name=prodLgu]").on("change", function(event){
   let lgu = $(this).val();
   let $options = $prodBuyer.find("option");
   $options.hide();
   $options.filter((i,e)=>i==0).show();
   if(lgu){
      $options.filter(`.\${lgu}`).show();
   }else{
      $options.show();
   }
}).change();

</script>