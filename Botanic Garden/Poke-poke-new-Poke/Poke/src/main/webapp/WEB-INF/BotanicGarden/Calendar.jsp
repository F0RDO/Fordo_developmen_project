<%@page import="com.poke.domain.UserInfoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang='en'>
<head>
<% UserInfoVO vo =(UserInfoVO)session.getAttribute("user");%>
<meta charset='utf-8' />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Shop &mdash; Free Website Template, Free HTML5 Template
   by gettemplates.co</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/icomoon.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/flexslider.css">
<link rel="stylesheet" href="css/owl.carousel.min.css">
<link rel="stylesheet" href="css/owl.theme.default.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/style2.css">
<script src="js/jquery.min.js"></script>
<script src="js/jquery.waypoints.min.js"></script>
<script src="js/jquery.countTo.js"></script>
<script src="js/jquery.flexslider-min.js"></script>
<script src="js/modernizr-2.6.2.min.js"></script>

<!--[if lt IE 9]>
   <script src="js/respond.min.js"></script>
   <![endif]-->

<link href='fullcalendar-5.11.0/lib/main.css' rel='stylesheet' />
<script src='fullcalendar-5.11.0/lib/main.js'></script>

<script type="text/javascript">

var aJsonArray = new Array();
var bJsonArray = new Array();
var a;
var id1 = "<%=vo.getId()%>";
console.log("id"+id1);
function getData() {
  data1 = $.ajax({
       url : 'plantCalendar.do',
       method : 'post',
       dataType : "json",
       data : {"id" :id1 },
       async: false
 });
  
  
   
 
 data1.done(function(data){
        $.each(data, function(index, res2) {
          $.each(res2, function(index2, res3) {
             var aJson = new Object();
              	 aJson.plantcare_seq = res3.plantcare_seq;
                 aJson.title=res3.plant_nickname;
                 aJson.start=res3.last_date.split(' ')[0];                   
                 //<a.href= "goView (res3.last_date.split(' ')[0])">;
                 //document.goView(res3.last_date.split(' ')[0]);
                 aJsonArray.push(aJson);       
       });
  });
  // alert(aJsonArray);
   data2 = $.ajax({
	    url : 'plantwater.do',
	    type : 'post',
	    dataType : 'json',
	    data : {"id" :id1},
	   
	    async: false,
	 });      	  
 
   data2.done(function(data){
      $.each(data, function(index, res2) { 
          var bJson = new Object();
          bJson.title=res2.plant_nickname;
          bJson.start=res2.last_date.split(' ')[0];      
          bJsonArray.push(bJson);
        });    
       a = aJsonArray.concat(bJsonArray);
     });   
  })
  //alert(a);
 console.log(a);
 }
  
  document.addEventListener("DOMContentLoaded", function() {
    	getData();
    	buildCalendar();
       
    });

    var today = new Date(); // @param 전역 변수, 오늘 날짜 / 내 컴퓨터 로컬을 기준으로 today에 Date 객체를 넣어줌
    var date = new Date();  // @param 전역 변수, today의 Date를 세어주는 역할

    /**
     * @brief   이전달 버튼 클릭
     */
    function prevCalendar() {
        this.today = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
        buildCalendar();    // @param 전월 캘린더 출력 요청
    }

    /**
     * @brief   다음달 버튼 클릭
     */
    function nextCalendar() {
        this.today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
        buildCalendar();    // @param 명월 캘린더 출력 요청
    }

    /**
     * @brief   캘린더 오픈
     * @details 날짜 값을 받아 캘린더 폼을 생성하고, 날짜값을 채워넣는다.
     */
    function buildCalendar() {
        let doMonth = new Date(today.getFullYear(), today.getMonth(), 1);
        let lastDate = new Date(today.getFullYear(), today.getMonth() + 1, 0);

        let tbCalendar = document.querySelector(".scriptCalendar > tbody");

        document.getElementById("calYear").innerText = today.getFullYear();                                  // @param YYYY월
        document.getElementById("calMonth").innerText = autoLeftPad((today.getMonth() + 1), 2);   // @param MM월


        // @details 이전 캘린더의 출력결과가 남아있다면, 이전 캘린더를 삭제한다.
        while(tbCalendar.rows.length > 0) {
            tbCalendar.deleteRow(tbCalendar.rows.length - 1);
        }


        // @param 첫번째 개행
        let row = tbCalendar.insertRow();



        // @param 날짜가 표기될 열의 증가값
        let dom = 1;

        // @details 시작일의 요일값( doMonth.getDay() ) + 해당월의 전체일( lastDate.getDate())을  더해준 값에서
        //               7로 나눈값을 올림( Math.ceil() )하고 다시 시작일의 요일값( doMonth.getDay() )을 빼준다.
        let daysLength = (Math.ceil((doMonth.getDay() + lastDate.getDate()) / 7) * 7) - doMonth.getDay();

        // @param 달력 출력
        // @details 시작값은 1일을 직접 지정하고 요일값( doMonth.getDay() )를 빼서 마이너스( - )로 for문을 시작한다.
       
        for(let day = 1 - doMonth.getDay(); daysLength >= day; day++) {
        
            
            let column = row.insertCell();

            // @param 평일( 전월일과 익월일의 데이터 제외 )
            if(Math.sign(day) == 1 && lastDate.getDate() >= day) {


                // @param 평일 날짜 데이터 삽입
                column.innerText = autoLeftPad(day, 2);
                
                
                //철민
                
               // column.innerHTML = "<button onclick=''>"+a[day].title+"</button>";
                
                if (day < 10){
                	day = "0"+day;
                }
                
                var dayday = today.getFullYear()+"-"+autoLeftPad((today.getMonth() + 1), 2)+"-"+day;
                column.style.color = "white";
                //console.log(dayday)
                
               				
					
                // @param 일요일인 경우
                if(dom % 7 == 1) {
                    column.style.color = "white";
                }

                // @param 토요일인 경우
                if(dom % 7 == 0) {
                    column.style.color = "white";
                    row = tbCalendar.insertRow();   // @param 토요일이 지나면 다시 가로 행을 한줄 추가한다.
                }

            }

            // @param 평일 전월일과 익월일의 데이터 날짜변경
            else {
                let exceptDay = new Date(doMonth.getFullYear(), doMonth.getMonth(), day);
                column.innerText = autoLeftPad(exceptDay.getDate(), 2);
                column.style.color = "#A9A9A9";
            }

            
            var dayday = today.getFullYear()+"-"+autoLeftPad((today.getMonth() + 1), 2)+"-"+day;
            //var aa=JSON.stringify(a[day-1]);
            for(var i=0;i<aJsonArray.length;i++){
            	if(dayday==aJsonArray[i].start){
            		//column.innerText = autoLeftPad(exceptDay.getDate(), 2);
            		
            		column.innerHTML=aJsonArray[i].start.substring(8,10)+"<div class = 'pn'><br><a href='javascript:goView("+aJsonArray[i].plantcare_seq+")'>"+aJsonArray[i].title+"</a>";
            		//column.innerText=a[i].title;
            		 
            	}
            }
            
            for(var i=0;i<bJsonArray.length;i++){
            	if(dayday==bJsonArray[i].start){
            		//column.innerText = autoLeftPad(exceptDay.getDate(), 2);
            		
            		//column.innerHTML=bJsonArray[i].start.substring(8,10)+"<br><a href='javascript:goPlantCare(\""+aJsonArray[i].title+"\")'>"+bJsonArray[i].title+"</a>";
            		column.innerHTML=bJsonArray[i].start.substring(8,10)+"<div class = 'round'><br><input id = 'check1' onclick = 'goPlantCare(\""+aJsonArray[i].title+"\")' type = 'checkbox' ><label for = 'check1'></label>"+bJsonArray[i].title+"</div>";
            		
            		
            		
            		//column.innerText=a[i].title;
            		 
            	}
            }
            // @brief   전월, 명월 음영처리
            // @details 현재년과 선택 년도가 같은경우
            
            
            if(today.getFullYear() == date.getFullYear()) {

                // @details 현재월과 선택월이 같은경우
                if(today.getMonth() == date.getMonth()) {

                    // @details 현재일보다 이전인 경우이면서 현재월에 포함되는 일인경우
                    if(date.getDate() > day && Math.sign(day) == 1) {
     					 column.style.cursor = "pointer";
                         column.onclick = function(){ calendarChoiceDay(this);}
                     }

                    // @details 현재일보다 이후이면서 현재월에 포함되는 일인경우
                    else if(date.getDate() < day && lastDate.getDate() >= day) {
                        column.style.cursor = "pointer";
                        column.onclick = function(){ calendarChoiceDay(this); }
                    }

                    // @details 현재일인 경우
                    else if(date.getDate() == day) {
                        column.style.backgroundColor = "#c9ddca";
                        column.style.cursor = "pointer";                
                        column.className +="thatks";
                        column.onclick = function(){ calendarChoiceDay(this); }                       
                    }

                // @details 현재월보다 이전인경우
                } else if(today.getMonth() < date.getMonth()) {
                    if(Math.sign(day) == 1 && day <= lastDate.getDate()) {
                    }
                }

                // @details 현재월보다 이후인경우
                else {
                    if(Math.sign(day) == 1 && day <= lastDate.getDate()) {
                        column.style.cursor = "pointer";
                        column.onclick = function(){ calendarChoiceDay(this); }
                    }
                }
            }

            // @details 선택한년도가 현재년도보다 작은경우
            else if(today.getFullYear() < date.getFullYear()) {
                if(Math.sign(day) == 1 && day <= lastDate.getDate()) {
                    column.style.backgroundColor = "#E5E5E5";
                }
            }

            // @details 선택한년도가 현재년도보다 큰경우
            else {
                if(Math.sign(day) == 1 && day <= lastDate.getDate()) {
                    column.style.cursor = "pointer";
                    column.onclick = function(){ calendarChoiceDay(this); }
                }
            }
            dom++;
          	
        }//_for_
        let thatks = document.getElementsByClassName("thatks")[0];
       $(".thatks").css({"border-radius":"50%"});
       
    }// fn_
    
    
   
    /**
     * @brief   날짜 선택
     * @details 사용자가 선택한 날짜에 체크표시를 남긴다.
     */
    function calendarChoiceDay(column) {

        // @param 기존 선택일이 존재하는 경우 기존 선택일의 표시형식을 초기화 한다.
        if(document.getElementsByClassName("choiceDay")[0]) {
            document.getElementsByClassName("choiceDay")[0].classList.remove("choiceDay");
        }

        // @param 선택일 체크 표시


        // @param 선택일 클래스명 변경
        column.classList.add("choiceDay");
    }

    /**
     * @brief   숫자 두자릿수( 00 ) 변경
     * @details 자릿수가 한자리인 ( 1, 2, 3등 )의 값을 10, 11, 12등과 같은 두자리수 형식으로 맞추기위해 0을 붙인다.
     * @param   num     앞에 0을 붙일 숫자 값
     * @param   digit   글자의 자릿수를 지정 ( 2자릿수인 경우 00, 3자릿수인 경우 000 … )
     */
    function autoLeftPad(num, digit) {
        if(String(num).length < digit) {
            num = new Array(digit - String(num).length + 1).join("0") + num;
        }
        return num;
    }
    
    
    
    
    
    
    // 여기부터
    
    
   
