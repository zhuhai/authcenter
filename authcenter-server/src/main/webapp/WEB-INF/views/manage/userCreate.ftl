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
        <form action="/authUser/create" method="post">
            <span>用户名：</span><input type="text" name="userName"/><br>
            <span>密码：</span><input type="password" name="password"/><br>
            <span>部门：</span><input type="text" name="organizationId"/><br>
            <button type="submit">提交</button>
        </form>
    </div>
</body>
</html>
