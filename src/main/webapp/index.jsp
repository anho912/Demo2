<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<title>图书商城</title>
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
	<div class="clear"></div>
	</header>
	<!--end header -->

	<!-- start banner_x -->
	<div class="banner_x center">
		<!-- <a href="./index" target="_blank"><div class="logo fl"></div></a>
			<a href=""><div class="ad_top fl"></div></a> -->
		<div class="nav fl">
			<ul>
				<li><a href="./liebiao" target="_blank">图书排行榜</a></li>
			</ul>
		</div>
		<div class="search fr">
			<form action="findBySearch" method="get">
				<div class="text fl">
					<input type="text" class="shuru" name="searchStr"
						placeholder="书名/类型  搜索">
				</div>
				<div class="submit fl">
					<input type="submit" class="sousuo" value="搜索" />
				</div>
				<div class="clear"></div>
			</form>
			<div class="clear"></div>
		</div>
	</div>
	<!-- end banner_x -->

	<!-- start banner_y -->
	<div class="banner_y center">
		<div class="nav" id="dictionaryList"></div>
	</div>
	<!-- end banner -->

	<!-- start danpin -->
	<div class="danpin center">

		<div class="biaoti center">图书明星单品</div>
		<div class="main center" id="sixProduct"></div>
	</div>
	<div class="peijian w">
		<div class="biaoti center">其他</div>
		<div class="main center">
			<div class="content" id="oneProduct">

				<div class="clear"></div>
			</div>
			<div class="content" id="twoProduct"></div>
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
		$(function() {
			$("#searchStr").keydown(function(e) {
				if (e.keyCode === 13) {
					searchProduct();
				}
			});
			$
					.ajax({
						url : "loadingAll",
						type : "post",
						dataType : "json",
						success : function(data) {
							if (data.code == 0) {
								//显示类目列表
								var dicHTML = "<ul>";
								var dicList = data.dictionaries;
								for (var i = 0; i < dicList.length; i++) {
									if (i % 2 == 0) {
										var str1 = dicList[i].ddName;
										var str2 = dicList[i + 1].ddName;
										dicHTML += "<li><a href='findBySearch?searchStr="
												+ str1
												+ "'>"
												+ dicList[i].ddName
												+ "</a> <a href='findBySearch?searchStr="
												+ str2
												+ "'>"
												+ dicList[i + 1].ddName
												+ "</a></li>";
									}
								}
								dicHTML += '</ul>';
								document.getElementById("dictionaryList").innerHTML = dicHTML;

								//显示明星单品
								var sixHTML = "";
								var sixList = data.sixProducts;
								for (var i = 0; i < 5; i++) {
									sixHTML += "<div class='mingxing fl'><div class='sub_mingxing'><a href='getInfoById?proId="
											+ sixList[i].proId
											+ "'><img src='upload/"+sixList[i].proImg +"'></a></div><div class='pinpai'><a href='getInfoById?proId="
											+ sixList[i].proId
											+ "'>"
											+ sixList[i].proName
											+ "</a></div><div class='youhui'>"
											+ sixList[i].proCategory
											+ "</div><div class='jiage'>"
											+ sixList[i].proPrice
											+ "</div></div>";
								}
								sixHTML += "<div class='clear'></div>";
								document.getElementById("sixProduct").innerHTML = sixHTML;

								//显示其他商品
								var oneHTML = "";
								var twoHTML = "";
								var tenList = data.products;
								for (var i = 0; i < 5; i++) {
									oneHTML += "<div class='remen fl'><div class='xinpin'><span style='background:#fff'></span></div><div class='tu'><a href='getInfoById?proId="
											+ tenList[i].proId
											+ "'><img src='upload/"+tenList[i].proImg+"' width='150' height='160'></a></div><div class='miaoshu'><a href='getInfoById?proId="
											+ tenList[i].proId
											+ "'>"
											+ tenList[i].proName
											+ "</a></div><div class='jiage'>"
											+ tenList[i].proPrice
											+ "元</div><div class='piao'><a href='getInfoById?proId="
											+ tenList[i].proId
											+ "'><span>"
											+ tenList[i].proCategory
											+ "</span></a></div></div>";

								}
								for (var i = 5; i < 9; i++) {
									twoHTML += "<div class='remen fl'><div class='xinpin'><span style='background:#fff'></span></div><div class='tu'><a href='getInfoById?proId="
											+ tenList[i].proId
											+ "'><img src='upload/"+tenList[i].proImg+"' width='150' height='160'></a></div><div class='miaoshu'><a href='getInfoById?proId="
											+ tenList[i].proId
											+ "'>"
											+ tenList[i].proName
											+ "</a></div><div class='jiage'>"
											+ tenList[i].proPrice
											+ "元</div><div class='piao'><a href='getInfoById?proId="
											+ tenList[i].proId
											+ "'><span>"
											+ tenList[i].proCategory
											+ "</span></a></div></div>";
								}
								oneHTML += "<div class='clear'></div>";
								document.getElementById("oneProduct").innerHTML = oneHTML;
								twoHTML += "<div class='remenlast fr'><div class='liulangengduo'><a href='liebiao'><img src='./image/liulangengduos.png' alt=''></a></div></div><div class='clear'></div>";
								document.getElementById("twoProduct").innerHTML = twoHTML;
							} else {
								layer.msg(data.msg);
							}
						},
						error : function() {
							layer.msg("操作异常！");
						}
					});
		});
	</script>
</body>
</html>