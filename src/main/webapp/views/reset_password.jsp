<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reset Password</title>
<link rel="stylesheet" href="./css/styles.css">
</head>
<body>
	<div class="container">
		<div class="header">
			<h1>みんなのつぶやき</h1>
		</div>
		<div class="resetpass-form">
			<h2>Reset Password</h2>

			<form action="checkResetPass" method="POST">
				<!-- Password fields -->
				<label for="pass">パスワード</label> <input type="password" name="pass" />
				<br /> <label for="confPass">パスワード確認</label> 
				<input type="password" name="confPass" /> <br /> 
				<input type="hidden" name="stid" value="${ stid }">
				<button type="submit">送信</button>
			</form>
			<c:if test="${not empty error}">
				<div style="color: red;">${error}</div>
				
			</c:if>
			<c:if test="${not empty success}">
				<div style="color: blue;">${success}</div>
				<div class="nav-buttons">
					<button onclick="location.href='./'">トップ</button>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>