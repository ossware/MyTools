<html>
<head>
    <title>hello</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
    <h1>Welcome ${user}</h1>
    <h1>${appUrl}</h1>
    map取值: ${map["key"]}<br/>
    map遍历:
    <#list map?keys as key>
        key is ${key} =
        value is ${map[key]}
    </#list><br>
    取长度: ${"abc"?length}<br>
    判断空: Welcome ${user!"Anonymous"} 或者：<#if user??>Welcome ${user}</#if>
</body>
</html>