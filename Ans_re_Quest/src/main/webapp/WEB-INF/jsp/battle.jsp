<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
    <title>Battle Page</title>
</head>
<body>
    <h1>クイズバトル</h1>
    <p>第<%= request.getAttribute("currentQuizNo") %>問/全<%= request.getAttribute("totalQuizCount") %>問</p>
    <p>問題: <%= request.getAttribute("questionText") %></p>
    <form method="post" action="battle">
        <button type="submit" name="choice" value="<%= request.getAttribute("choice1") %>"><%= request.getAttribute("choice1") %></button><br>
        <button type="submit" name="choice" value="<%= request.getAttribute("choice2") %>"><%= request.getAttribute("choice2") %></button><br>
        <button type="submit" name="choice" value="<%= request.getAttribute("choice3") %>"><%= request.getAttribute("choice3") %></button><br>
        <button type="submit" name="choice" value="<%= request.getAttribute("choice4") %>"><%= request.getAttribute("choice4") %></button><br>
    </form>
    <p><%= request.getAttribute("towerName") %></p> 
    <p>階層: <%= request.getAttribute("currentFloor") %></p>
    
    <p>プレイヤーのHP: <%= request.getAttribute("playerHP") %> / <%= request.getAttribute("playerMaxHP") %></p>
    <p>敵：<%= request.getAttribute("enemyName") %>のHP: <%= request.getAttribute("enemyHP") %> / <%= request.getAttribute("enemyMaxHP") %></p>
    <% if (!(Boolean) request.getAttribute("isPlayerAlive")) { %>
        <p>プレイヤーは敗北しました。</p>
    <% } else if (!(Boolean) request.getAttribute("isEnemyAlive")) { %>
        <p>敵を倒しました！</p>
    <% } %>
</body>
</html>
