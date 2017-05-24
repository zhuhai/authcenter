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
        <#if authUserList??>
            <#list authUserList as authUser>
                <li>${authUser.userName}</li>
            </#list>
        </#if>


    </ul>
</div>
</body>
</html>

