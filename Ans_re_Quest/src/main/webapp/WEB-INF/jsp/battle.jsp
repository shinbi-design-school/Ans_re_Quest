<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="head.jsp"/>
    <title>Battle Page</title>
</head>
<body>
<div id="bgi">
    <h1>クイズバトル</h1>
    <img alt="enemy" src="imgs/face.png" id="enemy">
    <p id="enemy">敵のHP: <progress id="file" max="30" value="<%= request.getAttribute("enemyHP") %>"></progress><%= request.getAttribute("enemyHP") %></p>
    <img alt="PLface" src="imgs/face.png">
    <p>プレイヤーのHP:  <input type="range" id="inputSlider" min="0" max="30" value="<%= request.getAttribute("playerHP") %>" /><%= request.getAttribute("playerHP") %></p>
    <div id="question">
    <p>問題: <%= request.getAttribute("questionText") %></p>
    <form method="post" action="battle" id="flex">
        <button type="submit" name="choice" id="box" value="<%= (String)request.getAttribute("choice1") %>"><%= request.getAttribute("choice1") %></button><br>
        <button type="submit" name="choice" id="box" value="<%= (String)request.getAttribute("choice2") %>"><%= request.getAttribute("choice2") %></button><br>
        <button type="submit" name="choice" id="box" value="<%= (String)request.getAttribute("choice3") %>"><%= request.getAttribute("choice3") %></button><br>
        <button type="submit" name="choice" id="box" value="<%= (String)request.getAttribute("choice4") %>"><%= request.getAttribute("choice4") %></button><br>
    </form>
    </div>
    <% if (!(Boolean) request.getAttribute("isPlayerAlive")) { %>
        <p>プレイヤーは敗北しました。</p>
    <% } else if (!(Boolean) request.getAttribute("isEnemyAlive")) { %>
        <p>敵を倒しました！</p>
    <% } %>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
