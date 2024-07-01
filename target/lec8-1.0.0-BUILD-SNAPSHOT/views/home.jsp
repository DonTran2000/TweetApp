<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>みんなのつぶやき</title>
</head>
<body>
	<h1>みんなのつぶやき</h1>
	<P>${hello}</P>
	<a href="${action1}">${actionName1}</a>
	<a href="${action2}">${actionName2}</a>
	<hr />
	<c:forEach var="tw" items="${result}">
		<p>${tw.content}<br />
		by ${tw.student.name}(${tw.student.department.deptname}, <fmt:formatDate value="${tw.tweettime}" pattern="yyyy-MM-dd HH:mm:ss" />)
		</p>
	</c:forEach>
</body>
</html>
