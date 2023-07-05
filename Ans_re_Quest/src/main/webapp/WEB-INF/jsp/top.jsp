<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp"/>
<title>Ans re Quest</title>
</head>
<body>
  <div id="splash">
    <div id="splash_logo">
      <img src="imgs/Ans_re_Quest_logo.png" alt="logo" class="fadeUp">
      <div id="containe"></div>
    </div>
  </div>

<div id="button-wrap" class="start">
    <button id="can-play-button">当サイトは音楽が流れます<br>ご注意ください</button>
</div>
<div id="totalMenu">

	<img alt="logo" src="imgs/Ans_re_Quest_logo.png" class="topimg">
	<div>
	<button class="btn">
		<a href="home">冒険に出る</a>
	</button>
	</div>
	<audio id="audio" src="https://tool-engineer.work/wp-content/uploads/2022/10/demo.mp3"></audio>
	<jsp:include page="login.jsp"/>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>