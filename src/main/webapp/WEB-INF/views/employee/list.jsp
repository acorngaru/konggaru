<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<jsp:include page="/WEB-INF/views/common/libs.jsp"/>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/crudTable.css">
<jsp:include page="/WEB-INF/views/common/head.jsp" />
<script type="text/javascript">
    <jsp:include page="js/toDetailBtn.jsp"/>
    <jsp:include page="js/toEdtiBtn.jsp"/>
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
                            <h2>Manage <b>Employees</b></h2>
                        </div>
                        <div class="col-sm-6">
                            <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Employee</span></a>
<%--                            <a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>--%>
                        </div>
                    </div>
                </div>
                <table id="employeePrintTable" class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th style="display: none">사번</th>
                        <th>이름</th>
                        <th>전화번호</th>
                        <th>직책</th>
                        <th>급여</th>
                        <th style="display: none">입사일</th>
                        <th style="display: none">재직현황</th>
                        <th>근무시간</th>
                        <th><input type="text" id="selectName"/></th>
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
    <jsp:include page="js/editModalEvent.jsp"/>
    <jsp:include page="js/fillTableWithEmp.jsp"/>
    <jsp:include page="js/editPageBtn.jsp"/>
    <jsp:include page="js/searchByName.jsp"/>
</script>
</body>

</html>