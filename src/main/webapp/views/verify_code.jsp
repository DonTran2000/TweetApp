<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
    <title>検証コード</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="./css/styles.css">
</head>
<body>
    <div class="container">
        <h2>検証コード</h2>
        <form action="verifyCode" method="post">
                <input type="hidden" name= "stid" value="${stid}">
            
                <label for="code">検証コード: </label>
                <input type="text" name="code" required >
           
                <button type="submit">検証</button>
            
        </form>
        <c:if test="${not empty error}">
            <div style="color: red;">
                ${error}
            </div>
        </c:if>
    </div>
</body>
</html>
