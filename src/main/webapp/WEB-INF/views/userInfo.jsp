<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>個人資料</title>
</head>
<body>
歡迎登入會員系統：
	<table border="1">
		<tr>
			<td colspan="2">個人資料</td>
		</tr>
		<tr>
			<td>姓名</td>
			<td>${requestScope.result.getUname()}</td>
		</tr>
		<tr>
			<td>帳號</td>
			<td>${requestScope.result.getUacct()}</td>
		</tr>
		<tr>
			<td>生日</td>
			<td>${requestScope.result.getUbday()}</td>
		</tr>
		<tr>
			<td>性別</td>
			<td>${requestScope.result.getUsex()}</td>
		</tr>
		<tr>
			<td>信箱</td>
			<td>${requestScope.result.getUmail()}</td>
		</tr>
	</table>

</body>
</html>