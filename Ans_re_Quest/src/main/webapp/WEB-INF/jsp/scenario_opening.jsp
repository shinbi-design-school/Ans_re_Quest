<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp"/>
<title>Ans re Quest</title>
</head>
<body>
<div id="video_wrapper">
<video id="video">
  <source src="${pageContext.request.contextPath}/resource/Ans_re_Quest_preOP.mp4" type="video/mp4" autoplay>
  <p>お使いのブラウザはビデオタグをサポートしていません。</p>
</video>

<script>
  let played = false;
  window.addEventListener('click', () => {
    if (!played) {
      document.getElementById('video').play();
      played = true;
    }
  });
  const video = document.getElementById('video');
  video.addEventListener('ended', () => {
	  setTimeout(function() {
	      history.back();
	    }, 1000);  
	});
</script>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>