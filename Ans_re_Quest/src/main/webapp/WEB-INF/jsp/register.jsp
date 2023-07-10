<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
</head>
<body>
<form action="user" method="post">
<%= request.getAttribute("error") %>
<p>すべて入力してください</p>
<p>
<input type="radio" name=is_admin value="true">管理者で登録する
<input type="radio" name="is_admin" value="false" checked>ユーザーで登録する
</p>
名前：<input type="text" name="name" required><br>
メールアドレス：<input type="email" name="email" required><br>
パスワード：<input type="password" name="password" required><br>
パスワード(確認)：<input type="password" name="confirmed" required><br>
<input type="submit" value="登録"><br>
<input type="hidden" name="operation" value="add">
</form>
</body>
</html>
