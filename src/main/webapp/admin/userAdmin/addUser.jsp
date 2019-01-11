<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>新增用户</title>
    <!-- DataTables jQuery -->
    <script type="text/javascript" charset="utf8"
            src="${pageContext.request.contextPath}/DataTables-1.10.15/media/js/jquery.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.min.css"
          crossorigin="anonymous">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.min.js"
            crossorigin="anonymous"></script>


    <!--多图上传 -->
    <script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/js/qiniuImgJs/ui.js"></script>
    <script type="text/javascript" charset="utf8"
            src="${pageContext.request.contextPath}/js/qiniuImgJs/main.js"></script>
    <script type="text/javascript" charset="utf8"
            src="${pageContext.request.contextPath}/js/qiniuImgJs/zh_CH.js"></script>
    <script type="text/javascript" charset="utf8"
            src="${pageContext.request.contextPath}/js/qiniuImgJs/dist/qiniu.js"></script>
    <script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/js/insertAtCaret.js"></script>
    <style type="text/css">
        .doSearch {
            width: 190px;
            height: 90px;
            border-width: 0px 1px 1px 1px;
            border-style: solid;
            position: absolute;
            background-color: #ffffff;
            display: none;
            overflow-y: auto;
        }

        .popover {
            width: auto;
            max-width: 800px;
        }

    </style>

</head>

<body>

<form id="authform" style="margin-top: 50px"></form>
    &nbsp;&nbsp;&nbsp;<h1>添加用户</h1>
<table id="guige" class="table table-hover table-condensed" style="width: 800px">
    <thead>
    <tr>
        <td style="width: 150px;text-align: right">名称：</td>
        <td style="width: 750px;"><input id="userName" style="width: 600px" value="${user.userName}" name="userName"></td>
    </tr>
    <tr>
        <td style="width: 150px;text-align: right">密码：</td>
        <td style="width: 750px;"><input id="userPwd" style="width: 600px" value="${user.userPwd}" name="userPwd"></td>
    </tr>

    <tr>
        <td style="width: 150px;text-align: right">角色：</td>
        <td style="width: 750px;">
            <c:forEach var="role" items="${roleList}">
            <input id="roleId" type="checkbox" style="width: 60px" value="${role.id}" name="roleId">${role.roleName} &nbsp;&nbsp;&nbsp;&nbsp;
            </c:forEach>
        </td>
    </tr>

    <tr><td></td>
        <td colspan="2" style="text-align: center">
            <input id="addUser" class="btn btn-primary" type="button" value="提交" onclick="addUser()">&nbsp&nbsp
        </td>
        <td></td>
    </tr>
    </thead>
</table>
</form>
</body>

<script type="text/javascript">

    function addUser() {
        var userName = $("#userName").val();
        var userPwd = $("#userPwd").val();
        var roleId = [];//定义一个角色数组
        $("input[name='roleId']:checked").each(function(i){//把所有被选中的复选框的值存入数组
            roleId[i] =$(this).val();
                   });
        if (userName != '' && userPwd !== '') {
            $.ajax({
                type: "POST",
                url: "/userAdmin/add",
                data: {
                    "roleId":roleId,
                    "userName": userName,
                    "userPwd": userPwd
                },
                success: function (data) {
                    alert(data.msg);
                }
            });
        } else if (userName == '') {
            alert("用户名称为必填项！")

        } else if (userPwd == '') {
            alert("用户密码为必填项！")

        }
    }


</script>
</html>