<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
    <title>Battle Page</title>
</head>
<body>
    <h1>クイズバトル</h1>
    <p>問題: <%= request.getAttribute("questionText") %></p>
    <form method="post" action="battle">
        <button type="submit" name="choice" value="<%= request.getAttribute("choice1") %>"><%= request.getAttribute("choice1") %></button><br>
        <button type="submit" name="choice" value="<%= request.getAttribute("choice2") %>"><%= request.getAttribute("choice2") %></button><br>
        <button type="submit" name="choice" value="<%= request.getAttribute("choice3") %>"><%= request.getAttribute("choice3") %></button><br>
        <button type="submit" name="choice" value="<%= request.getAttribute("choice4") %>"><%= request.getAttribute("choice4") %></button><br>
    </form>
    <p>プレイヤーのHP: <%= request.getAttribute("playerHP") %></p>
    <p>敵のHP: <%= request.getAttribute("enemyHP") %></p>
    <% if (!(Boolean) request.getAttribute("isPlayerAlive")) { %>
        <p>プレイヤーは敗北しました。</p>
    <% } else if (!(Boolean) request.getAttribute("isEnemyAlive")) { %>
        <p>敵を倒しました！</p>
    <% } %>
</body>
</html>
