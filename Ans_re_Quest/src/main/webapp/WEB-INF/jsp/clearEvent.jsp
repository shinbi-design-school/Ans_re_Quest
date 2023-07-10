<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="head.jsp"/>
	<title>Insert title here</title>
</head>
<body>
<div id="totalMenu">
<p><%= request.getAttribute("getMoney") %>マネー手に入れた</p>
<a href="home">クリア階層でのイベント画面</a>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>