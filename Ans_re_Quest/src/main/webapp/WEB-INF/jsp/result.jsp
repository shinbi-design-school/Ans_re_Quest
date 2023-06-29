<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.design_shinbi.Ans_re_Quest.Battle"%>
<!DOCTYPE html>

<html>
<head>
<title>Result Page</title>
</head>
<body>
	<h1>バトル結果</h1>
	<%
	boolean isPlayerAlive = (boolean) request.getAttribute("isPlayerAlive");
	boolean isEnemyAlive = (boolean) request.getAttribute("isEnemyAlive");
	%>
	<%
	if (!isPlayerAlive) {
	%>
	<p>プレイヤーは敗北しました。</p>
	<%
	} else if (!isEnemyAlive) {
	%>
	<p>敵を倒しました！</p>
	<%
	}
	%>
</body>
</html>