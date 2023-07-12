<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="head.jsp"/>
	<title>Ans re Quest</title>
</head>
<body>
<div id="totalMenu">
<div class="clearEvent">
<h1>Congratulation</h1>
<hr class="accessory">
<div class="resultArea">
<p>貴方は<%= request.getAttribute("getMoney") %>$手に入れた</p>

<div>
<div><p style="word-wrap: break-word;" class="clearText">クリアおめでとう!<br>でもごめんね、クリアのイベントは未実装なんだよ。<br>というか実装できるかも不明だね。</p></div>
<img src="imgs/homeChara/face6.png" class="clearCharacter">
</div>
<a href="home" class="homeReturn"><button>Homeへ帰還</button></a>
</div></div></div>
<jsp:include page="footer.jsp"/>
</body>
</html>