function palntCareInsert(plant_nickname){
        
	 
	 
	 var plant_diary = $("#plant_diary").val();
	 var plant_height = $("#plant_height").val();
	
    	
    	$.ajax({
        	
            
            url : 'plantCalendarInsert.do',
            type : 'post',
            data : {
            	"plant_nickname" :plant_nickname,
            	"plant_height" : plant_height ,
            	"plant_diary" : plant_diary
 	
            },
            success :  function(){
            	document.location.href = document.location.href;
            },
            error: function(){
               alert("error");
            }
         });
      }
                  
      
      
      // 내용보여주는 함수 (과거 식물 적었던 기록 단순히 보여주는 함수)
      function goView(plantcare_seq) { // 2022-06-13                  
      //위 alert까진 나오는데 아래 success시 나와야할 값을 못가져오는중.. 
         goview = $.ajax({
               url : "plantCareView.do",
               type : "get",
               data : {
                  "plantcare_seq" : plantcare_seq
               },
               dataType : "json"});
         	
               goview.done(function(data) { // data = {    , "count":5}
            	var list ="";
               var list2 = "";
               $("#past_myModal").modal("show");
               
               list += "<table id = 'popup_table'>"
               list += "<tr>"
               list += "<td>다이어리</td>"
               list += "</tr>"
               list += "<tr>"
               list += "<td><textarea id = 't' rows = '7'  readonly class = 'form-control'>"
                   + data.plant_diary + "</textarea></td>"
               list += "</tr>"
               list += "<tr>"
               list += "<td>성장 정도</td>"
               list += "</tr>"
               list += "<tr>"
               list += "<td><input readonly type = 'text'  id = 'plant_height' value = '"+data.plant_height+"'</td>"
               list += "</tr>"
               list += "<tr>"
               list += "</table>"
               list2+= "<input type = 'button' class = 'btn_amend' id = 'update' onclick = 'goUpdate(\""+data.plantcare_seq+"\")'value = '변경'>"
            	   $("#diary").html(list);
               	$("#u").html(list2);
               },
            );
        }
         
      // 게시글 수정하기 위해 (과거 식물 수정)
      
      function goUpdate(plantcare_seq) {
         $("#t"  ).attr("readonly", false);

         var newButton = "<button id = 'aaa' class ='btn btn-success btn-sm'   onclick='update("+plantcare_seq+")'>수정하기</button>"
         
         $("#u" ).html(newButton);

      }
      
      //(이건 수정 후 업로드인가?)
      function update(plantcare_seq) {
         // idx, title, content
         var content = $("#t").val();
         $.ajax({
            url : "plantCareUpdate.do",
            type : "post",
            data : {
               "plantcare_seq" : plantcare_seq,
               "plant_diary" : content
            },
            success : function(){
            	document.location.href = document.location.href;
            },
            error : function() {
               alert("error");
            }
         })
      }
      
      // diary,hegiht 작성하는 form 보여주는 함수 (이거 이해안됨)
      function goPlantCare(plant_nickname){
          	 $("#recent_myModal").modal("show");
          	var list ="";
          	var list2 = "";
    	    list += "<table id = 'recent_popup_table'>"
    	    list += "<input type = 'hidden' id = '"+plant_nickname+"' value = ''/>"
    	    list += "<tr>"
            list += "<td>다이어리</td>"
            list += "</tr>"
            list += "<tr>"
    	    list += "<td><textarea rows = '7' value = '식물의 다이어리를 작성해주세요.' cols='20' id = 'plant_diary' name = 'plant_diary' class = 'form-control'></textarea></td>"
    	    list += "</tr>"
    	    list += "<tr>"
    	    list += "<td>성장 정도</td>"
    	    list += "</tr>"
    	    list += "<tr>"
    	    list += "<td><input type = 'number' value = '0' id = 'plant_height' name = 'plant_height' class = 'form-control' /></td>"
    	    list += "</tr>"
    	    list += "</table>"
    	    list2+= "<input type = 'button' class = 'btn_amend' id = 'plantCareInsert' onclick = 'palntCareInsert(\""+plant_nickname+"\")'value = '등록'>"
             console.log(plant_nickname);
    	    $("#bfrm").html(list);
    	    $("#u2").html(list2);
             };
             
             
        // 자동 물주기 체크박스
             
             function goToggle(){
     			

     			const checkbox = document.getElementById('autoWater');
     			const is_checked = checkbox.checked;
     			
     			goAutoWater(is_checked);

     			
     			
     		}
        
     			function goAutoWater(is_checked){
     			console.log(is_checked);
     				
     				$.ajax({
     				
     					url : "plantAutoWater.do",
     					type : "post",
     					data : {
     						"AutoWaterSelect" : is_checked
     					},
     					success : getData,
     					error : function(){
     						alert("error");
     					}
     					
     				})
     				
     			}
   
    
    

