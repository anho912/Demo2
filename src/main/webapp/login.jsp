<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>会员登录</title>
<link rel="stylesheet" type="text/css" href="./css/login.css">

</head>
<body>
	<!-- login -->
	<div class="top center">
		<div class="logo center">
			<a href="login" target="_blank"><img
				src="./image/booktore_logo.png" alt=""></a>
		</div>
	</div>
	<input type="hidden" id="data_info" name="data_info" value="${info}">
	<form method="post" action="userlogin" class="form center">
		<div class="login">
			<div class="login_center">
				<div class="login_top">
					<div class="left fl">会员登录</div>
					<div class="right fr">
						您还不是我们的会员？<a href="register" target="_self">立即注册</a>
					</div>
					<div class="clear"></div>
					<div class="xian center"></div>
				</div>
				<div class="login_main center">
					<div class="username">
						用户名:&nbsp;<input class="shurukuang" type="text" name="username"
							placeholder="请输入你的用户名" />
					</div>
					<div class="username">
						密&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;<input class="shurukuang"
							type="password" name="password" placeholder="请输入你的密码" />
					</div>
				</div>
				<div class="login_submit">
					<input class="submit" type="submit" name="submit" value="立即登录">
				</div>

			</div>
		</div>
	</form>
	<footer>
	<div class="copyright">
		我司版权所有-京ICP备10046xxx-<img src="./image/ghs.png" alt="">京公网安备1101080202xxxx号-京ICP证110xxx号
	</div>
	</footer>

	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="layer/layer.js"></script>
	<script type="text/javascript">
		$(function() {
			var datainfo = document.getElementById("data_info");
			if (datainfo.value.length > 0) {
				layer.msg(datainfo.value);
			}
		});
	</script>
</body>
</html>