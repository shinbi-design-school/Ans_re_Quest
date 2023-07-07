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
    <div>
    	<img alt="enemy" src="imgs/face.png" id="enemy">
    	<p id="enemy">敵のHP: <progress id="file" max="<%=request.getAttribute("enemyMaxHP") %>" value="<%= request.getAttribute("enemyHP") %>"></progress><%= request.getAttribute("enemyHP") %></p>
    </div>
    <div>
	    <img alt="PLface" src="imgs/face.png" id="player">
	    <p id="player">プレイヤーのHP:  <input type="range" id="inputSlider" min="0" max="<%=request.getAttribute("playerMaxHP") %>" value="<%= request.getAttribute("playerHP") %>" /><%= request.getAttribute("playerHP") %></p>
    </div>
    <div class="questions">
    	<p>第<%= request.getAttribute("currentQuizNo") %>問/全<%= request.getAttribute("totalQuizCount") %>問</p>
    	<p>問題: <%= request.getAttribute("questionText") %></p>
    	<p>制限時間:<%= request.getAttribute("limitTime") %></p>
    </div>
    <form method="post" action="battle" id="flex-questions">
        <button type="submit" name="choice" id="box" value="<%= (String)request.getAttribute("choice1") %>"><%= request.getAttribute("choice1") %></button><br>
        <button type="submit" name="choice" id="box" value="<%= (String)request.getAttribute("choice2") %>"><%= request.getAttribute("choice2") %></button><br>
        <button type="submit" name="choice" id="box" value="<%= (String)request.getAttribute("choice3") %>"><%= request.getAttribute("choice3") %></button><br>
        <button type="submit" name="choice" id="box" value="<%= (String)request.getAttribute("choice4") %>"><%= request.getAttribute("choice4") %></button><br>
    <input type="hidden" name="isUsed5050" value="false">
    </form>
	
	<!-- 修正 -->

    <p>//AIやアイテムを使ったらパラメーターに使用の有無情報が伝わるように設定予定//</p>
    <div class="helpItems">
    <p>Opened AI Answer: <%= request.getAttribute("aiAnswer") %> →parameter=isUsedAI</p>
    
    <!--  5050アイテム0または使用済みなら隠す -->
    <% request.getAttribute("isUsed5050"); %>
    <form method="post" name="form1" action="battle" class="half">
    <input type="hidden" name="isUsed5050" value="true">
    <a href="javascript:form1.submit()">
    	50/50: <%= request.getAttribute("5050Quantity") %>個</a>
	</form>
	
	<!--  SKIPアイテム0なら隠す -->
    <form method="post" name="form2" action="battle" class="skip">
	    <input type="hidden" name="isUsedSKIP" value="true">
	    <a href="javascript:form2.submit()">
		<p>SKIP: <%= request.getAttribute("skipQuantity") %>個 </p>
		</a>
	</form>
	</div>
</div>
</div>
<jsp:include page="footer.jsp"/>

</body>
</html>
