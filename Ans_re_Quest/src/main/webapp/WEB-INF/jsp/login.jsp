<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="loginform">
<form action="login" method="post">
メールアドレス：<input type="text" name="email" required><br>
　パスワード　：<input type="password" name="password" required><br>
<input type="submit" value="ログイン"><br>
        <% 
        Object error = request.getAttribute("error");
        if (error != null) {
        %>
        <%= error %>
        <% } %>
</form>
<p>アカウント登録がお済みでない方はこちらへ↓
    <form method="post" name="form1" action="user">
    <input type="hidden" name="operation" value="new"><a href="javascript:form1.submit()">新規登録</a></form>
</p>	
</div>
