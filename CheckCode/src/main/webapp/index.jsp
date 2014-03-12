<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String path = request.getContextPath();
%>
<html>
<title>登录页面</title>

<body>
<h2>登录页面</h2>
<form id="loginForm">
    <table align="center">
        <tr>
            <td> 用户名:</td>
            <td><input type="text" name="username" size="15"></td>
        </tr>

        <tr>
            <td> 密码:</td>
            <td><input type="password" name="password" size="16"></td>
        </tr>

        <tr>
            <td>验证码:</td>
            <td>
                <input type="text" id="validatecode" size="15"/>
                <img src="servlet/AuthImage" border="0" id="checkCodeImg" onclick="refreshCode();" title="点击刷新"/>
                <span id="msg"></span>
            </td>
        </tr>

        <tr>
            <td colspan="2" align="center">
                <input type="button" value="登录" onclick="checkCode(document.getElementById('validatecode').value);">
                <input type="reset" value="重置">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
<script type="text/javascript">
    function refreshCode() {
        document.getElementById('checkCodeImg').setAttribute("src", "servlet/AuthImage");
        document.getElementById('checkCodeImg').reload();
    }

    function checkCode(code) {
        var xmlHttp = createXMLHttpRequest();
        if (!xmlHttp) {
            return alert('create failed');
        }

        xmlHttp.open("POST", "<%=path%>/servlet/CheckLogin?validatecode=" + code, true);
        xmlHttp.onreadystatechange = function() {
            // 成功之后的回调函数
            if(xmlHttp.readyState == 4) {
                if(xmlHttp.status == 200) {
                    document.getElementById("msg").innerHTML = xmlHttp.responseText;
                } else {
                    document.getElementById("msg").innerHTML = xmlHttp.responseText;
                    document.getElementById("checkCodeImg").src = "servlet/AuthImage";
                }
            }
        };
        xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        xmlHttp.send();
    }


    function createXMLHttpRequest() {
        var xmlHttp;
        if (window.ActiveXObject) {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        } else if (window.XMLHttpRequest) {
            xmlHttp = new XMLHttpRequest();
        }
        return xmlHttp;
    }
</script>
