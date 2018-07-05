<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>用户注册</title>
<link rel="stylesheet" type="text/css" href="./css/login.css">

</head>
<body>
	<input type="hidden" id="data_info" name="data_info" value="${info}">
	<form method="post" action="userRegist"
		onsubmit="return checkRegist();">
		<div class="regist">
			<div class="regist_center">
				<div class="regist_top">
					<div class="left fl">会员注册</div>
					<div class="right fr">
						<a href="login">返回登录</a>
					</div>
					<div class="clear"></div>
					<div class="xian center"></div>
				</div>
				<div class="regist_main center">
					<div class="username">
						用&nbsp;&nbsp;户&nbsp;&nbsp;名:&nbsp;&nbsp;<input class="shurukuang"
							type="text" id="userName" name="userName" placeholder="请输入你的用户名" /><span>请不要输入汉字</span>
					</div>
					<div class="username">
						密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;&nbsp;<input
							class="shurukuang" type="password" name="password" id="password"
							placeholder="请输入你的密码" /><span>请输入6位以上字符</span>
					</div>

					<div class="username">
						确认密码:&nbsp;&nbsp;<input class="shurukuang" type="password"
							name="repassword" id="repassword" placeholder="请确认你的密码" /><span>两次密码要输入一致哦</span>
					</div>
					<div class="username">
						手&nbsp;&nbsp;机&nbsp;&nbsp;号:&nbsp;&nbsp;<input class="shurukuang"
							type="text" id="userTel" name="userTel" placeholder="请填写正确的手机号" /><span>填写下手机号吧，方便我们联系您！</span>
					</div>

				</div>
				<div class="regist_submit">
					<input class="submit" type="submit" name="submit" value="立即注册">
				</div>

			</div>
		</div>
	</form>
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

		//正则表达式 验证数字
		var patrn = /^[0-9]*$/;
		function checkRegist() {
			if ($("#userName").val().length < 2) {
				layer.msg("用戶名最少输入两个字符！");
				return false;
			} else if ($("#userName").val().length > 10) {
				layer.msg("用戶名最多输入十个字符！");
				return false;
			}
			if ($("#password").val().length < 6) {
				layer.msg("密碼最少输入六个字符！");
				return false;
			} else if ($("#password").val().length > 15) {
				layer.msg("密碼最多输入十五个字符！");
				return false;
			}
			if ($("#password").val() != $("#repassword").val()) {
				layer.msg("两次输入密码不一致！");
				return false;
			}
			if ($("#userTel").val().length < 11
					|| $("#userTel").val().length > 11
					|| !patrn.test($("#userTel").val())) {
				layer.msg("手机号码格式错误！");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>
