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
	<div><p style="word-wrap: break-word;" class="scenarioText">ここではシナリオを読んだり、OP/EDムービーが視聴出来るよ。<br>今は全部読めちゃうからネタばれ注意ってやつだね！<br>因みにシナリオ中の僕と今の僕が違うようにみえても気にしちゃダメだよ！<br>それと、ムービーはyoutubeから読み込む形だから一回クリックしないと基本的に始まらないよ！</p></div>
	<img src="imgs/homeChara/face6.png" class="scenarioCharacter">
</div>

<div class="scenarioList">
<div class="clickable-div">
	<form action="scenario" method="post">
		<button type="submit" id="submit-button" name="scenarioAction" value="GotoOP">オープニング(仮作成)</button>
	</form>
</div>
<div class="clickable-div"">
	<form action="scenario" method="post">
		<button type="submit" id="Prologue" name="scenarioAction" value="GotoPrologue">プロローグ</button>
	</form>
</div>
<div class="clickable-div">
	<form action="scenario" method="post">
		<button type="submit" id="submit-button" name="scenarioAction" value="GotoSupport">初めての出会い</button>
	</form>
</div>
<div class="clickable-div">
	<form action="scenario" method="post">
		<button type="submit" id="submit-button" name="scenarioAction" value="GotoShop">愛想のないショップ店員</button>
	</form>
</div>
<div class="clickable-div">
	<form action="scenario" method="post">
		<button type="submit" id="submit-button" name="scenarioAction" value="GotoBoss">VS BOSS</button>
	</form>
</div>
<div class="clickable-div">
	<form action="scenario" method="post">
		<button type="submit" id="submit-button" name="scenarioAction" value="GotoEpilogue">エピローグ(未作成)</button>
	</form>
</div>
<div class="clickable-div">
	<form action="scenario" method="post">
		<button type="submit" id="submit-button" name="scenarioAction" value="GotoEnding">エンディング(未作成)</button>
	</form>
</div>
<div class="clickable-div">
	<form action="scenario" method="post">
		<button type="submit" id="submit-button" name="scenarioAction" value="Goto???">？？？</button>
	</form>
</div>

</div>


<jsp:include page="bottomMenu.jsp"/>
</div>
<jsp:include page="footer.jsp"/>
<script>
  const clickableDivs = document.querySelectorAll('.clickable-div');
  clickableDivs.forEach(div => {
    div.addEventListener('click', () => {
      div.querySelector('button[type="submit"]').click();
    });
  });
</script>
</body>
</html>