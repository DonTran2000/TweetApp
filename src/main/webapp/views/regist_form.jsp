<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<title>新規学生登録</title>
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
	
	
		<div class="regist-form">
			<h2>新規学生登録</h2>
			
			<f:form action="check" method="POST" modelAttribute="RegistForm">
				<p><f:errors path="*" cssClass="error" /></p>
				
				<label for="id">学籍番号</label>
				<f:input type="text" name="id" path="id" />
				<br />
				<label for="name">名前</label>
				<f:input type="text" size="20" path="name" />
				<br />
				<label for="dept">所属学科</label>
				<%-- <f:select items="${deptItems}" path="dept" itemValue="deptcode" itemLabel="deptname" / --%>
				<f:select path="dept">
                    <option value="" label="選択してください" disabled="disabled" selected="selected" style="text-align: center">
                    <f:options items="${deptItems}" itemValue="deptcode" itemLabel="deptname" style="text-align: center"/>
                </f:select>
				<br />
				<label for="pass">パスワード</label>
				<f:input type="password" size="20" path="pass" />
				<br />
				<label for="confPass">パスワード確認</label>
				<f:input type="password" size="20" path="confPass" />
				<br />
				<button type="submit">送信</button>
			</f:form>
			
		</div>
	</div>
	
	
	
</body>
</html>