<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css">
    <jsp:include page="head.jsp"/>
    <script>  //モーダルウィンドウ
        function openModal() {
            var modal = document.getElementById("modal");
            modal.style.display = "block";
            setTimeout(function() {
                var closeButton = document.getElementById("modal-close");
                closeButton.style.display = "block";
            }, 5000);
        }

        function closeModal() {
            var modal = document.getElementById("modal");
            modal.style.display = "none";
            var closeButton = document.getElementById("modal-close");
            closeButton.style.display = "none";
        }
    </script>
    <title>Ans re Quest</title>
</head>
<body>
<div id="totalMenu">
    <h2>Mypage</h2>
    <div id="flexMenu">
        <div class="leftMenu">
            <div>ユーザネームorプレイヤーネーム</div>
            <div><img alt="キャラ立ち絵" src="imgs/man.png"></div>
            <div><p>踏破階層を出力します</p></div>
        </div>
        <div class="rightMenu">
            <p>現在の装備</p>
            <div class="weapon">武器<br>　エスカリボルグ</div>
            <div class="armor">防具<br>　sampleローブ</div>
            <div class="items">装飾<br>　sampleブレスレット</div>
            <div class="money">現在の所持金<br>　9999999999$</div>
        </div>
        <div class="trickMenuList">
            <div>Menu</div>
            <a href="inventory"><p class="trickMenu">ITEM</p></a>
            <a href="ranking"><p class="trickMenu">RANKING</p></a>
            <p class="trickMenu" onclick="openModal()">ADVERTISEMENT</p>
            <form method="post" name="scenarioAction" action="scenario">
                <input type="hidden" name="scenarioAction" value="GotoCredit">
                <a href="javascript:scenarioAction.submit()"><p class="trickMenu">Credit</p></a>
            </form>
        </div>
    </div>
    <jsp:include page="bottomMenu.jsp"/>
</div>
<div id="modal">
    <div id="modal-content">
        <img src="imgs/hikusugi.png" alt="Advertisement Image" style="width: 100%; height: auto;">
        <div id="modal-close">
            <button onclick="closeModal()">Close</button>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>