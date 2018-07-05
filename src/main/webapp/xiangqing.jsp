<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%  String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>立即购买-图书商城</title>
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

	<!-- xiangqing -->
	<form>
		<div class="xiangqing">
			<div class="neirong w">
				<div class="xiaomi6 fl">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商品详情</div>
			</div>
		</div>

		<div class="jieshao mt20 w">
			<input type="hidden" name="proId" id="proId"
				value="${products.proId}" />
			<div class="left fl">
				<img src="<%=path%>/upload/${products.proImg}" width='560'
					height='560'>
			</div>
			<div class="right fr">
				<div class="h3 ml20 mt20">${products.proName}</div>
				<div class="jianjie mr40 ml20 mt10">${products.proCategory}</div>
				<div class="jiage ml20 mt10">${products.proPrice}元</div>
				<div class="xqxq mt20 ml20">
					<div class="top1 mt10">
						<div class="left1 fl">${products.proDescription}:
							${products.proName}</div>
						<div class="right1 fr">${products.proPrice}元</div>
						<div class="clear"></div>
					</div>
					<div class="bot mt20 ft20 ftbc">总计：${products.proPrice}元</div>
				</div>
				<div class="xiadan ml20 mt20">
					<input class="jrgwc" type="button" name="jrgwc" value="加入购物车"
						onclick="insertCart();" />
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</form>
	<!-- footer -->
	<footer class="mt20 center">
	<div>京ICP证110xxx号 京ICP备10046xxx号 京公网安备110108020xxxxx号
		京网文[2014]0059-0xxx号</div>
	<div>违法和不良信息举报电话：185-xxxx-xxxx，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</div>
	</footer>

	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="layer/layer.js"></script>
	<script type="text/javascript">
		var proId = $("#proId").val();
		function insertOrder() {
			window.location.href = "loadingOrder?proId=" + proId;
		}

		function insertCart() {
			$.ajax({
				url : "loadingCart?proId=" + proId,
				type : "get",
				dataType : "json",
				success : function(data) {
					layer.msg(data.msg);
				},
				error : function() {
					layer.msg("操作异常!");
				}
			});
		}
	</script>
</body>
</html>