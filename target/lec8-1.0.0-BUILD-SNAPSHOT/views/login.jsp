<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./resources/style.css">
<title>Login Page</title>
<script>
	function showPass() {
		var pass = document.getElementById("pass");
		var toggleIcon = document.getElementById("toggleBtn").querySelector("img");
		if (pass.type === "password") {
			pass.type = "text";
			toggleIcon.src = "./resources/show.png";
			toggleIcon.alt = "Show Password";
		} else {
			pass.type = "password";
			toggleIcon.src = "./resources/hide.png";
			toggleIcon.alt = "Hide Password";
		}
	}
</script>
</head>
<body>
	<h1>ログイン</h1>
	<p>
		<span>${message }</span>
	</p>
	<f:form action="login" modelAttribute="LoginSes" method="POST">
		<label for="id">ログイン：</label> 
		<f:input type="text" name="id" path="id"/>
		<br />
		<label for="pass">パスワード：</label> 
		<f:input type="password" name="pass" id="pass" path="pass"/>
		<button type="button" onclick="showPass()" id="toggleBtn">
			<img src="./css/images/hide.png" alt="Hide Password">
		</button>
		<p>
			<input type="submit" value="ログイン" />
			<a href="./" class="button">キャンセル</a>
		</p>
	</f:form>

</body>
</html>