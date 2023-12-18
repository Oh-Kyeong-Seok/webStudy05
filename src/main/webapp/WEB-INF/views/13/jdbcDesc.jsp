<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>13/jdbcDesc.jsp</title>
</head>
<body>
<h4>JDBC(Java DataBase Connectivity)</h4>
<pre>
	1. 드라이버를 빌드패스에 추가
	2. 드라이버(클래스) 로딩 
	3. Connection 생성 
	4. 쿼리 객체 생성
		- Statement : 쿼리 객체 생성시 쿼리가 고정되지 않기떄문에, runtime에 동적 쿼리 실행가능
		- PreparedStatement(선 컴파일된 쿼리 객체) : 쿼리를 미리 컴파일하고 쿼리 객체를 생성함.
										runtime에 쿼리가 사용되는 literal(값)을 변경하여 쿼리를 재사용함. 
		- CallableStatement : 절차적 코드집합인 function/procedure를 호출할 때 사용함.
	5. 쿼리 실행
	6. 결과 집합 핸들링(select)
	7. close(***) - try with resource 구문 활용
</pre>
<table>
	<thead>
		<tr>
			<th>PROPERTY_NAME</th>
			<th>PROPERTY_VALUE</th>
			<th>DESCRIPTION</th>
		</tr>
	</thead>
	<tbody>
	<c:set var="propList" value="${requestScope.list }"/>
		<c:choose>
			<c:when test="${empty propList }">
				<tr>
					<td colspan="3">조회 결과 없음</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${propList }" var="record">
					<tr>
						<td>${record.propertyName }</td>
						<td>${record.propertyValue }</td>
						<td>${record.description }</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>	
	</tbody>
</table>
</body>
</html>