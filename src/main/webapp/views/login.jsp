<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="./css/styles.css">
	<title>Login Page</title>
<script>
	function myFunction() {
		var x = document.getElementById("pass");
		if (x.type === "password") {
			x.type = "text";
		} else {
			x.type = "password";
		}
	}
</script>
</head>
<body>
	<div class="container">
		<div class="header">
			<h1>みんなのつぶやき</h1>
		</div>
		<div class="nav-buttons">
			<button onclick="location.href='./'">トップ</button>
		</div>
		
		<div class="login-form">
			<h2>ログイン</h2>
			<p> ${ message }</p>
			<f:form action="loginProcess" modelAttribute="LoginSes" method="POST">
				<label for="id">学籍番号</label> 
				<f:input type="text" name="id" path="id"/>
				<br />
				<label for="pass">パスワード</label> 
				<f:input type="password" id="pass" path="pass"/>
				<br />
				<label for="showPass">Show Password</label>
				<input type="checkbox" id="showPass" onclick="myFunction()">
				<br />
				<br />
				<button type="submit">ログイン</button>
	            <button type="reset">クリア</button>
			</f:form>
		</div>
	</div>
</body>
</html>