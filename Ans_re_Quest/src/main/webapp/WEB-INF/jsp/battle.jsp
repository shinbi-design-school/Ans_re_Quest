<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
Boolean isUsed5050 = (Boolean) request.getAttribute("isUsed5050");
if (isUsed5050 == null) {
    isUsed5050 = false;
}

Boolean isUsedSkip = (Boolean) request.getAttribute("isUsedSkip");
if (isUsedSkip == null) {
    isUsedSkip = false;
}

Boolean isNoHint = (Boolean) request.getAttribute("isNoHint");
if (isNoHint == null) {
    isNoHint = false;
}
int quantity5050 = (int)request.getAttribute("quantity5050");
int quantitySkip = (int)request.getAttribute("quantitySkip");
%>    

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
    <div class="enemy">
    	<p><%=request.getAttribute("enemyName") %></p>
    	<img alt="enemy" src="imgs/face.png" id="enemy">
    	<p id="enemy">HP: <progress id="file" max="<%=request.getAttribute("enemyMaxHP") %>" value="<%= request.getAttribute("enemyHP") %>"></progress><%= request.getAttribute("enemyHP") %></p>
    </div>
    <div class="player">
    	<p><%=request.getAttribute("Name") %></p>
	    <img alt="PLface" src="imgs/face.png" id="player">
	    <p id="player">HP:  <input type="range" id="inputSlider" min="0" max="<%=request.getAttribute("playerMaxHP") %>" value="<%= request.getAttribute("playerHP") %>" /><%= request.getAttribute("playerHP") %></p>
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

    <p></p>
    <div class="helpItems">
		<script>
    function startTypewriter() {
        var text = "<%= request.getAttribute("aiAnswer") %>"; // 表示したいテキスト
        var speed = 100; // 文字を表示する速度（ミリ秒単位）

        var i = 0;
        function typeWriter() {
            if (i < text.length) {
                document.getElementById("typewriter").textContent += text.charAt(i);
                i++;
                setTimeout(typeWriter, speed);
            }
        }

        // タイプライターエフェクトを開始する
        typeWriter();
    }
    function hideButtonText() {
        var aiButton = document.getElementById("aiButton");
        aiButton.style.display = "none";
        
        var form = document.getElementById("flex-questions");
        var isUsedAiInput = document.createElement("input");
        isUsedAiInput.type = "hidden";
        isUsedAiInput.name = "isUsedAi";
        isUsedAiInput.value = "true";
        form.appendChild(isUsedAiInput);
    }
</script>
	<button onclick="startTypewriter(); hideButtonText()" id="aiButton">AIに聞く</button>
	<div id="typewriter"></div>
	
    <!--  5050アイテム0または使用済みなら隠す -->
    <% if(isUsed5050){ %>
	<p>5050使用済み<p>
	<%} %>    
	<% if(!(isNoHint || isUsed5050 || isNoHint || (quantity5050 <= 0))){ %>
    <form method="post" name="form1" action="battle">
    <input type="hidden" name="isUsed5050" value="true">
    <a href="javascript:form1.submit()">
    	50/50: <%= quantity5050 %>個</a>
	</form>
	<%} %>
	<!--  SKIPアイテム0なら隠す -->
	    <% if(isUsedSkip){ %>
	<p>Skip使用した<p>
	<%} %>    
	<% if(!(isNoHint || (quantitySkip <= 0) )){ %>
    <form method="post" name="form2" action="battle" class="skip">
	    <input type="hidden" name="isUsedSkip" value="true">
	    <a href="javascript:form2.submit()">
		SKIP: <%= quantitySkip %>個 </a>
	<%} %>	
		
	</form>
	</div>
</div>
</div>
<jsp:include page="footer.jsp"/>

</body>
</html>
