<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:if test="${not empty message }">
   <script>
      alert("${message}");
   </script>
</c:if>

<h4>가입(수정) 양식</h4>
<form:form method="post" enctype="multipart/form-data" modelAttribute="member">
   <table>
      <tr>
         <th><spring:message code="member.memId" /></th>
         <td>
<%-- 	         <input type="text" name="memId" class="form-control" value="${member.memId }" /> --%>
	         <form:input type="text" path="memId" class="form-control" />
	         <form:errors path="memId" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <th><spring:message code="member.memPass" /></th>
         <td>
<!-- 	         <input type="text" name="memPass" class="form-control" /> -->
	         <form:input type="text" path="memPass" class="form-control" />
	         <form:errors path="memPass" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <th><spring:message code="member.memImg" /></th>
         <td>
         	<input type="file" name="memImage" accept="image/*" class="form-control"/>
         </td>
      </tr>
      
      <tr>
         <th><spring:message code="member.memName" /></th>
         <td>
<%-- 	         <input type="text" name="memName" class="form-control" value="${member.memName }" /> --%>
	         <form:input type="text" path="memName" class="form-control" />
	         <form:errors path="memName" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <th><spring:message code="member.memRegno1" /></th>
         <td>
<%-- 	         <input type="text" name="memRegno1" class="form-control" value="${member.memRegno1 }" /> --%>
	         <form:input type="text" path="memRegno1" class="form-control" />
	         <form:errors path="memRegno1" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <th><spring:message code="member.memRegno2" /></th>
         <td>
<%-- 	         <input type="text" name="memRegno2" class="form-control" value="${member.memRegno2 }" /> --%>
	         <form:input type="text" path="memRegno2" class="form-control" />
	         <form:errors path="memRegno2" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <th><spring:message code="member.memBir" /></th>
         <td>
<%-- 	         <input type="date" name="memBir" class="form-control" value="${member.memBir }" /> --%>
	         <form:input type="date" path="memBir" class="form-control" />
	         <form:errors path="memBir" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <th><spring:message code="member.memZip" /></th>
         <td>
<%-- 	         <input type="text" name="memZip" class="form-control" value="${member.memZip }" /> --%>
	         <form:input type="text" path="memZip" class="form-control" />
	         <form:errors path="memZip" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <th><spring:message code="member.memAdd1" /></th>
         <td>
<%-- 	         <input type="text" name="memAdd1" class="form-control" value="${member.memAdd1 }" /> --%>
	         <form:input type="text" path="memAdd1" class="form-control" />
	         <form:errors path="memAdd1" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <th><spring:message code="member.memAdd2" /></th>
         <td>
<%-- 	         <input type="text" name="memAdd2" class="form-control" value="${member.memAdd2 }" /> --%>
	         <form:input type="text" path="memAdd2" class="form-control" />
	         <form:errors path="memAdd2" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <th><spring:message code="member.memHometel" /></th>
         <td>
<%-- 	         <input type="text" name="memHometel" class="form-control" value="${member.memHometel }" /> --%>
	         <form:input type="text" path="memHometel" class="form-control" />
	         <form:errors path="memHometel" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <th><spring:message code="member.memComtel" /></th>
         <td>
<%-- 	         <input type="text" name="memComtel" class="form-control" value="${member.memComtel }" /> --%>
	         <form:input type="text" path="memComtel" class="form-control" />
	         <form:errors path="memComtel" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <th><spring:message code="member.memHp" /></th>
         <td>
<%-- 	         <input type="text" name="memHp" class="form-control" value="${member.memHp }" /> --%>
	         <form:input type="text" path="memHp" class="form-control" />
	         <form:errors path="memHp" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <th><spring:message code="member.memMail" /></th>
         <td>
<%-- 	         <input type="text" name="memMail" class="form-control" value="${member.memMail }" /> --%>
	         <form:input type="text" path="memMail" class="form-control" />
	         <form:errors path="memMail" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <th><spring:message code="member.memJob" /></th>
         <td>
<%-- 	         <input type="text" name="memJob" class="form-control" value="${member.memJob }" /> --%>
	         <form:input type="text" path="memJob" class="form-control" />
	         <form:errors path="memJob" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <th><spring:message code="member.memLike" /></th>
         <td>
<%-- 	         <input type="text" name="memLike" class="form-control" value="${member.memLike }" /> --%>
	         <form:input type="text" path="memLike" class="form-control" />
	         <form:errors path="memLike" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <th><spring:message code="member.memMemorial" /></th>
         <td>
<%-- 	         <input type="text" name="memMemorial" class="form-control" value="${member.memMemorial }" /> --%>
	         <form:input type="text" path="memMemorial" class="form-control" />
	         <form:errors path="memMemorial" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <th><spring:message code="member.memMemorialday" /></th>
         <td>
<%-- 	         <input type="date" name="memMemorialday" class="form-control" value="${member.memMemorialday }" /> --%>
	         <form:input type="date" path="memMemorialday" class="form-control" />
	         <form:errors path="memMemorialday" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <td colspan="2">
	         <input type="submit" value="전송" class="btn btn-primary" /> 
	         <input type="reset" value="취소" class="btn btn-warning" />
         </td>
      </tr>
   </table>

</form:form>