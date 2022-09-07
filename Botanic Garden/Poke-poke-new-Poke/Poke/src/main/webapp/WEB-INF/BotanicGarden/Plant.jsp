<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>


<script type="text/javascript">
	$(document).ready(function() {

		PlantList();
	});
	
	
	
	
	
	function palntInsert(){
		var fPlant = $("#Plant").serialize();
		$.ajax({
			
			url : 'plantInsert.do',	
			type : 'post',
			data : fPlant,
			success : function(){
				document.location.href = document.location.href;
			},
			error : function (){
				alert("error")
			}
		});
	}
	
	function PlantList(){
		$.ajax({
			url : 'palntList.do',
			type : 'post',
			dataType : "json",
			success : function(data){
				console.log(data);
				let list = "";
				list += "<input class='ad' list='plant_list' name='plant_name' id='plant_name' > ";
				list += "<datalist id='plant_list' display = 'none'>";
				$.each(data, function(index,res2){

				
					
					list += "<option value = "+data[index].plant_name+" label = '물주기 : "+data[index].cycle+"일'>";
				})
				list+= "<option>추가</option>";
				list+= "</datalist>"
				
				$("#plantList").html(list);
			},
			error : function(){
				alert("error")
			}
		});
	}

	
	
	
	</script>
</head>
<body>
	<form id="Plant">
		<div class="plant_reg">
			<table id="plant_reg_add">
				<tr>
					<td>
						<h3>식물 추가</h3>
					</td>
					<td>
						<h3>애칭</h3>
					</td>
					<!-- <td> <h3>물 주는 주기</h3> </td> -->
					<td>
						<h3>마지막으로 물 준 날</h3>
					</td>
					<td></td>
				</tr>
				<tr class="submit_add">
					<td id="plantList"></td>
					<td><input class="ad" type="text" name="plant_nickname"
						id="plant_lastdate" placeholder="애칭을 입력해주세요."></td>
					<!-- <td><input class="ad"  readonly = "true" type="number" min="0" max="100" name="cycle" value = "0" id = "cycle" ></td> -->
					<!-- 오늘 이후 선택 안되도록 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
					<td><input class="ad" type="date" id="plant_lastdate"
						name="plant_lastdate"></td>
					<td></td>
				</tr>
			</table>

			<!-- + 눌렀을 때 아래 추가한 값이 계속 나타날 수 있도록 설정해야 함 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->

			<div class="plant_add_list"></div>
			<div class="btn_Plant_reg">
				<button type="button" class="btn btn-info btn-sm"
					onclick="palntInsert()">확인</button>
			</div>
		</div>
	</form>





</body>
</html>