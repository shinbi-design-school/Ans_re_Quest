<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp"/>
<title>重篤なエラー</title>
</head>
<body>
<main>
<style>
  #bad {
    background-color: black;
  }
</style>
  <div id="error">
    <p id="bad" style="word-wrap: break-word;"></p>
    <button id="badButton"><a href="javascript:history.back()">はヤく戻レ</a></button>
  </div>
<script type="text/javascript">
  history.pushState(null, null, 
		  'YsXDWFLNRbgjwgsPQZecpzNWAgDxUSysXXBxKwbirVnJactySekyzNstRYeMgNKRfJebkZmskdUeZnXxMmHKMJXnyYhQzRTfNVYbzJyca'
		  );

  var text = "エラー";
  var i = 0;
  function typeWriter() {
    var bad = document.getElementById("bad");
    if (i < text.length) {
      bad.innerHTML += text.charAt(i);
      i++;
      setTimeout(typeWriter, 10);
    } else if (bad.clientHeight + bad.scrollTop >= bad.scrollHeight) {
      i = 0;
      bad.innerHTML;
      setTimeout(typeWriter, 10);
    }
  }
  typeWriter();
</script>
</main>
<jsp:include page="footer.jsp"/>
</body>
</html>