</script>
</head>


 
<body>




   

   <div id="page">
      <nav class="fh5co-nav" role="navigation">
         <div class="container">
            <div class="row">
               <div class="col-md-3 col-xs-2">
                  <div id="fh5co-logo">
                     <a href="viewMain.do">Botanic Garden</a>
                  </div>
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
                     <!-- <li>🌻닉네임</a></li> -->
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



   


      <!--  데이터 확인 
   <div id="testtest"><script type="text/javascript" src="js/main2.js">getData();</script></div> -->


<!-- 과거 팝업 -->

 <!-- Modal -->
<div id="past_myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
     <div class = "cm"><!-- modal -->
      <div class="cm header"> 
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="cm body">
        <h4 class="cm title">지난 식물 일기</h4>
        <div id="diary"> 
        </div>
        
      </div>
      <div  id = "u" class="modal-footer">
      
      </div>
    </div>
</div>
  </div>
</div>



<!-- 미래 식물 다이어리, 성장정도 입력하는 팝업 -->
         
       
      
         
         <div id="recent_myModal" class="modal fade" role="dialog"> 
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content"><!-- m-w -->
    <div class = "cm"><!-- modal -->
      <div class="cm header"><!-- head -->
        <button type="button" class="close" data-dismiss="modal">&times;</button><!-- btn-close -->
      </div>
      <div class="cm body"><!-- content -->
        <h4 class="cm title">식물 일기</h4>
        <div id="bfrm"> 
        </div>
        
      </div>
      <div  id = "u2" class="modal-footer">
      
      </div>
    </div>
