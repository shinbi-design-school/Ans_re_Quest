<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="loginform">
<form action="/login" method="post">
ユーザーID：<input type="text" name="id" required><br>
パスワード：<input type="password" name="password" required><br>
<input type="submit" value="ログイン"><br>
</form>
<p>
アカウント登録がお済みでない方はこちらへ↓<br>
<a href="jsp/register.jsp"><button>新規登録</button></a>
</p>
</div>