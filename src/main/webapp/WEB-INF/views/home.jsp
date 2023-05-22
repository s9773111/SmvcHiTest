<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登入會員</title>
</head>
<body>
	<h1>歡迎來到 會員中心</h1>
	
	<form id="loginform" action="<c:url value="/login"/>" method="post"
        style="width: 270px; border: 1px solid gray; padding: 5px; text-align: center;">
	<table style="text-align: left;">
    	<tr>
        	<td>登入帳號:</td>
            <td><input type="text" name="account" required="required" id='login_name' style="width:150px"/></td>
        </tr>
        <tr>
            <td>登入密碼:</td>
            <td><input type="password" name="password" required="required" id='login_pass' style="width:150px"/></td>
        </tr>
        <tr>
            <td colspan="4" style="text-align:center;">
                <input type="hidden" name="action" value="login"/>
                <input type="submit" value="登入"/>
            </td>
        </tr>

	</table>
	</form>

	<br/>
	<c:choose>
		<c:when test="${not empty result}">
			${result}
		</c:when>
		<c:otherwise>
			請登入
		</c:otherwise>
	</c:choose>
	<br/>
	
	<a href="<c:url value="/register"/>">註冊</a> |
	<a href="<c:url value="/password"/>">忘記密碼</a>
</body>
</html>