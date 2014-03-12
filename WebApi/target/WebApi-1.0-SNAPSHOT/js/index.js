$(document).ready(testJson);

function testJson() {
	$("#btnTestJson").click(requestServlet);
	$("#btnGetJson").click(getJsonData);
}

var user = {
	name : "xiaolei",
	age : 27,
	birthday : "1985-4-17",
    login_key : login_key
};

function requestServlet() {
	var user_json = JSON.stringify(user);

	//通过js携带data来实现的(可以用fromJsonStringToBean1方法来处理)
	$.ajax({
		type:"post",
		url:"api/addUser/test/123.json",
		data:user_json,
//		dataType:"json",	//预期服务器返回的数据类型(请求时无需)
		success: resultFn
	});
	
	
	//通过url串上的传参来实现的(可以用fromJsonStringToBean2方法来处理)
//	$.ajax({
//		type:"post",
	
//		url:"servlet/ProcessJSONDataServlet?group=" + group_json,
//		success:function(result) {
//			alert("结果：" + result);
//		},
//		async:true
//	});
}

function resultFn(data) {
	var result = $("#result");
	result.html(data);
}
