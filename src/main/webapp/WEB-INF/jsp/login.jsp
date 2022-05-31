<%@page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<%--引入公共资源--%>
	<%@include file="/WEB-INF/jsp/inc/commons_head.jsp" %>
	<script>
		// 如果当前窗口不是顶层窗口，让当前页面在顶层窗口展示
		if (top !== window) {
			top.location = location;
		}
		//alert("${param.redirectUrl}");
		jQuery(function ($) {
			$("#loginBtn").click(function () {
				$.ajax({
					url: "/user/login.do",
					type: "post",
					data: {
						username: $("#username").val(),
						password: $("#password").val(),
						autoLogin: $("#autoLogin").prop("checked")
					},
					success: function(data) {
						if (data.success) {
							/*if (${empty param.redirectUrl}) {
								location = "/workbench/index.html";
							} else {
								location = "${param.redirectUrl}";
							}*/
							// 如果||之前的表达式为true，则location的值为||之前的表达式，否则为||之后的表达式
							location = "${param.redirectUrl}" || "/settings/dictionary/index.html";
						}
						// 错误提示
						if (data.msg) {// 非空字符串、非null、非0、非null、非undefined、非false
							alert(data.msg);
						}
					}
				})
			})
			// 密码输入完成之后，回车登录
			$("#password").keydown(function (e) {
				/*
					e为事件句柄对象，通过该对象可以获取事件相关的属性，
						例如：
							1. 按下的是键盘上的哪个键
							2. 按下的是鼠标的左键还是右键还是中间键
							3. 鼠标按下时的坐标（x,y）
							...
				 */
				if(e.which == 13) {
					// 触发登录按钮的点击事件
					//$("#loginBtn").click();
					//$("#loginBtn").trigger("click");
					$("#loginBtn").triggerHandler("click"); // 推荐
				}
			})
		})
	</script>
</head>
<body>
	<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
		<img src="/static/image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
	</div>
	<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">动力云客 &nbsp;<span style="font-size: 12px;">&copy;2017&nbsp;动力节点</span></div>
	</div>
	
	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1>登录</h1>
			</div>
			<div class="form-horizontal" role="form">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input id="username" class="form-control" type="text" placeholder="用户名">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input id="password" class="form-control" type="password" placeholder="密码">
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
						<label>
							<input id="autoLogin" type="checkbox"> 十天内免登录
						</label>
					</div>
					<button id="loginBtn" type="button" class="btn btn-primary btn-lg btn-block" style="width: 350px; position: relative;top: 45px;">登录</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>