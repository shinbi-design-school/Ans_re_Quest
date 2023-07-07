<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp"/>
    <link rel="stylesheet" type="text/css" href="shop.css">
    <title>shop</title>
</head>
<body>
<div id="totalMenu">
    <h1>ショップ</h1>
    
    <div class="item">
        <img src="item1.png" alt="アイテム1">
    	<p>価格: $10</p>
        <button>購入</button>
    </div>
    
    <div class="item">
        <img src="item2.png" alt="アイテム2">
        <p>価格: $20</p>
        <button>購入</button>
    </div>
    
    <div class="item">
        <img src="item3.png" alt="アイテム3">
        <p>価格: $30</p>
        <button>購入</button>
        
        <jsp:include page="bottomMenu.jsp"></jsp:include>
    </div>
</body>
</html>