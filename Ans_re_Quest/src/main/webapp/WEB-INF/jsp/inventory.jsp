<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css">
    <jsp:include page="head.jsp"/>
    <title>アイテム所持数</title>
</head>
<body>
    <div id="totalMenu">
        <jsp:include page="moneyFrame.jsp"/>
        <h1>アイテム所持数</h1>
        <div class="item-container">
            <div class="item">
                <div class="item-wrapper">
                    <h2>5050<br>（所持数：<%= request.getAttribute("5050Quantity") %>）</h2>
                </div>
                <div class="price"></div>
            </div>
            <div class="item">
                <div class="item-wrapper">
                    <h2>Skip<br>（所持数：<%= request.getAttribute("skipQuantity") %>）</h2>
                </div>
                <div class="price"></div>
            </div>
        </div>

    <jsp:include page="bottomMenu.jsp"></jsp:include>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>