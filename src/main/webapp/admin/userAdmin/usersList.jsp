<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/DataTables-1.10.15/media/css/jquery.dataTables.css">
    <!-- DataTables jQuery -->
    <script type="text/javascript" charset="utf8"
            src="${pageContext.request.contextPath}/DataTables-1.10.15/media/js/jquery.js"></script>
    <!-- DataTables DataTables -->
    <script type="text/javascript" charset="utf8"
            src="${pageContext.request.contextPath}/DataTables-1.10.15/media/js/jquery.dataTables.js"></script>
    <!-- 格式化时间-->
    <script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/js/dateformat.js"></script>

    <!--弹出框-->
    <script language="javascript" type="text/javascript"
            src="${pageContext.request.contextPath}/js/bootbox.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.min.css"
          crossorigin="anonymous">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.min.js"
            crossorigin="anonymous"></script>
    <!-- 导出表格 -->
    <script type="text/javascript" charset="utf8"
            src="${pageContext.request.contextPath}/tableExport/jquery.base64.js"></script>
    <script type="text/javascript" charset="utf8"
            src="${pageContext.request.contextPath}/tableExport/tableExport.js"></script>
    <script type="text/javascript" charset="utf8"
            src="${pageContext.request.contextPath}/tableExport/chineseTableExport.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/win10.child.js"></script>


    <style type="text/css">
        td {
            text-align: center;
            font-size: 14px
        }
    </style>
</head>
<body>

<br>
<div class="input-group input-group-sm" style="width: 900px">
    <span class="input-group-addon">用户名称:</span>
    <input id="userName" type="text" class="form-control input-mini" placeholder="输入用户名称">

</div>
<br>
<br>
<div class="input-group input-group-sm" style="width: 100px">
    <input id="search" class="btn btn-primary form-control" type="button" value="搜索">
</div>
<br>
<div class="input-group input-group-sm" style="width: 635px">
    <input id="addUser" class="btn btn-primary" type="button" value="添加用户"
           onclick="Win10_child.openUrl('/userAdmin/toAddUser',
                   '<img class=\'icon\' src=\'${pageContext.request.contextPath}/img/icon/doc.png\'/>添加用户')">&nbsp;&nbsp;

</div>
<br>
<table id="table_id_example" class="display">
    <thead>
    <tr>
        <td>用户ID</td>
        <td>名称</td>
        <td>密码</td>
        <td>状态</td>
        <td>创建时间</td>
        <td>操作</td>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>
</body>
<script type="text/javascript">
    var oTable;
    $(document).ready(function () {
        oTable = initTale();
        $("#search").click(function () {
            if (datatables != null) {
                datatables.destroy();
            }
            oTable = initTale();
        });
    });
    var datatables;
    function initTale() {
        datatables = $('#table_id_example').DataTable({
            paging: true,
            searching: false,
            ordering: false,
            processing: true,
            serverSide: true,
            aLengthMenu: [[5, 10, 15, 20, 25, 50, -1], ["5条", "10条", "15条", "20条", "25条", "50条", "全部"]],
            language: {
                url: '${pageContext.request.contextPath}/DataTables-1.10.15/Chinese.json'
            },
            ajax: {
                url: '/userAdmin/findUsersByCriteria',
                dataType: "json",
                type: "post",
                dataSrc: "data",
                data: {
                    "userName": $("#userName").val()
                }
            },
            columns: [
                {data: 'id'},
                {data: 'userName'},
                {data: 'userPwd'},
                {data: function (data) {
                        if (data.cutOff == 1){
                                 return "禁用";
                         }else {
                            return "正常";
                        }
                }},
                {data: function (data) {
                        return timeToData(data);
                    }},
                {
                    data: function (data) {
                        return "<input type='button' class='btn btn-default' value='删除' onclick='toDelete(" + data.id + ")'>"+
                            "<input type='button' class='btn btn-default' value='修改' onclick='toEdit(" + data.id + ")'>";
                    }
                }
            ]
        });
    }



   function timeToData(data) {
            if (data.creatTime != null) {
                var date = new Date(data.creatTime);//如果date为10位不需要乘1000
                var Y = date.getFullYear() + '-';
                var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
                var D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate()) + ' ';
                var h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
                var m = (date.getMinutes() <10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
                var s = (date.getSeconds() <10 ? '0' + date.getSeconds() : date.getSeconds());
                console.log(Y+M+D+h+m+s);
                return Y+M+D+h+m+s;
            }
            return "空";
        }



    function toDelete(userId) {
        var r =confirm("确定删除此用户吗？");
        if (r==true) {
            $.ajax({
                type: "get",
                url: "/userAdmin/delete/"+ userId,
                dataType: "json",
                success: function(result){
                    if (result.success){
                        alert(result.msg);
                        //重新初始化tables
                        if (datatables != null) {
                            datatables.destroy();
                        }
                        initTale();
                    } else{
                        alert(result.msg);
                    }
                }
            });
        }
    }

    function toEdit(userId) {
        Win10_child.openUrl('/userAdmin/toEdit/'+ userId,
            '<img class=\'icon\' src=\'${pageContext.request.contextPath}/img/icon/doc.png\'/>修改用户信息');
    }

</script>
</html>
