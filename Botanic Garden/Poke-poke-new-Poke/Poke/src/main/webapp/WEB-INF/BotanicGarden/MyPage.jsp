<%@page import="com.poke.domain.UserInfoVO"%>
<%@page import="com.poke.domain.UserChallVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Botanic Garden</title>
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
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->
	<script src="js/jquery.min.js"></script>
	<script>
	<%
		UserInfoVO vo1 = new UserInfoVO();
		if(session.getAttribute("user")!=null){
			vo1 = (UserInfoVO)session.getAttribute("user");	
		}
	%>
	$(document).ready(() => {
		PlantListLoad("<%=vo1.getId() %>");
	});
	
	function PlantDelete(plant_seq){
		$.ajax({
			url : "PlantDelete.do",
			type : "get",
			data : {"plant_seq" : plant_seq},
			dataType : "json",
			success : ReloadPage,
			error : () => {
				alert("error");
			}
		});
	function ReloadPage(data){
		alert("식물이 삭제되었습니다.");	
		location.reload();
	}
		
	}
	function PlantListLoad(id) {
		$.ajax({
			url : "plantListView.do",
			type : "get",
			data : {"UserId" : id},
			dataType : "json",
			success : PlantListView,
			error : () => {
				alert("error");
			}
		});
	}
	function PlantListView(data){
		let list = "<form action='PlantDelete.do' method='post'>";
		list += "<div class='plantCard'>";
		list += "<input type='radio' name='plantSel' id='slide_1' checked>";
		$.each(data, function(index, list1){
			if(index<data.length-1){
				list += "<input type='radio' name='plantSel' id='slide_"+(index+2)+"'>";
			}
		})
		list += "<input type='checkbox' id='slideImg'>";
		list += "<div class='slider'>";
		$.each(data, function(index, list2){
			if(index<data.length){
				list += "<label for='slide_"+(index+1)+"' class='slide slide_"+(index+1)+"'></label>";
			}
		})
		list += "</div>";
		$.each(data, function(index, list3){
			if(index<data.length){
				list += "<div class='inner_part' id='divcheck"+index+"'>";
				list += "<label for='slideImg' class='plantImg'>";
				list += "<img class='img_"+(index+1)+"' src='./images/darong"+index+".jpg'>"
				list += "</label>";
				list += "<div class='plantContent plantContent_"+(index+1)+"'>";
				list += "<div class='plantTitle'>"+data[index].plant_nickname+"</div>"
				list += "<div class='plantText'>";
				list += "귀여운 "+data[index].plant_nickname+" 구경하세요💖";
				list += "</div>";
				list += "<button type='button' class='chb' onclick='PlantDelete("+data[index].plant_seq+")'>X</button>";
				list += "</div></div>";
			}
		})
		list += "</div>";
		list += "</form>";	
		$(".myPlantImg").html(list).trigger("create");
		}	
		
		
	</script>
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
						<%
							if(vo1!=null){
								out.print("🌻"+vo1.getNickname());
							}else {
								out.print("<li><a href='viewLogin.do'>LOGIN</a></li>");
							}
						%>
						<li><a href="userLogout.do">로그아웃</a></li>
						<li><a href="viewMyPage.do">마이페이지</a></li>
					</ul>
				</div>
			</div>
			
		</div>
	</nav>

<div class="myPage">
	<div class="myPageProfile">
		<h3>닉네임</h3>
			<div class="btn_mem_amend">
				<button type="button" onclick="location.href='viewMemAmend.do';">회원정보수정</button>
				<button type="button" onclick="location.href='userLogout.do';">로그아웃</button>
			</div>
			<div class="challenge2">
				<h3>도전과제</h3>
			</div>
			<div class="challenge">
			<div class="chal_checklist">
				<div id="chal_checklist">
					<input id="01" type="checkbox" name="r" value="01" class="a12" checked>
					<label for="01">물 주기</label>
					<input id="02" type="checkbox" name="r" value="02" class="b12">
					<label for="02">영양제 주기</label>
					<input id="03" type="checkbox" name="r" value="03" class="c12">
					<label for="03">좋은말씀드리기</label>
					<div class="container_graph">
						<div class="progress">
						  <div class="progress-bar"></div>
						</div>
					</div>
				  </div>
			</div>
			</div>
		<div class="myPlant">
			<h3>나의 반려식물</h3>
		</div>
		<div class="myPlantBg">
			<div class="myPlantImg">
				
	   
				</div> 

		
		</div>





	</div> <!--myPageProfile E-->
</div> <!--myPage-->

	
	<footer id="fh5co-footer" role="contentinfo">
		<div class="container">
			<div class="row row-pb-md">
				<div class="col-md-4 fh5co-widget">
					<h3>BOTANIC GARDEN</h3>
					<p>보타닉 가든 어쩌구저쩌구</p>
				</div>
				<div class="col-md-2 col-sm-4 col-xs-6 col-md-push-1">
					<ul class="fh5co-footer-links">
						<li><a href="#">About</a></li>
						<li><a href="#">Help</a></li>
						<li><a href="#">Contact</a></li>
						<li><a href="#">Terms</a></li>
						<li><a href="#">Meetups</a></li>
					</ul>
				</div>

				<div class="col-md-2 col-sm-4 col-xs-6 col-md-push-1">
					<ul class="fh5co-footer-links">
						<li><a href="#">Shop</a></li>
						<li><a href="#">Privacy</a></li>
						<li><a href="#">Testimonials</a></li>
						<li><a href="#">Handbook</a></li>
						<li><a href="#">Held Desk</a></li>
					</ul>
				</div>

				<div class="col-md-2 col-sm-4 col-xs-6 col-md-push-1">
					<ul class="fh5co-footer-links">
						<li><a href="#">Find Designers</a></li>
						<li><a href="#">Find Developers</a></li>
						<li><a href="#">Teams</a></li>
						<li><a href="#">Advertise</a></li>
						<li><a href="#">API</a></li>
					</ul>
				</div>
			</div>

			<div class="row copyright">
				<div class="col-md-12 text-center">
					<p>
						<small class="block">&copy; 2018 Free HTML5. All Rights Reserved.</small> 
						<small class="block">Designed by <a href="http://freehtml5.co/" target="_blank">FreeHTML5.co</a> Demo Images: <a href="http://blog.gessato.com/" target="_blank">Gessato</a> &amp; <a href="http://unsplash.co/" target="_blank">Unsplash</a></small>
					</p>
					<p>
						<ul class="fh5co-social-icons">
							<li><a href="#"><i class="icon-twitter"></i></a></li>
							<li><a href="#"><i class="icon-facebook"></i></a></li>
							<li><a href="#"><i class="icon-linkedin"></i></a></li>
							<li><a href="#"><i class="icon-dribbble"></i></a></li>
						</ul>
					</p>
				</div>
			</div>

		</div>
	</footer>
	</div>

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

    