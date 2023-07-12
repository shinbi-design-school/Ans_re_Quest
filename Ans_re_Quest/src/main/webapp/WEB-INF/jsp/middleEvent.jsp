<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css">
<jsp:include page="head.jsp"/>
<title>Insert title here</title>
</head>
<body>
<div id="totalMenu">
    <%
    List<Integer> patterns = (List<Integer>)request.getAttribute("patterns");
    int pattern1 = patterns.get(0);
    int pattern2 = patterns.get(1);
    %>

    <h3>イベントフロア</h3>
    <p>イベント<%= pattern1 %></p>
<div class="middle_event_window">
    <% if (pattern1 == 1) { %>
        <h2>アイテムに目がくらんで<br>罠にかかってしまった！</h2>
        <form method="post" name="form1" action="battle">
  		<input type="hidden" name="isSerious" value="true">
        <p>アイテム:5050を<%= pattern2 %>個手に入れた。</p>
        <p>まじめかの罠を踏んだ（アイテム使用不可）</p>
        <p><a href="javascript:form1.submit()">次へ</a></p>
        </form>
    <% } else if (pattern1 == 2) { %>
        <h2>アイテムに目がくらんで<br>罠にかかってしまった！</h2>
        <form method="post" name="form2" action="battle">
  		<input type="hidden" name="isImpatient" value="true">
        <p>アイテム:Skipを<%= pattern2 %>個手に入れた。</p>
        <p>あせりの罠を踏んだ（制限時間減少）</p>
		<p><a href="javascript:form2.submit()">次へ</a></p>
		</form>
    <% } else if (pattern1 == 3) { %>
    	<h2>アイテムに目がくらんで<br>罠にかかってしまった！</h2>
        <form method="post" name="form3" action="battle">
  		<input type="hidden" name="isConfused" value="true">
        <p>アイテム:5050・Skipを１つずつ手に入れた。</p>
        <p>混乱の罠を踏んだ。（問題文把握困難）	</p>
        <p><a href="javascript:form3.submit()">次へ</a></p>
		</form>

    <% } else { %>
        <h2>無効なパターンです。</h2>
        <p>有効なパターンを選択してください。</p>
    <% } %>
    </div>
    </div>
</body>
</html>