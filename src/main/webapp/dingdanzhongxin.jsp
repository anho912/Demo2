<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>图书商城-个人中心</title>
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
	<!-- self_info -->
	<div class="grzxbj">
		<div class="selfinfo center">
			<div class="lfnav fl">
				<div class="ddzx">订单中心</div>
				<div class="subddzx">
					<ul>
						<li><a href="dingdanzhongxin"
							style="color: #ff6700; font-weight: bold;">我的订单</a></li>
					</ul>
				</div>
				<input type="hidden" id="userId" name="userId"
					value="${sessionScope.session_user.userId}" />
				<div class="ddzx">个人中心</div>
				<div class="subddzx">
					<ul>
						<li><a href="userInfo">我的个人中心</a></li>
					</ul>
				</div>
			</div>
			<div class="rtcont fr" id="orderListTable"></div>
			<div class="clear"></div>
		</div>
	</div>
	<!-- self_info -->
	<div align="center">
		<div id="page" class="page_div"></div>
	</div>

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

		function ajaxTest(num) {
			var userId = $("#userId").val();
			$
					.ajax({
						url : "orderTable?userid=" + userId + "&page=" + num,
						type : "get",
						dataType : "json",
						success : function(data) {
							if (data.data.data.length == 0) {
								layer.msg("查询为空！");
								window.history.back(-1);
							}
							var tablehtml = "<div class='ddzxbt'>交易订单</div>";
							for (var i = 0; i < data.data.data.length; i++) {
								var product = data.data.data[i];
								var type = "";
								if (product.isAccept == 0) {
									type = "交易进行中";
								} else {
									type = "交易已完成";
								}
								tablehtml += "<div class='ddxq'><div class='ddspt fl' align='center'><a href='getInfoById?proId="
										+ product.proId
										+ "' target='_blank'><img src='upload/"+product.proImg+"' width='90' height='80' title='"+product.proName+"'></a></div>"
										+ "<div class='ddbh fl'>订单/详情编号:"
										+ product.orderId
										+ "/"
										+ product.orderinfoId
										+ "</div><div class='ztxx fr'><ul><li>"
										+ type
										+ "</li>"
										+ "<li>单价：￥"
										+ product.proPrice
										+ "</li><li>数量："
										+ product.proNum
										+ "</li><li>总额：￥"
										+ product.proMoney
										+ "</li></li>"
										+ "<li><a href='receiveInfo?orderinfo="
										+ product.orderinfoId
										+ "'>收货详情></a></li><div class='clear'></div></ul></div><div class='clear'></div></div>";

							}
							document.getElementById("orderListTable").innerHTML = tablehtml;
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
	</script>
</body>
</html>