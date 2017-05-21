<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>添加用户</title>
    <style>
        .container {
            width: 300px;
            margin: 10px auto;
        }
    </style>
</head>
<body>
<div class="container">
    <ul>
        <c:forEach items="${authUserList}" var="authUser">
            <li>${authUser.userName}</li>
        </c:forEach>
    </ul>
</div>
</body>
</html>

