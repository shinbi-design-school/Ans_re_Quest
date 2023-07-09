<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%
    List<Integer> patterns = (List<Integer>)request.getAttribute("patterns");
    int pattern1 = patterns.get(0);
    int pattern2 = patterns.get(1);
    %>

    <h1>RPGイベントフロア</h1>
    <p>現在のパターン: <%= pattern1 %></p>

    <% if (pattern1 == 1) { %>
        <h2>パターン1のイベント</h2>
        <p>5050Quantityを<%= pattern2 %>個手に入れた。</p>
        <a href="battle">特定階層でのイベント画面</a>
    <% } else if (pattern1 == 2) { %>
        <h2>パターン2のイベント</h2>
        <p>SkipQuantityを<%= pattern2 %>個手に入れた。</p>
        <a href="battle">特定階層でのイベント画面</a>
    <% } else if (pattern1 == 3) { %>
    	<h2>パターン3のイベント</h2>
        <form method="post" name="form1" action="battle">
  		<input type="hidden" name="isConfused" value="true">
   		<a href="javascript:form1.submit()">
        5050QuantityとSkipQuantityを１つずつ手に入れた。
        特定階層でのイベント画面</a>
	</form>

    <% } else { %>
        <h2>無効なパターンです。</h2>
        <p>有効なパターンを選択してください。</p>
    <% } %>
</body>
</html>