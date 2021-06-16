<%--
  Created by IntelliJ IDEA.
  User: nbeom
  Date: 2021/06/07
  Time: 8:43 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Accongaru</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/css/icons/favicon.ico"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/util.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body style="background-color: #666666;">

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <form class="login100-form validate-form" action="/login" method="POST">
					<span class="login100-form-title p-b-43">
						Login to continue
					</span>


                <div class="wrap-input100 validate-input" >
                    <input class="input100" type="text" name="username" id="username" placeholder="User Name">
                    <span class="focus-input100"></span>
                    <span class="label-input100"></span>
                </div>


                <div class="wrap-input100 validate-input" data-validate="Password is required">
                    <input class="input100" type="password" name="pwd" id="password" placeholder="password">
                    <span class="focus-input100"></span>
                    <span class="label-input100"></span>
                </div>

                <div class="flex-sb-m w-full p-t-3 p-b-32">

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                    <div>
                        <a href="/login/register" class="txt1">
                            singup
                        </a>
                    </div>
                </div>


                <div class="container-login100-form-btn">
                    <button class="login100-form-btn">
                        Login
                    </button>
                </div>


            </form>

            <div class="login100-more" style="background-image: url('../../../css/bg-01.jpg');">
            </div>
        </div>
    </div>
</div>
</body>
</html>