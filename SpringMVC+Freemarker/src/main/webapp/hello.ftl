<#assign c=JspTaglibs["/tld/c.tld"] />
<#assign fmt=JspTaglibs["/tld/fmt.tld"] />
<html>
<head>
    <title>hello</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
    <h1>Welcome ${user}</h1>
    <h1>${appUrl}</h1>
    map取值: ${map["key"]}<br/>
    <#--map遍历: -->
</body>
</html>