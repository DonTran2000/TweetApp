<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <title>学生登録確認</title>
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
            <h2>登録情報の確認</h2>
            <c:choose>
                <c:when test="${not empty errors}">
                    <div class="error-messages">
                        <ul>
                            <c:forEach var="error" items="${errors}">
                                <li>${error}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:when>
                <c:otherwise>
                    <p style="color: blue;">以下の情報を確認してください。</p>
                    <table>
                        <tr>
                            <th>学籍番号</th>
                            <td>${RegistForm.id}</td>
                        </tr>
                        <tr>
                            <th>名前</th>
                            <td>${RegistForm.name}</td>
                        </tr>
                        <tr>
                            <th>所属学科</th>
                            <td>${RegistForm.deptName}</td>
                        </tr>
                    </table>
                    <form action="submit" method="POST">
                        <input type="hidden" name="id" value="${RegistForm.id}" />
                        <input type="hidden" name="name" value="${RegistForm.name}" />
                        <input type="hidden" name="dept" value="${RegistForm.dept}" />
                        <input type="hidden" name="pass" value="${RegistForm.pass}" />
                        <input type="hidden" name="confPass" value="${RegistForm.confPass}" />
                        <div class="button-group">
                            <button type="submit">登録確定</button>
                            <button type="button" onclick="history.back()">戻る</button>
                        </div>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>
