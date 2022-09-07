<%@page import="com.poke.domain.UserInfoVO"%>
<%@page import="com.poke.domain.ChoiceVO"%>
<%@page import="com.poke.domain.QuestionVO"%>
<%@page import="com.poke.DAO.QuestionDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Botanic Garden</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
	<link rel="stylesheet" href="css/join_style.css">

	<!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

	<!--Typelt-->
	<script src="<https://unpkg.com/typeit@8.5.5/dist/index.umd.js>"></script>
	<script src="https://cdn.jsdelivr.net/npm/typeit@7.0.4/dist/typeit.min.js"></script>


<style>
	:root {
		--ti-cursor-margin-left: 0.1em;
		--ti-cursor-color: rgb(27, 90, 35);
	}
</style>

<script type="text/javascript">


$(document).ready(() => {
	QuestionlistLoad(1);
});

let choice1_num = 0;
let choice2_num = 0;
let choice_name = "";
function QuestionlistLoad(num) {
	$.ajax({
		url : "viewQuestionList.do",
		type : "get",
		data : {"Question_num" : num},
		dataType : "json",
		success : QuestionlistView,
		error : () => {
			alert("error");
		}
	});
}


function QuestionlistView (data){
	console.log(data[0].Question_contents)
	if(data[0].Question_num==1){
		new TypeIt("#choicePlantQ",{
			strings: data[0].Question_contents,
			speed: 75,
			waitUntilVisible: true,
		}).go();
		ChoiceListLoad()
	}else {
		$('#choicePlantQ').css("left", "33%");
		$('#choicePlantQ').html("");
		new TypeIt("#choicePlantQ",{
			strings: choice_name+data[0].Question_contents,
			speed: 75,
			waitUntilVisible: true,
		}).go();
		Choice2ListLoad()
	}
}

function ChoiceListLoad(){
	$.ajax({
		url : "viewChoiceList.do",
		type : "get",
		dataType : "json",
		success : ChoiceListView,
		error : () => {
			alert("error");
		}
	});
}

function ChoiceListView(data){
	let choice = "<ul class='ks-cboxtags'>";
	$.each(data, function(index, choice1){
		choice += "<li><input type='checkbox' id='checkbox"+index+"' onclick='ChoiceNumSave("+(index+1)+"); ChoiceNameSave(\""+data[index].choice_contents+"\"); QuestionlistLoad(2);'><label for='checkbox"+index+"'>";
		choice += " "+(data[index].choice_contents)+"</label></li>";
	})
	choice += "</ul>";
	$('#container_check').html(choice);
}

function Choice2ListLoad(){
	$.ajax({
		url : "viewChoice2List.do",
		type : "get",
		dataType : "json",
		success : Choice2ListView,
		error : () => {
			alert("error");
		}
	});
}

function Choice2ListView(data){
	let choice2= "<ul class='ks-cboxtags'>";
	$.each(data, function(index, choice4){
		choice2 += "<li><input type='checkbox' id='checkbox"+index+"' onclick='Choice2NumSave("+(index+1)+");'><label for='checkbox"+index+"'>";
		choice2 += data[index].choice_contents+"</label></li>";
	})
	choice2 += "</ul>";
	$('#container_check').html(choice2);
}

function ChoiceNumSave(num){
	choice1_num = num;
}
function Choice2NumSave(num){
	choice2_num = num;
	$.ajax({
		url : "viewRecommendPlantList.do",
		type : "get",
		dataType : "json",
		data : {"choice1_num" : choice1_num, "choice2_num" : choice2_num},
		success : FileLocationLoad,
		error : () => {
			alert("error");
		}
	});
	console.log(choice2_num);
}
function ChoiceNameSave(name){
	choice_name = name
	console.log(choice_name)
}

function FileLocationLoad(data){
	let arr = [];
	$.each(data, function(index, arr1){
		arr.push(data[index].recommend_num);
		console.log(arr);
	})
	$.ajax({
		url : "fileLocationLoad.do",
		type : "post",
		traditional : true,
		data : {"recommend_num" : arr},
		dataType : "json",
		success : viewRecommendPlantList,
		error : () => {
			alert("해당 카테고리의 식물은 존재하지 않습니다.");
		}
	});
}
function viewRecommendPlantList(data){
	console.log(data)
	$('.re_containerQ').css("display","none");
	$('.container_check').css("display","none");
	let list = "<div class='re_list'><ul class='re_plantList'>";
	$.each(data, function(index, obj){
		list += "<li class='re_plantImage'>";
		list += "<img src='plantfile/"+obj.file_location+"'>";
		list += "<div class='re_plantName'><h2>"+data[index].recommend_plant+"</h2></div></li>";
	});
	list += "</ul></div>"
	$('.re_container').html(list);
}
</script>
</head>
<body>
	<%
		UserInfoVO vo = new UserInfoVO();
		if(session.getAttribute("user")!=null){
			vo = (UserInfoVO)session.getAttribute("user");	
		}
	%>
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
							<li class="has-dropdown">
							<%
								if(vo!=null){
									out.print("🌻"+vo.getNickname());
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
	
<div class="wr_full">
	<div class="re_container" id="wr_table">
		<div class="re_containerQ">
			<h1 id="choicePlantQ"></h1>
			<h1 id="choicePlantQ2"></h1>
		</div>
		<div class="container_check" id="container_check">
			
		</div>
	</div> <!--wr_container E-->
</div> <!--wr_full-->

<div id=""></div>
























	<!-- footer -->
	<footer id="fh5co-footer" role="contentinfo">
		<div class="container">
			<div class="row row-pb-md">
				<div class="col-md-4 fh5co-widget">
					<h3>Shop.</h3>
					<p>Facilis ipsum reprehenderit nemo molestias. Aut cum mollitia reprehenderit. Eos cumque dicta adipisci architecto culpa amet.</p>
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