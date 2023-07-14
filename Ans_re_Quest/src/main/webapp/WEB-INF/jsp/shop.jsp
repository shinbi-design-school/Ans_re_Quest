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
 <%--<div class="currency">所持コイン: <%= request.getAttribute("money") %></div> --%>
    
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
                <h2>coming soon…</h2>
            </div>
            <div class="price">$--</div>
            <button>購入する</button>
        </div>  
        <div>
        <div><p style="word-wrap: break-word;" class="shopText">……正直どうでもいいとは思ってるけど<br>対価を払わずに手に入れようとはしない事ね。</p></div>
        <img alt="" src="imgs/scenario/キレルミー_通常.png" class="shopGirl"></div>
        </div>
        <div class="buyAction">
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
        <p>SKIPを購入した</p>
        <% } %>
        </div>
        <jsp:include page="bottomMenu.jsp"></jsp:include>
    </div>
<jsp:include page="footer.jsp"/>
</body>
</html>