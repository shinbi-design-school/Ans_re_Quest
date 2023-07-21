<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css">
<jsp:include page="head.jsp"/>
<title>Result Page</title>
</head>
<body>
<div class="spaceBar"></div>
<div id="totalMenu">


	<h1>バトル結果</h1>
	<%
	boolean isPlayerAlive = (boolean) request.getAttribute("isPlayerAlive");
	boolean isEnemyAlive = (boolean) request.getAttribute("isEnemyAlive");
	%>
	<%
	if (!isPlayerAlive) {
	%>
	<div id="resultMessage">
	<p>あなたは問題を解くことができず<br>メンタルが崩壊してしまった…</p>
	</div>
	<div id="gameOverText">GAMEOVER</div>
	<div id="result_next">
	<a href="home">最初に戻る</a>
	</div>
	<%
	} else if (!isEnemyAlive) {
	%>
	<div id="resultMessage">
	<p>問題を解き、敵のメンタルを削りきった！</p>
	</div>
	<div id="victoryText">VICTORY!</div>
	<%
	}
	%>

	<%
	if (!isEnemyAlive) {
	%>
	<div id="result_next">
	<a href="battle">次へ進む</a>
	</div>
	<%
	}
	%>
	
</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>