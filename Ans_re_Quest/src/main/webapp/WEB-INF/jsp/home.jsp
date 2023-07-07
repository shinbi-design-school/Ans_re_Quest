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

<div class="homeChara">
<div><img src="imgs/homeChara/face1.png" id="change_image" class="character"></div>
<div class="text"><div class="wordbox"><div class="word">
<div class="comment" style="word-wrap: break-word;">
</div></div></div></div>
</div>

<div class="homeQuest"><a href="battle"><button class="Quest"></button></a></div>

<jsp:include page="bottomMenu.jsp"></jsp:include>

</div>

<jsp:include page="footer.jsp"/>
</body>
</html>