<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp"/>
<title>home画面(構成中)</title>
</head>
<body>
<div id="totalMenu">
<%--挑戦中、もしくは踏破した塔やプレイヤーネームを表示させたい --%>
<div class="circle"><p style="word-wrap: break-word;"><p>塔の名前とかレベルとか出る予定</p></div>
<div class="userName"><p>ここに名前を表示させたい</p></div>

<jsp:include page="moneyFrame.jsp"/>

<div class="homeChara">
<div><img src="imgs/homeChara/face1.png" id="change_image" class="character"></div>
<div class="text"><div class="wordbox"><div class="word">
<div class="comment" style="word-wrap: break-word;">
</div></div></div></div>
</div>

<div class="homeMenu">
	<div class="homeScenario"><a href="scenario"><button class="scenario"></button></a></div>
	<div class="homeQuest"><a href="battle"><button class="Quest"></button></a></div>
	<div class="homeLogout"><a href="logout"><button class="logout"></button></a></div>
</div>

<jsp:include page="bottomMenu.jsp"/>

</div>

<jsp:include page="footer.jsp"/>
</body>
</html>