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
    <!-- <jsp:include page="moneyFrame.jsp"/> ファイルが見当たらないためコメントアウト-->
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
                <h2>5050(<%= request.getAttribute("5050Quantity") %>)</h2>
            </div>
            <div class="price">$20</div>
				<form method="post" name="form1" action="shop" class="5050">
					<input type="hidden" name="operation" value="buy5050">
					<button type="submit">購入する</button>
				</form>
			</div>
        
        <div class="item">
            <div class="item-wrapper">
                <h2>Skip(<%= request.getAttribute("skipQuantity") %>)</h2>
            </div>
            <div class="price">$10</div>
				<form method="post" name="form2" action="shop" class="skip">
					<input type="hidden" name="operation" value="buySkip">
					<button type="submit">購入する</button>
				</form>
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