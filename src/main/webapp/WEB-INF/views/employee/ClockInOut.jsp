<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<jsp:include page="/WEB-INF/views/common/libs.jsp"/>
<jsp:include page="/WEB-INF/views/common/head.jsp"/>
<script type="text/javascript">
    <jsp:include page="js/toDetailBtn.jsp"/>
    <jsp:include page="js/toEdtiBtn.jsp"/>
</script>

<script type="text/javascript">
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
				$("#ClickDiv").empty();
				var html = "";
				html += data.name + '님 출/퇴근 하시겠습니까 ?'
					html += '<button id="checkInBtn" class="btn btn-success">'
						+ "출근" + '</button>';
				html += '<button id="checkOutBtn" class="btn btn-danger">'
						+ "퇴근" + '</button>'; 
				if(data.inTime!=null){html += data.name + "님 "
									  html += data.inTime + '에 출근'}			
				$("#ClickDiv").append(html);
				
				
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
</script>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
    <!-- Sidebar -->
    <jsp:include page="/WEB-INF/views/common/sidebar.jsp" />
    <!-- Header -->
    <jsp:include page="/WEB-INF/views/common/head.jsp" />
    <!-- Content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->

        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Manage <b>RollBook</b></h2>
                        </div>
                        <div class="col-sm-6" id="ClickDiv">
                        	사번 : <input type="text" id="ClockId">
                            <a href="#" class="btn btn-success" data-toggle="modal" id="ClockIdBtn"><i class="material-icons">&#xE147;</i> <span>입력</span></a>
                        </div>
                    </div>
                </div>
                <table id="rollBookPrintTable" class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>사번</th>
                        <th>이름</th>
                        <th>출근시간</th>
                        <th>퇴근시간</th>
                        <th>근무시간</th>
                        <th>              
                        <select id="ClockSearchType" class="form-control">
                            <option value="name">이름</option>
                            <option value="inTime">출근시간</option>
                            <option value="outTime">퇴근시간</option>
                        </select>                    	
                    	</th>
                        <th><input type="text" id="ClockselectName" /></th>
                    </tr>
                    </thead>
                    <tbody>
                    <%--여기는 search를 통해 불러올 것입니다.--%>
                    </tbody>
                </table>

                <div class="clearfix">
                    <ul class="pagination">
                        <li class="page-item" id="preBtn"><a href="#">Previous</a></li>
                        <li class="page-item" id="nextBtn"><a href="#" class="page-link">Next</a></li>
                    </ul>
                </div>
            </div>
        </div><!-- /.container-fluid -->
        <br><br>
        <!-- The Modal -->
        <jsp:include page="list_modal.jsp"/>
    </div>
    <!-- /content -->
</div>


</div>
    <!-- Footer -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</div>
<%--import js--%>
<script type="text/javascript">
	 <jsp:include page="js/clockSearchByName.jsp"/>
     <jsp:include page="js/clockEditPageBtn.jsp"/>
     <jsp:include page="js/fillTableWithRollBook.jsp"/>
     <jsp:include page="js/clockIdCheck.jsp"/>

</script>
</body>

</html>