</div>
  </div>
</div>


<!-- 여기까지!!!!!!!!!!!!!!!!!!!!!! -->


<!-- 캘린더 띄움 -->

 <div class="calender_container">
 <div class="calender_bg">
 <input type = "checkbox" id = "autoWater" onClick="goToggle()"> 
 <div class= "scriptCalendar_bg">


 
 
<table class="scriptCalendar">
    <thead>
        <tr>
            <td onClick="prevCalendar();" style="cursor:pointer;">&#60;</td>
            <td colspan="5" id = "ctitle">
                <span id="calMonth">MM</span> 
                <span id="cals">/</span>
                <span id="calYear">YYYY</span>
            </td>
            <td onClick="nextCalendar();" style="cursor:pointer;">&#62;</td>
        </tr>
        <tr id = "weekend">
            <td>Su</td><td>Mo</td><td>Tu</td><td>We</td><td>Th</td><td>Fr</td><td>Sa</td>
        </tr>
    </thead>
    <tbody></tbody>
</table>
</div>
</div>
      </div>







      <script>
         document
               .addEventListener(
                     'DOMContentLoaded',
                     function() {
                        var tr6 = document
                              .querySelector('.fc-scrollgrid-sync-table tbody tr:nth-child(6)');
                        //console.log(tr6);
                        //tr6.innerHTML = "";
                        //console.log(tr6);
                     })
      </script>



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
                     <small class="block">&copy; 2018 Free HTML5. All Rights
                        Reserved.</small> <small class="block">Designed by <a
                        href="http://freehtml5.co/" target="_blank">FreeHTML5.co</a>
                        Demo Images: <a href="http://blog.gessato.com/" target="_blank">Gessato</a>
                        &amp; <a href="http://unsplash.co/" target="_blank">Unsplash</a></small>
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
   <script src="js/main3.js"></script>



</body>
</html>