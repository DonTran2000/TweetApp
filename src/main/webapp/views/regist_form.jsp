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
	
	<script>
        function togglePasswordVisibility() {
            var passField = document.getElementById("pass");
            var confPassField = document.getElementById("confPass");
            var passToggleBtn = document.getElementById("passToggleBtn");

            if (passField.type === "password") {
                passField.type = "text";
                confPassField.type = "text";
                passToggleBtn.textContent = "Hide Password";
            } else {
                passField.type = "password";
                confPassField.type = "password";
                passToggleBtn.textContent = "Show Password";
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
	
	
		<div class="regist-form">
			<h2>新規学生登録</h2>
			
			<!-- Display messages -->
		    <c:if test="${not empty stExist_mess}">
		        <p style="color: red;">${stExist_mess}</p>
		    </c:if>
		    
		    <c:if test="${not empty mess_pass}">
		        <p style="color: red;">${mess_pass}</p>
		    </c:if>
		    
		    <c:if test="${not empty mess_error}">
		        <p style="color: red;">${mess_error}</p>
		    </c:if>
			
			<f:form action="check" method="POST" modelAttribute="RegistForm">
				<p style="color: red"><f:errors path="*" /></p>
				
				<label for="id">学籍番号</label>
				<f:input type="text" name="id" path="id" />
				<br />
				<label for="name">名前</label>
				<f:input type="text" size="20" path="name" />
				<br />
				<label for="dept">所属学科</label>
				<f:select path="dept">
                    <option value="" label="選択してください" disabled="disabled" selected="selected" style="text-align: center">
                    <f:options items="${deptItems}" itemValue="deptcode" itemLabel="deptname" style="text-align: center"/>
                </f:select>
				<br />
				<!-- Password fields -->
				<label for="pass">パスワード</label>
				<f:input type="password" size="20" path="pass" />
				<br />
					
				<label for="confPass">パスワード確認</label>
				<f:input type="password" size="20" path="confPass" />
				<br />
				
				 <!-- Button to toggle password visibility -->
		        <button type="button" id="passToggleBtn" onclick="togglePasswordVisibility()">Show Password</button>
		        <br />
		        <br />
				
				<button type="submit">送信</button>
			</f:form>
			
		</div>
	</div>
	
	
	
</body>
</html>