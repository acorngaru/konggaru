<%@ page contentType="text/html;charset=UTF-8" language="java" %>
$(function() {
	var name = "";
	var id = "";
	
	$(document).on("click","#ClockIdBtn", function() {
		$.ajax({
			type : "post",
			url : "RollBook/idCheck",
			dataType : "json",
			data : {
				ClockId : $("#ClockId").val()
				
			},//end data
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(data, status, xhr) {
				 name = data.name;
				 id = data.id;
			if(data.mesg==null){
				$("#ClickDiv").empty();
				var html = "";
				html += data.name + '님 출/퇴근 하시겠습니까 ?'
					html += '<button id="checkInBtn" class="btn btn-success">'
						+ "출근" + '</button>';
				html += '<button id="checkOutBtn" class="btn btn-danger">'
						+ "퇴근" + '</button>'; 		
				$("#ClickDiv").append(html);
			}else{
        		$("#ClickDiv").empty();
				var html = "";
            	html += '사번 :' + '<input type="text" id="ClockId">';
            	html += '<a href="#" class="btn btn-success" data-toggle="modal" id="ClockIdBtn">'+'<i class="material-icons">';
            	html += '&#xE147;' + '</i>' + '<span>' + '입력' + '</span>' + '</a>';
				$("#ClickDiv").append(html);
				
				alert(data.mesg);
			}
				
			},//end ClockIdBtn success		
			error : function(xhr, status, error) {
				console.log(error);
				console.log(status);
			}//end ClockIdBtn error
			
		})//end ClockIdBtn ajax
	})//end ClockIdBtn click
	
		
	$(document).on("click","#checkInBtn",function() {
		$.ajax({
			type : "post",
            url : "RollBook/ClockIn",
            dataType : "json",
            data : {
                id : id,
                name : name
            },
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success : function(data, status, xhr) {
            	if(data.mesg==null){
            	$("#ClickDiv").empty();
				var html = "";
            	html += '사번 :' + '<input type="text" id="ClockId">';
            	html += '<a href="#" class="btn btn-success" data-toggle="modal" id="ClockIdBtn">'+'<i class="material-icons">';
            	html += '&#xE147;' + '</i>' + '<span>' + '입력' + '</span>' + '</a>';
            	html += data.name + "님 "
				html += data.clockin + '에 출근'
				$("#ClickDiv").append(html);
            	clockSearchByName(1);
            	}else{
            		$("#ClickDiv").empty();
					var html = "";
	            	html += '사번 :' + '<input type="text" id="ClockId">';
	            	html += '<a href="#" class="btn btn-success" data-toggle="modal" id="ClockIdBtn">'+'<i class="material-icons">';
	            	html += '&#xE147;' + '</i>' + '<span>' + '입력' + '</span>' + '</a>';
					$("#ClickDiv").append(html);
					
					alert(data.name+"님은 "+data.mesg);
            	}
            	
            },//end checkInBtn success
            error : function(xhr, status, error) {
				console.log(error);
				console.log(status);
			}//end error
		})//end checkInBtn ajax
	})//end checkInBtn
	$(document).on("click","#checkOutBtn",function() {
		$.ajax({
			type : "post",
            url : "RollBook/ClockOut",
            dataType : "json",
            data : {
                id : id,
                name : name
            },
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success : function(data, status, xhr) {
            	if(data.mesg==null){
            	$("#ClickDiv").empty();
            	var html = "";
            	html += '사번 :' + '<input type="text" id="ClockId">';
            	html += '<a href="#" class="btn btn-success" data-toggle="modal" id="ClockIdBtn">'+'<i class="material-icons">';
            	html += '&#xE147;' + '</i>' + '<span>' + '입력' + '</span>' + '</a>';
            	html += data.name + "님 "
				html += data.clockout + '에 퇴근'
            	
            	$("#ClickDiv").append(html);
            	clockSearchByName(1);
            	}else{
            		$("#ClickDiv").empty();
					var html = "";
	            	html += '사번 :' + '<input type="text" id="ClockId">';
	            	html += '<a href="#" class="btn btn-success" data-toggle="modal" id="ClockIdBtn">'+'<i class="material-icons">';
	            	html += '&#xE147;' + '</i>' + '<span>' + '입력' + '</span>' + '</a>';
					$("#ClickDiv").append(html);
					
					alert(data.name +"님은 "+data.mesg);
            	}
            },//end checkOutBtn success
            error : function(xhr, status, error) {
                console.log(error);
                console.log(status);
            }
		})//end checkOutBtn ajax
	})//end checkOutBtn click
})//end ready