<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Acorngaru</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<style>
/*     body {
      min-height: 100vh;

      background: -webkit-gradient(linear, left bottom, right top, from(#92b5db), to(#1d466c));
      background: -webkit-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
      background: -moz-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
      background: -o-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
      background: linear-gradient(to top right, #92b5db 0%, #1d466c 100%);
    } */
.input-form {
	max-width: 680px;
	margin-top: 80px;
	padding: 32px;
	background: #fff;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	-webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	-moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
}
</style>
</head>

<body>
	<div class="container">
		<div class="input-form-backgroud row">
			<div class="input-form col-md-12 mx-auto">
				<h4 class="mb-3">My Page</h4>
				<form class="validation-form" action="/member/update" method="post">
					<!-- novalidate -->
					<input type="hidden" value="1" name="id">
					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="name">name</label> <input type="text"
								class="form-control" name="name" id="name" placeholder=""
								value="" disabled="disabled" required>
							<div class="invalid-feedback">name</div>
						</div>
						<div class="col-md-6 mb-3">
							<label for="nickname">nickname</label> <input type="text"
								class="form-control" name="nickName" id="nickName"
								placeholder="" value="" required>
							<div class="invalid-feedback">nickname</div>
						</div>
					</div>

					<div class="mb-3">
						<label for="password">password</label> <input type="password"
							class="form-control" name="password" id="password" placeholder=""
							value="" required>
						<div class="invalid-feedback">password</div>
					</div>

					<div class="mb-3">
						<label for="email">e-mail</label> <input type="email"
							class="form-control" name="email" id="email"
							placeholder="you@example.com" required>
						<div class="invalid-feedback">e-mail</div>
					</div>

					<div class="mb-3">
						<label for="phone">phone</label> <input type="text"
							class="form-control" name="phoneNumber" id="phoneNumber"
							placeholder="" value="" required>
						<div class="invalid-feedback">phone</div>
					</div>

					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="point">point</label> <input type="text"
								class="form-control" name="point" id="point" placeholder=""
								value="" disabled="disabled" required>
							<div class="invalid-feedback">point</div>
						</div>
						<div class="col-md-6 mb-3">
							<label for="auth">auth</label> <input type="text"
								class="form-control" name="auth" id="auth" placeholder=""
								disabled="disabled" value="" required>
							<div class="invalid-feedback">auth</div>
						</div>
					</div>

					<button class="btn btn-primary btn-lg btn-block" type="submit">Update
						your account</button>
				</form>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">

$(function() {
	
	function getMemberInfo(memberId) {
		fetch("/member/show", {
			headers: {
				"Content-Type" : "application/json; charset=utf-8"
			},
			method: "post",
			body: memberId
		})
		.then(response => response.json())
		.then(data => {
			$("#name").val(data.name);
			$("#nickName").val(data.nickName);
			$("#email").val(data.email);
			$("#phoneNumber").val(data.phoneNumber);
			$("#point").val(data.point);
			$("#auth").val(data.auth);
		})
		.catch(error => console.log("e >>>", error));
	} //

	getMemberInfo(1);
	
})

</script>

</html>