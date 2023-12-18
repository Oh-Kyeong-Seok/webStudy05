<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<%
String message = (String)session.getAttribute("message");
session.removeAttribute("message");	// flash attribute
if(message!=null && !message.trim().isEmpty()){
%>
   <script>
      alert("<%=message%>");
   </script>
<%
}
%>
<%
	Cookie[] cookies = request.getCookies();
	
	String idCookieValue = "";
	String chk = "";
	if(cookies != null){
		for(Cookie tmp :cookies) {
			if("idCookie".equals(tmp.getName())){
				idCookieValue = URLDecoder.decode(tmp.getValue(),"UTF-8");
				chk = "checked";
				break;
			}
		}
	}
	System.out.println("idCookieValue : "+ idCookieValue);

%>

</head>
<body>
<form action="<%=request.getContextPath() %>/login/loginProcess.do" method="post" enctype="application/x-www-form-urlencoded" >
	<ul>
		<li>
			아이디 : <input type="text" name="memId" value="<%=idCookieValue %>" />
			<input type="checkbox" name="idSave" value="saveId" <%=chk %>/>아이디저장하기
		</li>

		<li>
			비밀번호 : <input type="text" name="memPass" />
			<input type="submit" value="로그인" />
		</li>
	</ul>
</form>
</body>
</html>