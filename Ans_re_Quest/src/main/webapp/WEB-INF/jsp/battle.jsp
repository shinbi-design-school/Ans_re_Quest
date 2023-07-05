<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="head.jsp"/>
    <title>Battle Page</title>
</head>
<body>
<div id="totalMenu">
<div id="bgi">
    <h1><%= request.getAttribute("towerName") %>   <%= request.getAttribute("currentFloor") %>階</h1>
        <img alt="enemy" src="imgs/face.png" id="enemy">
    <p id="enemy">敵のHP: <progress id="file" max="<%=request.getAttribute("enemyMaxHP") %>" value="<%= request.getAttribute("enemyHP") %>"></progress><%= request.getAttribute("enemyHP") %></p>
    <img alt="PLface" src="imgs/face.png" id="player">
    <p id="player">プレイヤーのHP:  <input type="range" id="inputSlider" min="0" max="<%=request.getAttribute("playerMaxHP") %>" value="<%= request.getAttribute("playerHP") %>" /><%= request.getAttribute("playerHP") %></p>
    <p>第<%= request.getAttribute("currentQuizNo") %>問/全<%= request.getAttribute("totalQuizCount") %>問</p>
    <p>問題: <%= request.getAttribute("questionText") %></p>
    <form method="post" action="battle" id="flex">
        <button type="submit" name="choice" id="box" value="<%= (String)request.getAttribute("choice1") %>"><%= request.getAttribute("choice1") %></button><br>
        <button type="submit" name="choice" id="box" value="<%= (String)request.getAttribute("choice2") %>"><%= request.getAttribute("choice2") %></button><br>
        <button type="submit" name="choice" id="box" value="<%= (String)request.getAttribute("choice3") %>"><%= request.getAttribute("choice3") %></button><br>
        <button type="submit" name="choice" id="box" value="<%= (String)request.getAttribute("choice4") %>"><%= request.getAttribute("choice4") %></button><br>
    </form>
    <p>AI使ったらパラメーターに使用の有無情報が伝わるように設定予定</p>
    <p>Open AI Answer: <%= request.getAttribute("aiAnswer") %></p>
    <p>50/50: <%= request.getAttribute("50/50Count") %>個</p>
    <p>SKIP: <%= request.getAttribute("skipCount") %>個</p>
    
</div>
</div>
<jsp:include page="footer.jsp"/>

</body>
</html>
