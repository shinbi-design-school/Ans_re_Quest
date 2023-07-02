<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<a href="battle">最初に戻る。</a>
	<%
	} else if (!isEnemyAlive) {
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