
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Accongaru</title>
    <%@include file="../common/libs.jsp"%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/png" href="/css/icons/favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="/css/util.css">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
</head>
<body style="background-color: #666666;">

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <form class="login100-form validate-form" action="/login" method="POST">
					<span class="login100-form-title p-b-43">
						Sign Up
					</span>

                <div class="wrap-input100 validate-input" >
                    <input class="input100" type="text" name="username" id="nickName" pattern="nick name" placeholder="nick name">
                    <span class="focus-input100"></span>
                    <span class="label-input100"></span>
                </div>

                <div class="wrap-input100 validate-input" >
                    <input class="input100" type="text" name="name" id="name" placeholder="name">
                    <span class="focus-input100"></span>
                    <span class="label-input100"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Password is required">
                    <input class="input100" type="password" name="pwd" id="pwd" placeholder="password">
                    <span class="focus-input100"></span>
                    <span class="label-input100"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
                    <input class="input100" type="text" name="username" id="email" placeholder="email (xxx@kongaru.com)">
                    <span class="focus-input100"></span>
                    <span class="label-input100"></span>
                </div>
                <div class="wrap-input100 validate-input" >
                    <input class="input100" type="text" name="username" id="phoneNumber" placeholder="phone (0100000000)" >
                    <span class="focus-input100"></span>
                    <span class="label-input100"></span>
                </div>


                <div class="flex-sb-m w-full p-t-3 p-b-32">
                </div>


                <div class="container-login100-form-btn">
                    <button type="button" id="update_submit" class="login100-form-btn">
                        sign up
                    </button>
                </div>


            </form>

            <div class="login100-more" style="background-image: url('../../../css/bg-01.jpg');">
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    $(document).ready(function (){
        $("#update_submit").click(function (){
            var json = {
                'password' : $('#pwd').val() ,
                'nickName' : $('#nickName').val(),
                'email' : $('#email').val(),
                'phoneNumber' : $('#phoneNumber').val()*1,
                'name' :  $('#name').val(),
                'point' : 0,
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
