<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>我的购物车-图书商城</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">

<style>
* {
	padding: 0;
	margin: 0;
}
/*
			  * 外面盒子样式---自己定义
			  */
.page_div {
	margin-top: 20px;
	margin-bottom: 20px;
	font-size: 15px;
	font-family: "microsoft yahei";
	color: #666666;
	margin-right: 10px;
	padding-left: 20px;
	box-sizing: border-box;
}
/*
			 * 页数按钮样式
			 */
.page_div a {
	min-width: 30px;
	height: 28px;
	border: 1px solid #dce0e0 !important;
	text-align: center;
	margin: 0 4px;
	cursor: pointer;
	line-height: 28px;
	color: #666666;
	font-size: 13px;
	display: inline-block;
}

#firstPage, #lastPage {
	width: 50px;
	color: #0073A9;
	border: 1px solid #0073A9 !important;
}

#prePage, #nextPage {
	width: 70px;
	color: #0073A9;
	border: 1px solid #0073A9 !important;
}

.page_div .current {
	background-color: #0073A9;
	border-color: #0073A9;
	color: #FFFFFF;
}

.totalPages {
	margin: 0 10px;
}

.totalPages span, .totalSize span {
	color: #0073A9;
	margin: 0 5px;
}
</style>

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

	<!-- start banner_x -->
	<div class="banner_x center">
		<div class="wdgwc fl ml40">结算中心</div>
		<div class="wxts fl ml20">温馨提示：请核对产品，为确保购买成功，请尽快结算</div>
		<div class="clear"></div>
	</div>
	<div class="xiantiao"></div>
	<div class="gwcxqbj">
		<input type="hidden" name="productId" id="productId"
			value="${productId}"> <input type="hidden" name="datainfo"
			id="datainfo" value="${data}">
		<div class="gwcxd center" id="OrderTable"></div>
		<div class="gwcxd center" id="receiveInfo" align="right">
			<div>
				<label>收货人：</label><input id="username" name="username" type="text" />
			</div>
			<div>
				<label>联系方式：</label><input id="phone" name="phone" type="text" />
			</div>
			<div>
				<label>收货地址：</label><input id="address" name="address" type="text" />
			</div>
		</div>

		<div class="jiesuandan mt20 center">
			<div class="tishi fl ml20">
				<ul>
					<li><a href="liebiao">取消</a></li>
					<div class="clear"></div>
				</ul>
			</div>
			<div class="jiesuan fr">
				<div class="jsanniu fr">
					<input class="jsan" type="button" name="jiesuan" value="结算"
						onclick="createOrder();" />
				</div>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
		</div>
		<div align="center">
			<div id="page" class="page_div"></div>
		</div>
	</div>
	<div class="clear"></div>
	<!-- footer -->
	<footer class="mt20 center">
	<div>京ICP证110xxx号 京ICP备10046xxx号 京公网安备110108020xxxxx号
		京网文[2014]0059-0xxx号</div>
	<div>违法和不良信息举报电话：185-xxxx-xxxx，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</div>
	</footer>
	<script src="js/jquery.js"></script>
	<script src="js/paging.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="layer/layer.js"></script>
	<script type="text/javascript">
		ajaxTest(1);

		var datainfo = $("#datainfo").val();
		if (datainfo.length > 0) {
			layer.msg(datainfo);
		}
		function ajaxTest(num) {
			var productId = $("#productId").val();
			$
					.ajax({
						url : "OrderInfoData?productIds=" + productId
								+ "&page=" + num,
						type : "get",
						dataType : "json",
						success : function(data) {
							var tablehtml = "<div class='top2 center'><div class='sub_top fl'></div>"
									+ "<div class='sub_top fl'>商品名称</div><div class='sub_top fl'>单价</div>"
									+ "<div class='sub_top fl'>数量</div><div class='sub_top fl'>小计</div>"
									+ "<div class='sub_top fr'></div><div class='clear'></div></div>";
							for (var i = 0; i < data.data.data.length; i++) {
								var products = data.data.data[i];
								var proId = products.proId;
								tablehtml += "<div class='content2 center'><div class='sub_content fl '></div>"
										+ "<div class='sub_content fl'><img src='upload/"+products.proImg+"' width='60' height='100'></div>"
										+ "<div class='sub_content fl ft20'>"
										+ products.proName
										+ "</div>"
										+ "<div class='sub_content fl '>"
										+ products.proPrice
										+ "元</div><div class='sub_content fl'>"
										+ products.proNum
										+ "</div>"
										+ "<div class='sub_content fl'>"
										+ products.proMoney
										+ "元</div><div class='sub_content fl'>"
										+ "</div><div class='clear'></div></div>";
							}
							document.getElementById("OrderTable").innerHTML = tablehtml;
							//分页
							$("#page").paging({
								pageNo : num, //当前页
								totalPage : data.data.totalPage, //共多少页
								totalSize : data.data.totalSize, //共多少条记录
								callback : function(num) {
									ajaxTest(num);
								}
							})
						},
						error : function() {
							layer.msg("操作异常！");
						}
					})
		}

		//生成订单
		function createOrder() {
			var username=$("#username").val();
			var phone=$("#phone").val();
			var address=$("#address").val();
			if(!username.length>0){
				layer.msg("收货人姓名不可为空！");
				return;
			}
			if(!phone.length>0){
				layer.msg("收货人联系方式不可为空！");
				return;
			}
			if(!address.length>0){
				layer.msg("收货人地址不可为空！");
				return;
			}
			var productId = $("#productId").val();
			window.location.href = "createOrder?productIds=" + productId+"&username="+username+"&phone="+phone+"&address="+address;
		}
	</script>
</body>
</html>
