<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="shop.css">
<jsp:include page="head.jsp"/>
    
    <title>shop</title>
</head>
<body>
<div id="totalMenu">
    <jsp:include page="moneyFrame.jsp"/>
    <h1>ショップ</h1>
        <% 
        Object action = request.getAttribute("isBuy5050");
        if (action != null) {
        %>
        <p>5050を購入した</p>
        <% } %>
        <%
        Object action2 = request.getAttribute("isBuySkip");
        if (action2 != null) {
        %>
        <p>5050を購入した</p>
        <% } %>
 <div class="currency">所持コイン: <%= request.getAttribute("money") %></div>
    
       <div class="item-container">
        <div class="item">
            <div class="item-wrapper">
                <h2>アイテム1(<%= request.getAttribute("5050Quantity") %>)</h2>
            </div>
            <div class="price">$20</div>
            <button>購入する</button>
        </div>
        
        <div class="item">
            <div class="item-wrapper">
                <h2>アイテム2(<%= request.getAttribute("skipQuantity") %>)</h2>
            </div>
            <div class="price">$10</div>
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