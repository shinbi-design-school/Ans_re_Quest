<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css">
<jsp:include page="head.jsp"/>
    
    <title>guide</title>
</head>
<body>
<div class="spaceBar"></div>
<div id="totalMenu">
<h1>ガイド</h1>

<div class="details_container">

<details>
<summary>ログイン/ゲストモード</summary>
<p>ログインすることでゲームのデータが保存されます。めんどうな方はゲストでどうぞ。</p>
</details>
<details>
<summary>クエスト</summary>
<p>制限時間内に4択の中から正解を選び、敵のHPを0にすれば次の階層へ進むことができます。
<br>どうしてもわからない場合、ヒントと5050・skipのアイテムを使いクリアを目指しましょう。</p>
</details>
<details>
<summary>マイページ</summary>
<p>プレイヤーの現在の状態が表示されています。<br>メニューからITEM(所持品)、RANKING(順位)、ADVERTISEMENT(広告)、Credit(開発者)を見ることができる予定です。</p>
</details>
<details>
<summary>ショップ</summary>
<p>ゲーム内コインを消費してアイテムを購入することができます。</p>
</details>

        <jsp:include page="bottomMenu.jsp"></jsp:include>
    </div>
</body>
</html>