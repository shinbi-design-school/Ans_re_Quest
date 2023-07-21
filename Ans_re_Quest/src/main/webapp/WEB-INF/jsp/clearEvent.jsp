<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="head.jsp"/>
	<title>Ans re Quest</title>
</head>
<body>
<div class="spaceBar"></div>
<div id="totalMenu">
<div class="clearEvent">
<h2>Congratulations</h2>
<hr class="accessory">
<div class="resultArea">
<p>貴方は<%= request.getAttribute("getMoney") %>$手に入れた</p>
<div class="clickable-div" id="clear">
	<form action="scenario" method="post">
		<button type="submit" id="submit-button" name="scenarioAction" value="GotoED">エンディング</button>
	</form>
</div>
<div class="clickable-div" id="clear">
	<form action="scenario" method="post">
		<button type="submit" id="submit-button" name="scenarioAction" value="GotoEpilogue">エピローグ</button>
	</form>
</div>
<div>
<div><p style="word-wrap: break-word;" class="clearText">クリアおめでとう<br>君はこの塔にある秘密を知ることが出来る<br>この物語の結末を――</p></div>
<img src="imgs/homeChara/face1.png" class="clearCharacter">
</div>
<a href="home" class="homeReturn"><button>Homeへ帰還</button></a>
</div></div></div>
<jsp:include page="footer.jsp"/>
</body>
</html>