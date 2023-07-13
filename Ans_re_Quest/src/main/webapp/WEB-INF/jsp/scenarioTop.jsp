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
<div>
	<div><p style="word-wrap: break-word;" class="scenarioText">ここではシナリオを読むことが出来るよ。<br>今は全部読めちゃうからネタばれ注意ってやつだね！<br>因みにシナリオ中の僕と今の僕が違うようにみえても気にしちゃダメだよ！</p></div>
	<img src="imgs/homeChara/face6.png" class="scenarioCharacter">
</div>

<div class="scenarioList">
<div>
	<form action="scenario" method="post">
		<button type="submit" name="scenarioAction" value="GotoPrologue">プロローグ</button>
	</form>
</div>
<div>
	<form action="scenario" method="post">
		<button type="submit" name="scenarioAction" value="GotoSupport">初めての出会い</button>
	</form>
</div>
<div>
	<form action="scenario" method="post">
		<button type="submit" name="scenarioAction" value="GotoShop">愛想のないショップ店員</button>
	</form>
</div>
<div>
	<form action="scenario" method="post">
		<button type="submit" name="scenarioAction" value="Goto???">？？？</button>
	</form>
</div>

</div>


<jsp:include page="bottomMenu.jsp"/>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>