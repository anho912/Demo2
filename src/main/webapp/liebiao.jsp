<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>图书列表</title>
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

	<!-- start danpin -->
	<div class="danpin center">
		<input type="hidden" id="searchBookStr" name="searchBookStr"
			value="${searchStr}" />
		<div class="biaoti center">图书列表</div>
		<div class="main center" id="BookTableData"></div>
		<div class="main center mb20" id="BooksTableData"></div>
		<!-- <div value="1 0"></div> -->
		<br>
	</div>
	<div align="center">
		<div id="page" class="page_div"></div>
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
		ajaxTest(1, '');

		function ajaxTest(num, str) {
			str = $("#searchBookStr").val();
			$
					.ajax({
						url : "BookData?searchBookStr=" + str + "&page=" + num,
						type : "get",
						dataType : "json",
						success : function(data) {
							if(data.code==0){
								if (data.data.data.length == 0) {
									layer.msg("查询为空！");
								}
								var tablehtml = "";
								var tableshtml = "";
								for (var i = 0; i < data.data.data.length; i++) {
									if (i < 5) {
										var product = data.data.data[i];
										tablehtml += "<div class='mingxing fl mb20' style='border: 2px solid #fff; width: 230px; cursor: pointer;'"
										+" onmouseout='this.style.border='2px solid #fff'' onmousemove='this.style.border='2px solid red''>"
												+ "<div class='sub_mingxing'><a href='getInfoById?proId="
												+ product.proId
												+ "' target='_blank'><img src='upload/"+product.proImg+"' width='150' height='160' alt=''></a>"
												+ "</div><div class='pinpai'><a href='getInfoById?proId="
												+ product.proId
												+ "' target='_blank'>"
												+ product.proName
												+ "</a></div><div class='youhui'>"
												+ product.proCategory
												+ "</div>"
												+ "<div class='jiage'>"
												+ product.proPrice
												+ "元</div></div>";
									} else {
										var product = data.data.data[i];
										tableshtml += "<div class='mingxing fl mb20' style='border: 2px solid #fff; width: 230px; cursor: pointer;'"
											+" onmouseout='this.style.border='2px solid #fff'' onmousemove='this.style.border='2px solid red''>"
												+ "<div class='sub_mingxing'><a href='getInfoById?proId="
												+ product.proId
												+ "' target='_blank'><img src='upload/"+product.proImg+"' width='150' height='160' alt=''></a>"
												+ "</div><div class='pinpai'><a href='getInfoById?proId="
												+ product.proId
												+ "' target='_blank'>"
												+ product.proName
												+ "</a></div><div class='youhui'>"
												+ product.proCategory
												+ "</div>"
												+ "<div class='jiage'>"
												+ product.proPrice
												+ "元</div></div>";
									}
								}
								tablehtml += "<div class='clear'></div>";
								tableshtml += "<div class='clear'></div>";
								document.getElementById("BookTableData").innerHTML = tablehtml;
								document.getElementById("BooksTableData").innerHTML = tableshtml;
								//分页
								$("#page").paging({
									pageNo : num, //当前页
									totalPage : data.data.totalPage, //共多少页
									totalSize : data.data.totalSize, //共多少条记录
									callback : function(num) {
										ajaxTest(num);
									}
								})
							}else{
								layer.msg(data.msg);
							}
						},
						error : function() {
							layer.msg("操作异常！");
						}
					})
		}
	</script>
</body>
</html>