<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>忘記密碼</title>
</head>
<body>
忘記密碼，請輸入你的帳號，將重新寄送一組密碼到你的信箱：
<!-- <c:url value="test"/> -->
<form action="" method="post">
	<table>
        <tr>
            <td>輸入帳號：</td>
            <td><input type="text" name="account" id="Uaccount" required="required" />
            </td>
           <!--  <td>帳號只能是英文、數字、底線、減號與小數點，其餘字元均不接受</td> -->
        </tr>
        <tr>
            <td colspan="4" style="text-align:center; ">
                    <input type="hidden" name="action" value="reg" />
                    <input type="submit" value="寄送信件" />
            </td>
        </tr>
	</table>
</form>

<c:choose>
	<c:when test="${not empty mail}">
		信箱：${mail}。
	</c:when>
	<c:otherwise>
		請輸入正確的帳號
	</c:otherwise>
</c:choose>
</body>
</html>