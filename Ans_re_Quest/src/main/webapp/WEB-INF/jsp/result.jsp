<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.design_shinbi.Ans_re_Quest.model.Battle"%>
<!DOCTYPE html>

<html>
<head>
<title>Result Page</title>
</head>
<body>
	<h1>バトル結果</h1>
	<%
	System.out.println("1");
	boolean isPlayerAlive = (boolean) request.getAttribute("isPlayerAlive");
	boolean isEnemyAlive = (boolean) request.getAttribute("isEnemyAlive");
	%>
	<%
	System.out.println("2");
	if (!isPlayerAlive) {
	System.out.println("3");
	%>

	<p>プレイヤーは敗北しました。</p>
	<%
	} else if (!isEnemyAlive) {
		System.out.println("4");
	%>
	<p>敵を倒しました！</p>
	<%
	}
	%>

	<%
	if (!isEnemyAlive) {
	%>
	<a href="battle">次の戦闘へ進む</a>
	<%
	}
	%>
</body>
</html>