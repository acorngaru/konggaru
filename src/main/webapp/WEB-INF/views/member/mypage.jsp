<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Acorngaru</title>

	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:100,200,400,300,500,600,700">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
		  integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/magnific-popup.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/nice-select.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.carousel.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/client-main.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-clockpicker.min.css">
	<style>
		[v-cloak] {
			display: none;
		}
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
	<header id="header" style="background: rgba(20, 2, 0, 0.8); position: static">
		<div class="header-top">
			<div class="container">
				<div class="row justify-content-end">
					<div class="col-lg-8 col-sm-4 col-8 header-top-right p-2">
						<ul>
							<li>
								Mon-Fri: 10am to 5pm
							</li>
							<li>
								Sat-Sun: 11am to 4pm
							</li>
							<li>
								Tel: 02-538-0958
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="container">
			<div class="row align-items-center justify-content-between d-flex">
				<div id="logo">
					<a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/image/acorngaru-logo.png" alt="logo"/></a>
				</div>
				<nav id="nav-menu-container">
					<ul class="nav-menu">
						<li v-if="member == null"><a href="${pageContext.request.contextPath}/login">Login</a></li>
						<li v-else><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
						<li class="menu-active"><a href="${pageContext.request.contextPath}/">Home</a></li>
						<li><a href="${pageContext.request.contextPath}/member/mypage">My Page</a></li>
						<li><a href="${pageContext.request.contextPath}/order/list">My Order</a></li>
						<li><a href="${pageContext.request.contextPath}/cart/list">My Cart</a></li>
					</ul>
				</nav><!-- #nav-menu-container -->
			</div>
		</div>
	</header>


	<div class="container" id="app">
		<div class="input-form-backgroud row mb-60">
			<div class="input-form col-md-12 mx-auto">
				<h4 class="mb-3">My Page</h4>
				<form class="validation-form" @submit="update">
					<!-- novalidate -->
					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="name">name</label> <input type="text"
								class="form-control" name="name" id="name" placeholder=""
								v-model="member.name" required>
							<div class="invalid-feedback">name</div>
						</div>
						<div class="col-md-6 mb-3">
							<label for="nickname">nickname</label> <input type="text"
								class="form-control" name="nickName" id="nickName"
								disabled="disabled" v-model="member.nickName" required>
							<div class="invalid-feedback">nickname</div>
						</div>
					</div>

					<div class="mb-3">
						<label for="password">password</label> <input type="password"
							class="form-control" name="password" id="password"
							v-model="member.password" required>
						<div class="invalid-feedback">password</div>
					</div>

					<div class="mb-3">
						<label for="passwordConfirmation">password confirmation</label> <input type="password"
																	  class="form-control" name="password" id="passwordConfirmation"
																	  v-model="passwordConfirmation" @input="checkPassword" required>
						<div class="invalid-feedback">password</div>
					</div>

					<div class="mb-3">
						<label for="email">e-mail</label> <input type="email"
							class="form-control" name="email" id="email" v-model="member.email"
							@input="checkDuplicateEmail" required>
						<div class="invalid-feedback">e-mail</div>
					</div>

					<div class="mb-20">
						<label for="phoneNumber">phone</label> <input type="text"
							class="form-control" name="phoneNumber" id="phoneNumber"
							@input="checkDuplicatePhoneNumber" v-model="member.phoneNumber" required>
						<div class="invalid-feedback">phone</div>
					</div>

					<button class="genric-btn primary w-100" type="submit">Update
						your account</button>
				</form>
			</div>
		</div>
	</div>

	<!-- start footer Area -->
	<footer class="footer-area section-gap mt-auto" style="background: url('/image/footer-bg.jpg'); background-size: cover">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-8 col-sm-6">
					<div class="single-footer-widget">
						<h6>About Us</h6>
						<p>
							Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore dolore magna aliqua.
						</p>
						<p class="footer-text">
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						</p>
					</div>
				</div>

				<div class="col-lg-4 col-md-4 col-sm-6 social-widget">
					<div class="single-footer-widget">
						<h6>Follow Us</h6>
						<p>Let us be social</p>
						<div class="footer-social d-flex align-items-center">
							<a href="#"><i class="fab fa-facebook"></i></a>
							<a href="#"><i class="fab fa-twitter"></i></a>
							<a href="#"><i class="fab fa-dribbble"></i></a>
							<a href="#"><i class="fab fa-behance"></i></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
			integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
			integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
			crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
			crossorigin="anonymous"></script>
	<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBhOdIF3Y9382fqJYt5I_sswSrEw5eihAA"></script>
	<script src="${pageContext.request.contextPath}/js/easing.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/hoverIntent.js"></script>
	<script src="${pageContext.request.contextPath}/js/superfish.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.ajaxchimp.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.magnific-popup.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/owl.carousel.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.sticky.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.nice-select.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/parallax.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/waypoints/4.0.1/jquery.waypoints.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.counterup.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap-clockpicker.min.js"></script>
	<script>
		new Vue({
			el: "#header",
			data: {
				member: null
			},
			created: function () {
				fetch("/member/current")
						.then(response => response.json())
						.then(response => this.member = response.data)
						.catch(() => this.member = null);
			}
		})

		const member = JSON.parse('<%= request.getAttribute("member") %>');

		new Vue({
			el: "#app",
			data: {
				member: {
					...member,
					password: ""
				},
				originalEmail: member.email,
				originalPhoneNumber: member.phoneNumber,
				passwordConfirmation: ""
			},
			methods: {
				update: function (e) {
					e.preventDefault();

					fetch("/member/update", {
						headers: {
							"Content-Type": "application/json"
						},
						method: "post",
						body: JSON.stringify(this.member)
					})
					.then(response => response.json())
					.then(response => {
						console.log(response)
						if (response.status === "OK") {
							swal("Success", "Successfully updated.", "success")
							.then(() => location.reload())
						}
					});
				},
				checkPassword: function (e) {
					this.member.password && e.target.setCustomValidity(this.member.password === this.passwordConfirmation ?
							"" : "비밀번호가 올바르지 않습니다.");
				},
				checkDuplicateEmail: function (e) {
					console.log(this.originalEmail, e.target.value);
					e.target.setCustomValidity(
							e.target.value === this.originalEmail ? "" : "이미 존재하는 이메일입니다."
					);

					if (e.target.value !== this.originalEmail) {
						fetch("/member/check?email=" + e.target.value)
						.then(response => response.json())
						.then(response => {
							e.target.setCustomValidity(
									response.status === "OK" ? "" : "이미 존재하는 이메일입니다."
							)
						})
					}
				},
				checkDuplicatePhoneNumber: function (e) {
					e.target.setCustomValidity(
							e.target.value === this.originalPhoneNumber ? "" : "이미 존재하는 전화번호입니다."
					);
					if (e.target.value !== this.originalPhoneNumber) {
						fetch("/member/check?phoneNumber=" + e.target.value)
						.then(response => response.json())
						.then(response => {
							e.target.setCustomValidity(
									response.status === "OK" ? "" : "이미 존재하는 전화번호입니다."
							)
						})
					}
				}
			}
		})
	</script>
</body>
</html>