<%--
  Created by IntelliJ IDEA.
  User: nbeom
  Date: 2021/06/05
  Time: 1:02 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@include file="../common/libs.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form class="row g-3">
    <div class="col-md-6">
        <label for="nickName" class="form-label">name</label>
        <input type="text" class="form-control" id="nickName">
    </div>
    <div class="col-md-6">
        <label for="pwd" class="form-label">Password</label>
        <input type="password" class="form-control" id="pwd">
    </div>
    <div class="col-12">
        <label for="email" class="form-label">email</label>
        <input type="email" class="form-control" id="email" placeholder="kong@kongaru.com">
    </div>
    <div class="col-12">
        <label for="phoneNumber" class="form-label">phone number </label>
        <input type="text" class="form-control" id="phoneNumber" placeholder="Apartment, studio, or floor">
    </div>
    <div class="col-md-6">
        <label for="jumin" class="form-label">Birth</label>
        <input type="text" class="form-control" id="jumin" placeholder="960111">
    </div>
    <div class="col-12">
        <div class="form-check">
            <input class="form-check-input" type="checkbox" id="gridCheck">
            <label class="form-check-label" for="gridCheck">
                Check me out
            </label>
        </div>
    </div>
    <div class="col-12">
        <button type="button" id ="update_submit" class="btn btn-primary">Update</button>
    </div>
</form>

<script type="text/javascript">
    $(document).ready(function (){
        $("#update_submit").click(function (){
            var json = {
                'pwd' : $('#pwd').val() ,
                'nickName' : $('#nickName').val(),
                'email' : $('#email').val(),
                'phoneNumber' : $('#phoneNumber').val()*1,
                'jumin' :  $('#jumin').val()*1,
                'mileage' : 0,
                'memberId' : 0,
                'auth' : "ROLE_MEMBER"
            }
            console.log(json)
            $.ajax({
                type : 'post',
                url : '/login/register',
                contentType: 'application/json',
                data: JSON.stringify(json),
                success(data){
                    console.log(data)
                }

            })
    })})

</script>
</body>
</html>
