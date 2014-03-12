<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String login_key = request.getParameter("login_key");   // 登录成功后的key
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>WebApi</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script type="text/javascript">
        var login_key = "<%=login_key%>";
    </script>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="<%=path%>/js/index.js"></script>
    <script type="text/javascript" src="<%=path%>/js/json2.js"></script>
</head>

<body>
<input type="button" value="向server提交JSON数据" id="btnTestJson">&nbsp;
<input type="button" value="从server取得JSON数据" id="btnGetJson"><br>

<div id="result" style="border: 1px dotted red;"></div>
</body>

</html>