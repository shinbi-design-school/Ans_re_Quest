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
    
 <div class="currency">所持コイン: 100</div>
    
       <div class="item-container">
        <div class="item">
            <div class="item-wrapper">
                <h2>アイテム1</h2>
            </div>
            <div class="price">$10</div>
            <button>購入する</button>
        </div>
        
        <div class="item">
            <div class="item-wrapper">
                <h2>アイテム2</h2>
            </div>
            <div class="price">$20</div>
            <button>購入する</button>
        </div>
        
        <div class="item">
            <div class="item-wrapper">
                <h2>アイテム3</h2>
            </div>
            <div class="price">$30</div>
            <button>購入する</button>
        </div>  
        
        </div>  
        <jsp:include page="bottomMenu.jsp"></jsp:include>
    </div>
</body>
</html>