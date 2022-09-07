<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
<script type="text/javascript">
	
function PlantDelete(){
	var fPlant = $("#plantDelete").serialize();
	$.ajax({
		
		url : 'PlantDelete.do',	
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



</script>
	
	
	
</head>
<body>





	<form id = "plantDelete">
		<input type = "text" id = "plant_nickname" name= "plant_nickname">
		<button type = "button" class="btn btn-info btn-sm" onclick="PlantDelete()">삭제</button>
	</form>

</body>
</html>