<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>つぶやく</title>
	<link rel="stylesheet" href="./css/styles.css">
</head>
<body>
	<div class="container">
		<div class="header">
			<h1>みんなのつぶやき</h1>
		</div>
		<div class="nav-buttons">
			<c:if test="${ user != null }">	<!-- ログイン中 -->
				<p> ${ user }</p>
				<button onclick="location.href='./'">トップ</button>
            	<button onclick="location.href='./logout'">ログアウト</button>
			</c:if>
        </div>
        
        <div class="tweet">
        	<f:form action="tweetProcess" method="POST" modelAttribute="TweetForm">
        		<f:textarea path="content" rows="5" cols="50" required="required"></f:textarea>
        		<br />
        		<input type="submit" value="つぶやく">
        	</f:form>
        </div>
	</div>
</body>
</html>