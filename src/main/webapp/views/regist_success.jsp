<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>登録成功</title>
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
        
        <div class="success-message">
            <h2>登録が成功しました！</h2>
            <p>以下の情報で登録されました。</p>
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
            <p>ありがとうございました。</p>
        </div>
    </div>
</body>
</html>
