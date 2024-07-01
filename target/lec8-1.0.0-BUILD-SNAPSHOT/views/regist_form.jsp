<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>新規学生登録</title>
</head>
<body>
	<h1>みんなのつぶやき</h1>
	<h2>新規学生登録</h2>
	<f:form action="check" method="POST" modelAttribute="LoginSes">
		<label for="id">学籍番号：</label>
		<f:input type="text" name="id" path="id" />
		<br />
		<div class="error">
			<f:errors path="id" />
		</div>
		<br />
		<label for="name">名前</label>
		<f:input type="text" size="20" path="name" />
		<div class="error">
			<f:errors path="name" />
		</div>
		<br />
		<label for="dept">所属学科</label>
		<f:select items="${deptItems}" path="dept" itemValue="deptcode"
			itemLabel="deptname" />
		<div class="error">
			<f:errors path="dept" />
		</div>
		<br />
		<label for="pass">パスワード</label>
		<f:input type="passwaord" size="20" path="pass" />
		<div class="error">
			<f:errors path="pass" />
		</div>
		<br />
		<label for="confPass">パスワード確認</label>
		<f:input type="passwaord" size="20" path="confPass" />
		<div class="error">
			<f:errors path="confPass" />
		</div>
		<br />
		<p>
			<input type="submit" value="送信" /> <a href="./" class="button">キャンセル</a>
		</p>
	</f:form>
</body>
</html>