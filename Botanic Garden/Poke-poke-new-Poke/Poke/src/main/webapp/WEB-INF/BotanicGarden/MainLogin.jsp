<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.poke.domain.UserInfoVO"%>
<!DOCTYPE HTML>
<html>
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Shop &mdash; Free Website Template, Free HTML5 Template by gettemplates.co</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Free HTML5 Website Template by gettemplates.co" />
	<meta name="keywords" content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
	<meta name="author" content="gettemplates.co" />

	<!-- 
	//////////////////////////////////////////////////////

	FREE HTML5 TEMPLATE 
	DESIGNED & DEVELOPED by FreeHTML5.co

	Website: 		http://freehtml5.co/
	Email: 			info@freehtml5.co
	Twitter: 		http://twitter.com/fh5co
	Facebook: 		https://www.facebook.com/fh5co

	//////////////////////////////////////////////////////
	 -->

  	<!-- Facebook and Twitter integration -->
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />

	<!-- <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet"> -->
	<!-- <link href="https://fonts.googleapis.com/css?family=Playfair+Display:400,400i" rel="stylesheet"> -->
	
	<!-- Animate.css -->
	<link rel="stylesheet" href="css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="css/icomoon.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="css/bootstrap.css">

	<!-- Flexslider  -->
	<link rel="stylesheet" href="css/flexslider.css">

	<!-- Owl Carousel  -->
	<link rel="stylesheet" href="css/owl.carousel.min.css">
	<link rel="stylesheet" href="css/owl.theme.default.min.css">

	<!-- Theme style  -->
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/style3.css">

	<!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
		<script src="js/jquery.min.js"></script>
	<%
		UserInfoVO vo = (UserInfoVO)session.getAttribute("user");
	%>
	<script>
	jQuery(document).ready(function(){
		 jQuery('.toast__close').click(function(e){
		     e.preventDefault();
		     var parent = $(this).parent('.toast');
		     parent.fadeOut("slow", function() { $(this).remove(); } );
		   });
		   UVTTSLoad();
	});
	
	$(document).on('click',".toast__close",function(e){
		e.preventDefault();
		var parent = $(this).parent('.toast');
	     parent.fadeOut("slow", function() { $(this).remove(); } );
	})
	
	let color0 = "green";
	let color1 = "red";
	let color2 = "blue";
	let color3 = "gray";
	function UVTTSLoad() {
		$.ajax({
			url : "plantUVView.do",
			type : "get",
			dataType : "json",
			success : TemTTSLoad,
			error : () => {
				alert("error");
			}
		});
	}
	let dataPlus = "";
	function TemTTSLoad(data){
		console.log(data)
		dataPlus = data;
		$.ajax({
			url : "plantTempueratureView.do",
			type : "get",
			dataType : "json",
			success : AlramList,
			error : () => {
				alert("error");
			}
		});
	}
	function AlramList(data){
		var alarm = Object.assign({},dataPlus, data);
		console.log(alarm);
		let list = "";
		let colorlist = "";
		let num1 = 0;
		$.each(alarm, function(index, alarm1){
			if(alarm1=="온도가 낮아요 🌡🌡🌡🌡🌡🌡🌡🌡🌡🌡"|| alarm1=="빛이 쌔요 🔥🔥🔥🔥🔥🔥🔥🔥" || alarm1=="물이 부족해요! 💧💧💧💧💧💧💧💧💧"){
				list += "<div class=toast id='colorselect"+num1+"'>";
				num1 += 1;
				list += "<div class='toast__icon'>";
				list += "<svg version='1.1' class='toast__svg' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' x='0px' y='0px' viewBox='0 0 512 512' style='enable-background:new 0 0 512 512;' xml:space='preserve'>";
				list += "<g><g><path d='M504.502,75.496c-9.997-9.998-26.205-9.998-36.204,0L161.594,382.203L43.702,264.311c-9.997-9.998-26.205-9.997-36.204,0    c-9.998,9.997-9.998,26.205,0,36.203l135.994,135.992c9.994,9.997,26.214,9.99,36.204,0L504.502,111.7    C514.5,101.703,514.499,85.494,504.502,75.496z'></path>";
				list += "</g></g>";
				list += "</svg>";
				list += "</div>";
				list += "<div class='toast__content'>";
				list += "<p class='toast__type'>"+alarm.plant_nickname+"</p>";
			}
			if(alarm1=="온도가 낮아요 🌡🌡🌡🌡🌡🌡🌡🌡🌡🌡"){
				list += "<p class='toast__message'>"+alarm.teperatureResult+"</p>";			
			}else if(alarm1=="빛이 쌔요 🔥🔥🔥🔥🔥🔥🔥🔥"){
				list += "<p class='toast__message'>"+alarm.uvResult+"</p>";
			}else if(alarm1=="물이 부족해요! 💧💧💧💧💧💧💧💧💧") {
				list += "<p class='toast__message'>"+alarm.humidityResult+"</p>";
			}
			if(alarm1=="온도가 낮아요 🌡🌡🌡🌡🌡🌡🌡🌡🌡🌡" || alarm1=="빛이 쌔요 🔥🔥🔥🔥🔥🔥🔥🔥" || alarm1=="물이 부족해요! 💧💧💧💧💧💧💧💧💧"){
				list += "</div>";
				list += "<div class='toast__close'>";
				list += "<svg version='1.1' xmlns='http://www.w3.org/2000/svg' viewBox='0 0 15.642 15.642' xmlns:xlink='http://www.w3.org/1999/xlink' enable-background='new 0 0 15.642 15.642'>";
				list += "<path fill-rule='evenodd' d='M8.882,7.821l6.541-6.541c0.293-0.293,0.293-0.768,0-1.061  c-0.293-0.293-0.768-0.293-1.061,0L7.821,6.76L1.28,0.22c-0.293-0.293-0.768-0.293-1.061,0c-0.293,0.293-0.293,0.768,0,1.061  l6.541,6.541L0.22,14.362c-0.293,0.293-0.293,0.768,0,1.061c0.147,0.146,0.338,0.22,0.53,0.22s0.384-0.073,0.53-0.22l6.541-6.541  l6.541,6.541c0.147,0.146,0.338,0.22,0.53,0.22c0.192,0,0.384-0.073,0.53-0.22c0.293-0.293,0.293-0.768,0-1.061L8.882,7.821z'></path>";
				list += "</div>";
				list += "</div>";
				list += "</svg>";
				list += "</div>";
				list += "</div>";
			}
			
		})
		
		$(".toast__cell").html(list).trigger("create");
		$("#colorselect0").addClass("toast--red");
		$("#colorselect1").addClass("toast--green");
		$("#colorselect2").addClass("toast--blue");
		$(".toast").addClass("add-margin");
	}

	</script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

	</head>
	<body>
	
	<div class="fh5co-loader"></div>

	<div id="page">
		<nav class="fh5co-nav" role="navigation">
			<div class="container">
				<div class="row">
					<div class="col-md-3 col-xs-2">
						<div id="fh5co-logo"><a href="viewMain.do">Botanic Garden</a></div>
					</div>
					<div class="col-md-6 col-xs-6 text-center menu-1">
						<ul>
							<li class="has-dropdown">
								<a href="plantView.do">식물등록</a>
							</li>
							<li class="has-dropdown">
								<a href="plantCalendarView.do">일정확인</a>
							</li>
							<li><a href="viewRecommendList.do">식물추천</a></li>
							<li class="has-dropdown">
								<a href="viewPlantProblem.do">문제해결</a>
							</li>
							<li><a href="viewCommunityAll.do">커뮤니티</a></li>
						</ul>
					</div>
					<div class="col-md-3 col-xs-4 text-right hidden-xs menu-2">
						<ul>
							<li>🌻<%=vo.getNickname() %></a></li>
							<li><a href="viewMyPage.do">마이페이지</a></li>
						</ul>
					</div>
				</div>
				
			</div>
		</nav>

			
	
			<div class="wr_full">
				<div class="wr_container" id="wr_table">

			<div class="toast__container">
				<div class="toast__cell">
			</div>
			</div>
		</div>
		</div>
		
<!-- footer -->
<footer id="fh5co-footer" role="contentinfo">
	<div class="container">
		<div class="row copyright">
			<div class="col-md-12 text-center">
				<p>
					<small class="block">고객센터 062-314-0823</small>
					<small class="block">&copy; 2022 BOTANIC GARDEN All Rights Reserved.</small> 
				</p>
			</div>
		</div>

	</div>
</footer>
</div>
<div class="gototop js-top">
	<a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
</div><!--footer E-->

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
	</div>
	
	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Carousel -->
	<script src="js/owl.carousel.min.js"></script>
	<!-- countTo -->
	<script src="js/jquery.countTo.js"></script>
	<!-- Flexslider -->
	<script src="js/jquery.flexslider-min.js"></script>
	<!-- Main -->
	<script src="js/main.js"></script>
	<!-- Main2 -->
	<script src="js/main2.js"></script>
	
	</body>
</html>
