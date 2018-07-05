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
					value="${sessionScope.session_user.userId}" /> <input
					type="hidden" id="orderId" name="orderId"
					value="${orderInfo.orderId}" /><input type="hidden"
					id="createDate" name="createDate" value="${createDate}" /> <input
					type="hidden" id="receivername" name="receivername"
					value="${orderInfo.receivername}" /> <input type="hidden"
					id="receiveraddress" name="receiveraddress"
					value="${orderInfo.receiveraddress}" /> <input type="hidden"
					id="receiverphone" name="receiverphone"
					value="${orderInfo.receiverphone}" />
				<div class="ddzx">个人中心</div>
				<div class="subddzx">
					<ul>
						<li><a href="userInfo">我的个人中心</a></li>
					</ul>
				</div>
			</div>
			<div class="rtcont fr" id="receiveInfo">
				<div class="ddzxbt">订单其他信息</div>
				<br>
				<div>
					<a href="javascript:void(0)" onclick="goback();">返回</a>
				</div>
				<br>
				<br> <br>
				<div align="center">
					订单号： <label id="orderinfoID"></label>
				</div>
				<br>
				<div align="center">
					收货人： <label id="name"></label>
				</div>
				<br>
				<div align="center">
					联系电话： <label id="phone"></label>
				</div>
				<br>
				<div align="center">
					收货地址：<label id="address"></label>
				</div>
				<br>
				<div align="center">
					创建时间：<label id="createdate"></label>
				</div>
				<br>
			</div>

			<div class="clear"></div>
		</div>
	</div>

	<footer class="mt20 center">
	<div>京ICP证110xxx号 京ICP备10046xxx号 京公网安备110108020xxxxx号
		京网文[2014]0059-0xxx号</div>
	<div>违法和不良信息举报电话：185-xxxx-xxxx，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</div>
	</footer>
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="layer/layer.js"></script>
	<script type="text/javascript">
		document.getElementById("name").innerText = $("#receivername").val();
		document.getElementById("phone").innerText = $("#receiverphone").val();
		document.getElementById("address").innerText = $("#receiveraddress")
				.val();
		document.getElementById("orderinfoID").innerText = $("#orderId").val();
		document.getElementById("createdate").innerText = $("#createDate")
				.val();

		function goback() {
			window.history.back(-1);
		}
	</script>
</body>
</html>