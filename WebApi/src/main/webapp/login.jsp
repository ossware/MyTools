<%--
  Created by IntelliJ IDEA.
  User: haoxiaolei
  Date: 13-7-22
  Time: 下午5:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <title>登录</title>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="<%=path%>/js/index.js"></script>
    <script type="text/javascript" src="<%=path%>/js/json2.js"></script>
</head>
<body>
<div style="text-align: center;">
    <label for="userName"></label><input type="text" id="userName"><br>
    <label for="password"></label><input type="text" id="password"><br>
    <input type="button" id="loginBtn" value="登 录" onclick="login();">
</div>
</body>
</html>
<script type="text/javascript">
    function login() {
        var loginObj = {
            language : "zh_CN",
            params : {
                userName : document.getElementById('userName').value,
                pwd : document.getElementById('password').value
            }
        };

        var login_json = JSON.stringify(loginObj);
        alert(login_json);
        $.ajax({
            type:"post",
            url:"api/login.json",
            data:login_json,
            success: resultFn
        });
    }

    function resultFn(result) {
        alert("返回的sessionId----> " + result);
        <%--window.location.href = '<%=path%>/index.jsp?login_key=' + result;--%>
    }
</script>