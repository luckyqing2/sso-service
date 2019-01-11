<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>SSO-SERVICE</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
   <link rel='Shortcut Icon' type='image/x-icon' href='${pageContext.request.contextPath}/img/icon/logogreen3232.png'>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/myAlert.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/component/layer-v3.0.3/layer/layer.js"></script>
<style>
    html{
        width: 100%;
        height: 100%;
        overflow: hidden;
        font-style: normal;
    }
    body{
        width: 100%;
        height: 100%;
        font-family: 'Open Sans',sans-serif;
        margin: 0;
        background-color: #4A374A;
    }
    #login{
        position: absolute;
        top: 50%;
        left:50%;
        margin: -150px 0 0 -150px;
        width: 300px;
        height: 300px;
    }
    #login h1{
        color: #fff;
        text-shadow:0 0 10px;
        letter-spacing: 1px;
        text-align: center;
    }
    h1{
        font-size: 2em;
        margin: 0.67em 0;
    }
    input{
        width: 278px;
        height: 18px;
        margin-bottom: 10px;
        outline: none;
        padding: 10px;
        font-size: 13px;
        color: #fff;
        text-shadow:1px 1px 1px;
        border-top: 1px solid #312E3D;
        border-left: 1px solid #312E3D;
        border-right: 1px solid #312E3D;
        border-bottom: 1px solid #56536A;
        border-radius: 4px;
        background-color: #2D2D3F;
    }
    .but {
        width: 300px;
        min-height: 20px;
        display: block;
        background-color: #4a77d4;
        border: 1px solid #3762bc;
        color: #fff;
        padding: 9px 14px;
        font-size: 15px;
        line-height: normal;
        border-radius: 5px;
        margin: 0;
    }
</style>
</head>
<body>
            <div id="login">
                <h1>SSO-SERVICE登录系统</h1>
                <form class="form-signin" id="form" action="login?service=${sessionScope.get("service")}" method="post"
                      autocomplete="off">
                    <input id="userName" type="text" required="required" placeholder="用户名" name="userName"/>
                    <input id="userPwd" type="password" required="required" placeholder="密码" name="userPwd"/>
                    <button class="but" onclick="$('#form').submit()">登录</button>
                </form>
                <div style="margin-left: auto">
                    <span style="color:red">${error }</span>
                </div>
            </div>
</body>
</html>