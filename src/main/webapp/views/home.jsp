<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>みんなのつぶやき</title>
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
				<button onclick="location.href='./tweetForm'">つぶやく</button>
            	<button onclick="location.href='./logout'">ログアウト</button>
			</c:if>
            
            <c:if test="${ user eq null }">	<!-- 非ログイン -->
            	<p> ${ regist_success_mess }</p>
				<button onclick="location.href='./registForm'">新規登録</button>
            	<button onclick="location.href='./login'">ログイン</button>
			</c:if>
        </div>
		<div class="posts">
			<div class="post">
				<c:forEach var="tw" items="${result}">
					<p>${ tw.content }</p>
					<small>by ${ tw.student.name } (${ tw.student.department.deptname}, <fmt:formatDate value="${tw.tweettime}" pattern="yyyy-MM-dd HH:mm:ss" />)</small>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>
