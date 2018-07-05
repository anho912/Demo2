<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>图书列表</title>
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
					<li><a href="userInfo">个人中心 </a></li>
					<li>|</li>
					<li><a href="logout">退出登录</a></li>
				</ul>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	</header>
	<!--end header -->

	<!-- start danpin -->
	<div class="danpin center">
		<input type="hidden" id="sumMoney" name="sumMoney" value="${sumMoney}" />
		<input type="hidden" id="userId" name="userId" value="${sessionScope.session_user.userId}" />
		<div class="biaoti center">支付</div>
		<!-- <div value="1 0"></div> -->
		<div align="center">
			共需支付： <label id="money"></label> 元。
		</div>
		<br>
	</div>
	<div class="gwcxqbj">
		<div class="jiesuandan mt20 center">
			<div class="tishi fl ml20">
				<ul>
					<li><a href="javascript:void(0)" onclick="gobackPay()">取消</a></li>
					<div class="clear"></div>
				</ul>
			</div>
			<div class="jiesuan fr">
				<div class="jsanniu fr">
					<input class="jsan" type="button" name="jiesuan" value="确认支付"
						onclick="payOrder();" />
				</div>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
		</div>
	</div>

	<footer class="mt20 center">
	<div>京ICP证110xxx号 京ICP备10046xxx号 京公网安备110108020xxxxx号
		京网文[2014]0059-0xxx号</div>
	<div>违法和不良信息举报电话：185-xxxx-xxxx，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</div>
	</footer>

	<!-- end danpin -->

	<script src="js/jquery.js"></script>
	<script src="js/paging.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="layer/layer.js"></script>
	<script type="text/javascript">
		
		$(function(){
			var money = $("#sumMoney").val();
			document.getElementById("money").innerText =money;
		}); 
		
		//确认支付
		function payOrder(){
			var products = $("#products").val();
			window.location.href="payOrder";
		}
		//取消支付
		function gobackPay(){
			window.location.href="cancelPay?userId="+$("#userId").val();
		}
	</script>
</body>
</html>