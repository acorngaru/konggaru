
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<jsp:include page="/WEB-INF/views/common/head.jsp" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<body class="hold-transition sidebar-mini">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<jsp:include page="/WEB-INF/views/ingredient/printListJquery.jsp"/>
<%int pageNo = 1;%>
<div class="wrapper">
    <!-- Sidebar -->
    <jsp:include page="/WEB-INF/views/common/sidebar.jsp" />

    <!-- Content -->
    <div class="wrapper">
        <!-- Sidebar -->
        <jsp:include page="/WEB-INF/views/common/sidebar.jsp" />
        <!-- Header -->
        <jsp:include page="/WEB-INF/views/ingredient/common/header.jsp" />
        <!-- Content -->
        <div class="content-wrapper">
            <!-- Content Header (Page header) -->
            <div class="content-header">
                <div class="container-fluid">
                    <div class="row mb-2">
                        <div class="col-sm-6">
                            <h1 class="m-0">재고관리 Page</h1>
                        </div><!-- /.col -->
                        <div class="col-sm-6">
                            <ll class="breadcrumb float-sm-right">
                                <button type="button" data-toggle="modal" data-target="#createModal" class="btn btn-primary float-right" style="WIDTH: 45pt; HEIGHT: 30pt; FONT-SIZE:10pt;">추가</button>
                                <a href="#deleteAllModal" class="deleteAll" data-toggle="modal"><button type="button" class="btn btn-danger float-right" style="WIDTH: 45pt; HEIGHT: 30pt; FONT-SIZE:10pt;" data-toggle="tooltip" title="Delete">삭제</button></a>
                            </ll>
                        </div><!-- /.col -->
                    </div><!-- /.row -->
                </div><!-- /.container-fluid -->
            </div>
        <!-- /.content-header -->
        <!-- Main content -->
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div>
                        <strong>재고관리</strong> 현재 재고 현황을 보여줍니다.
                    </div>
                    <table class="table table-striped table-hover" id = "ingredientTable">
                        <thead>
                        <tr>
                            <th>
                                <span class="custom-checkbox">
									<input type="checkbox" id="selectAll">
									<label for="selectAll"></label>
								</span>
                            </th>
                            <th>번호</th>
                            <th>품명</th>
                            <th>재고</th>
                            <th>판매처</th>
                            <th>재고 단위</th>
                            <th>개당 가격</th>
                            <th>총합 가격</th>
                            <th><input type="text" id="selectName" /></th>
                        </tr>
                        </thead>
                        <tbody>
                        <!-- jquery 로 처리-->
                        </tbody>
                    </table>

                </div>


            </div>
            <!-- /.row -->
        </div><!-- /.container-fluid -->
            <div class="text-center">
                <ul class="pagination justify-content-center">
                    <li class="page-item" id="preBtn"><a href="#" class="page-link">Previous</a></li>
                    <li class="page-item" id="nextBtn"><a href="#" class="page-link">Next</a></li>
                </ul>
            </div>
        </div>
        <!-- /.content -->
        <jsp:include page="/WEB-INF/views/ingredient/modal/modalSet.jsp"></jsp:include>

        <!-- Footer -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</div>
</body>
</html>
