<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>图书商城-个人中心</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
	<!-- start header -->
	<header>
	<div class="top center">
		<div class="left fl">
			<ul>
				<li><a href="index">图书商城</a></li>
				<div class="clear"></div>
			</ul>
		</div>
		<div class="right fr">
			<div class="gouwuche fr">
				<a href="MyCart">购物车</a>
			</div>
			<div class="fr">
				<ul>
					<li>用户 <span class="username">${sessionScope.session_user.userName}</span>登录成功
					</li>
					<li>|</li>
					<li><a href="userInfo">个人中心</a></li>
					<li>|</li>
					<li><a href="logout">退出登录</a></li>
				</ul>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	</header>
	<!--end header -->
	<!-- self_info -->
	<div class="grzxbj">
		<div class="selfinfo center">
			<div class="lfnav fl">
				<div class="ddzx">订单中心</div>
				<div class="subddzx">
					<ul>
						<li><a href="dingdanzhongxin">我的订单</a></li>
					</ul>
				</div>
				<div class="ddzx">个人中心</div>
				<div class="subddzx">
					<ul>
						<li><a href="self_info"
							style="color: #ff6700; font-weight: bold;">我的个人中心</a></li>
					</ul>
				</div>
			</div>
			<input type="hidden" id="data_info" name="data_info" value="${info}">
			<form id="Userform" name="Userform">
				<input type="hidden" id="userId" name="userId"
					value="${users.userId }">
				<div class="rtcont fr">
					<div class="grzlbt ml40">
						开始编辑 | <a href="javascript:void(0)" onclick="submitTo();">确认</a> |
						<a href="javascript:void(0)" onclick="goback();">返回</a>
					</div>
					<div class="subgrzl ml40">
						<span>用戶名</span> <input type="text" id="userName"
							readonly="readonly" name="userName" value="${users.userName }" />
						<span>不可修改</span>
					</div>
					<div class="subgrzl ml40">
						<span>手机号</span> <input type="text" id="userTel"
							readonly="readonly" name="userTel" value="${users.userTel }" />
						<span>不可修改</span>
					</div>
					<div class="subgrzl ml40">
						<span>密码</span><input type="password" id="password"
							name="password" value="${users.password }" />
					</div>
					<div class="subgrzl ml40">
						<span>个性签名</span><input type="text" id="userIntroduce"
							name="userIntroduce" value="${users.userIntroduce }" />
					</div>
					<div class="subgrzl ml40">
						<span>积分余额</span><input type="text" id="userIntegral"
							readonly="readonly" name="userIntegral"
							value="${users.userIntegral }" /> <span>不可修改</span>
					</div>
					<div class="subgrzl ml40">
						<span>用户等级</span><input type="text" id="userGender"
							readonly="readonly" name="userGender"
							value="${users.userGender }" /> <span>不可修改</span>
					</div>
				</div>
				<div class="clear"></div>
			</form>
		</div>
	</div>
	<!-- self_info -->

	<footer class="mt20 center">
	<div>京ICP证110xxx号 京ICP备10046xxx号 京公网安备110108020xxxxx号
		京网文[2014]0059-0xxx号</div>
	<div>违法和不良信息举报电话：185-xxxx-xxxx，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</div>
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
		function submitTo() {
			if ($("#password").val().length < 6) {
				layer.msg("密碼最少输入六个字符！");
				return;
			} else if ($("#password").val().length > 15) {
				layer.msg("密碼最多输入十五个字符！");
				return;
			}
			if ($("#userIntroduce").val().length > 20) {
				layer.msg("签名最多输入20个字符！");
				return;
			}
			$.ajax({
				url : "editUserInfo", //提交的地址  
				type : "POST", //提交的方法
				data : $('#Userform').serialize(), // 序列化表单值  
				dataType : "json",
				error : function() { //失败的话
					layer.msg("操作异常！");
				},
				success : function(data) { //成功
					if (data.code == 0) {
						window.location.href = "userInfo";
					} else {
						layer.msg(data.msg);
					}
				}
			});
		}

		function goback() {
			window.history.back(-1);
		}
	</script>
</body>
</html>