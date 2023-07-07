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
<h2>Mypage</h2>
<div id="flex">
<div class="leftMenu">
<div>ユーザネームorプレイヤーネーム</div>
<div><img alt="キャラ立ち絵" src="imgs/man.png"></div>
<div><p>踏破階層を出力します</p></div>
</div>
<div class="rightMenu">
<div class="weapon">武器：エスカリボルグ</div>
<div class="armor">防具：sampleローブ</div>
<div class="items">装飾：sampleブレスレット</div>
</div>
<div class="trickMenuList">
<div>Menu</div>
<ul>
  <li class="trickMenu" >ITEM</li>
  <li class="trickMenu">RANKING</li>
  <li class="trickMenu">ADVERTISEMENT</li>
  <li class="trickMenu">Credit</li>
</ul>
</div>
</div>
<jsp:include page="bottomMenu.jsp"/>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>