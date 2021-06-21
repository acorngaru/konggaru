<%@ page contentType="text/html;charset=UTF-8" language="java" %>
function f(){
	html1 = "";
	html1 += '사번 :'	+ '<input type="text" id="clockId">';
	html1 += '<a href="#" class="btn btn-success" data-toggle="modal" id="clockIdBtn">'	+ '<i class="material-icons">';
	html1 += '&#xE147;'	+ '</i>' + '<span>' + '입력' + '</span>'	+ '</a>';
	return html1;
}

		$(function() {
			var name = "";
			var id = "";
				
			<!-- 아이디입력버튼 클릭시 -->
			$(document).on("click","#ClockIdBtn",function() {
					$.ajax({
						type : "post",
						url : "rollbook/idCheck",
						dataType : "json",
						data : {
							clockId : $("#clockId").val()
							},//end data
						contentType : "application/x-www-form-urlencoded; charset=UTF-8",
						success : function(data, status, xhr) {
							name = data.name;
							id = data.id;
							if (data.mesg == null) {
								$("#clickDiv").empty();
								var html = "";
									html += data.name + '님 출/퇴근 하시겠습니까 ?'
									html += '<button id="checkInBtn" class="btn btn-success">' + "출근" + '</button>';
									html += '<button id="checkOutBtn" class="btn btn-danger">' + "퇴근" + '</button>';
									$("#clickDiv").append(html);		
							} else {
								$("#clickDiv").empty();
								var html1 = "";
									html1 += '사번 :'	+ '<input type="text" id="clockId">';
									html1 += '<a href="#" class="btn btn-success" data-toggle="modal" id="ClockIdBtn">'	+ '<i class="material-icons">';
									html1 += '</i>' + '<span>' + '입력' + '</span>'	+ '</a>';
								$("#clickDiv").append(html1);
								alert(data.mesg); 
							}
						},//end ClockIdBtn success		
						error : function(xhr, status, error) {
								console.log(error);
								console.log(status);
						}//end ClockIdBtn error
					})//end ClockIdBtn ajax
    			})//end ClockIdBtn click
			
			<!-- 출근버튼 클릭시 -->
			$(document).on("click","#checkInBtn",function() {
					$.ajax({
						type : "post",
						url : "rollbook/clockIn",
						dataType : "json",
						data : {
							id : id,
							name : name
						},
						contentType : "application/x-www-form-urlencoded; charset=UTF-8",
						success : function(data, status, xhr) {
							if (data.mesg == null) {
								$("#clickDiv").empty();
								var html = "";
									html += '사번 :' + '<input type="text" id="clockId">';
									html += '<a href="#" class="btn btn-success" data-toggle="modal" id="ClockIdBtn">' + '<i class="material-icons">';
									html += '</i>'	+ '<span>' + '입력' + '</span>' + '</a>';
									html += data.name + '님';
									html += data.clockin + '에 출근';
								$("#clickDiv").append(html);
								clockSearchByName(1);
							} else {
								$("#clickDiv").empty();
								var html2 = "";
									html2 += '사번 :'	+ '<input type="text" id="clockId">';
									html2 += '<a href="#" class="btn btn-success" data-toggle="modal" id="ClockIdBtn">'	+ '<i class="material-icons">';
									html2 += '</i>' + '<span>'	+ '입력' + '</span>'	+ '</a>';
								$("#clickDiv").append(html2);
								alert(data.name + "님은 "	+ data.mesg);
								}
							},//end checkInBtn success
							error : function(xhr, status, error) {
									console.log(error);
									console.log(status);
							}//end error
						})//end checkInBtn ajax
					})//end checkInBtn
			
			<!-- 퇴근버튼 클릭시 -->
			$(document).on("click",	"#checkOutBtn",	function() {
					$.ajax({
						type : "post",
						url : "rollbook/clockOut",
						dataType : "json",
						data : {
							id : id,
							name : name
						},
						contentType : "application/x-www-form-urlencoded; charset=UTF-8",
						success : function(data, status, xhr) {
							if (data.mesg == null) {
								$("#clickDiv").empty();
								var html = "";
									html += '사번 :' + '<input type="text" id="clockId">';
									html += '<a href="#" class="btn btn-success" data-toggle="modal" id="ClockIdBtn">' + '<i class="material-icons">';
									html += '</i>'	+ '<span>' + '입력' + '</span>' + '</a>';
									html += data.name + '님'; 
									html += data.clockout + '에 퇴근';
									$("#clickDiv").append(html);
									clockSearchByName(1);
						    } else {
							    $("#clickDiv").empty();
							    var html3 = "";
								    html3 += '사번 :'	+ '<input type="text" id="clockId">';
								    html3 += '<a href="#" class="btn btn-success" data-toggle="modal" id="ClockIdBtn">'	+ '<i class="material-icons">';
								    html3 += '</i>' + '<span>' + '입력' + '</span>'	+ '</a>';
							    $("#clickDiv").append(html3);
							    alert(data.name + "님은 "	+ data.mesg);
					 	  	}
    					},//end checkOutBtn success
						error : function(xhr, status, error) {
							console.log(error);
							console.log(status);
						}
					})//end checkOutBtn ajax
			})//end checkOutBtn click
		})//end ready