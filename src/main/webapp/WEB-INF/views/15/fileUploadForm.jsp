<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>파일 업로드 양식</h4>
<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath }/15/fileUpload.do">
	<input type="text" name="uploader" />
	<input type="file" name="uploadFile" />
	<input type="submit" value="업로드" /> 
</form>

<c:if test="${not empty uploadVO }">
	uploader : ${uploadVO.uploader }
	<img src="<c:url value='${uploadVO.fileUrl }' />">
</c:if>

</body>
</html>