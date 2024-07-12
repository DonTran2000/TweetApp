<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forgot Password</title>
<link rel="stylesheet" href="./css/styles.css">
</head>
<body>
	<div class="container">
		<div class="header">
			<h1>みんなのつぶやき</h1>
		</div>
		<div class="nav-buttons">
			<button onclick="location.href='./'">トップ</button>
		</div>

		<div class="forgotpassword-form">
			<h2>Forgot Password</h2>
			<f:form action="sendVerificationCode" method="POST"
				modelAttribute="ForgotPasswordForm">
				<p style="color: red">
					<f:errors path="*" />
				</p>
				<label for="id">学籍番号</label>
				<f:input type="text" name="id" path="stid" />
				<label for="dept">所属学科</label>
				<f:select path="deptcode">
					<option value="" label="選択してください" disabled="disabled"
						selected="selected" style="text-align: center">
						<f:options items="${deptItems}" itemValue="deptcode"
							itemLabel="deptname" style="text-align: center" />
				</f:select>
				<br />
				<label for="email">メール</label>
				<f:input type="email" size="20" path="email" />
				<br />
				<button type="submit">送信</button>
			</f:form>

			<c:if test="${not empty error}">
				<div style="color: red;">${error}</div>
			</c:if>
		</div>
	</div>
</body>
</html>