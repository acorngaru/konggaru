<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
		.quantity:focus {
			outline: 0 none;
		}
		[v-cloak] {
			display: none;
		}
	</style>
</head>
<body>
	<header id="header" style="background: rgba(20, 2, 0, 0.8); position: static" v-cloak>
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
						<li><a href="${pageContext.request.contextPath}/order/list">My Order</a></li>
						<li><a href="${pageContext.request.contextPath}/cart/list">My Cart</a></li>
					</ul>
				</nav><!-- #nav-menu-container -->
			</div>
		</div>
	</header>
	<!-- #header -->

	<div class="container" id="app" v-cloak>
		<div class="py-5 text-left">
			<h2>My Cart</h2>
		</div>

		<!--Grid row-->
		<div class="row">

			<!--Grid column-->
			<div class="col-lg-8">

				<!-- Card -->
				<div class="mb-3">
					<div class="pt-4 wish-list">

						<h5 class="mb-4">Cart (<span>{{ countCarts }}</span> items)</h5>

						<div class="row mb-4" v-for="cart in carts">
							<div class="col-md-5 col-lg-3 col-xl-3">
								<img class="img-fluid w-100"
									 :src="cart.product.imageUrl" alt="product">
							</div>
							<div class="col-md-7 col-lg-9 col-xl-9">
								<div>
									<div class="d-flex justify-content-between">
										<div>
											<h5>{{ cart.product.name }}</h5>
										</div>
										<div>
											<div class="def-number-input number-input safari_only mb-0 w-100">
												<div class="btn-group">
													<button class="genric-btn primary-border small px-2" @click="plus(cart)">+</button>
													<input type="text" class="quantity input-number border border-white text-center small px-2"
														   style="width: 55px" v-model="cart.productQuantity" @input="checkQuantityRange(cart)"/>
													<button class="genric-btn primary-border small px-2" @click="minus(cart)">-</button>
												</div>
											</div>
										</div>
									</div>
									<div class="d-flex justify-content-between align-items-center">
										<div>
											<a type="button" class="small text-black text-uppercase mr-3" @click="deleteCart(cart)">
												<i class="fas fa-trash-alt mr-1"></i> Remove item
											</a>
										</div>
										<p class="mb-0"><span><strong id="summary">{{ cart.productQuantity * cart.product.price }}</strong></span></p>
									</div>
								</div>
							</div>
						</div>
						<hr class="mb-4">

						<p class=" mb-0">
							<i class="fas fa-info-circle mr-1"></i>
							Do not delay the purchase, adding items to your cart does not mean booking them.
						</p>
					</div>
				</div>
				<!-- Card -->

				<!-- Card -->
				<div class="mb-3">
					<div class="pt-4">

						<h5 class="mb-4">We accept</h5>

						<img class="mr-2" width="45px"
							 src="https://mdbootstrap.com/wp-content/plugins/woocommerce-gateway-stripe/assets/images/visa.svg"
							 alt="Visa">
						<img class="mr-2" width="45px"
							 src="https://mdbootstrap.com/wp-content/plugins/woocommerce-gateway-stripe/assets/images/amex.svg"
							 alt="American Express">
						<img class="mr-2" width="45px"
							 src="https://mdbootstrap.com/wp-content/plugins/woocommerce-gateway-stripe/assets/images/mastercard.svg"
							 alt="Mastercard">
						<img class="mr-2" width="45px"
							 src="https://mdbootstrap.com/wp-content/plugins/woocommerce/includes/gateways/paypal/assets/images/paypal.png"
							 alt="PayPal acceptance mark">
					</div>
				</div>
				<!-- Card -->

			</div>
			<!--Grid column-->

			<!--Grid column-->
			<div class="col-lg-4">

				<!-- Card -->
				<div class="mb-3">
					<div class="pt-4">

						<h5 class="mb-3">The total amount of</h5>

						<ul class="list-group list-group-flush">
							<li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-0">
								Temporary amount
								<span>&#8361; {{ getTotalPrice }}</span>
							</li>
							<li class="list-group-item d-flex justify-content-between align-items-center px-0">
								Shipping
								<span>Free</span>
							</li>
							<li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 mb-3">
								<div>
									<strong>The total amount of</strong>
									<strong>
										<p class="mb-0">(including VAT)</p>
									</strong>
								</div>
								<span><strong>&#8361; {{ getTotalPrice }}</strong></span>
							</li>
						</ul>

						<button type="button" class="genric-btn primary w-100 text-uppercase" @click="goToCheckout">go to checkout</button>

					</div>
				</div>
				<!-- Card -->

			</div>
			<!--Grid column-->

		</div>
		<!-- Grid row -->

	</div>

	<!-- start footer Area -->
	<footer class="footer-area section-gap" style="background: url('/image/footer-bg.jpg'); background-size: cover; margin-top: 300px">
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
	<script type="text/javascript">
		const carts = JSON.parse('<%= request.getAttribute("carts") %>');

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

		new Vue({
			el: "#app",
			data: {
				carts: carts
			},
			computed: {
				countCarts: function () {
					return this.carts ? this.carts.length : 0;
				},
				getTotalPrice: function () {
					return this.carts ? this.carts.reduce((acc, cur) => {
						return acc + (cur.productQuantity * cur.product.price);
					}, 0) : 0;
				}
			},
			methods: {
				deleteCart: function (cart) {
					fetch("/cart", {
						headers: {
							"Content-Type": "application/json"
						},
						method: "delete",
						body: JSON.stringify(cart)
					})
					.then(response => response.json())
					.then(response => {
						if (response.status === "OK")
							swal("Success", "The product has been successfully removed.", "success");
					})
				},
				plus: function (cart) {
					cart.productQuantity < 99 && cart.productQuantity++;

					fetch("/cart/update", {
						headers: {
							"Content-Type": "application/json"
						},
						method: "post",
						body: JSON.stringify(cart)
					})
				},
				minus: function (cart) {
					cart.productQuantity > 1 && cart.productQuantity--;

					fetch("/cart/update", {
						headers: {
							"Content-Type": "application/json"
						},
						method: "post",
						body: JSON.stringify(cart)
					})
				},
				checkQuantityRange: function (cart) {
					if (cart.productQuantity > 99)
						cart.productQuantity = 99;
					if (cart.productQuantity < 1)
						cart.productQuantity = 1;

					fetch("/cart/update", {
						headers: {
							"Content-Type": "application/json"
						},
						method: "post",
						body: JSON.stringify(cart)
					})
				},
				goToCheckout: function () {
					fetch("/checkout", {
						headers: {
							"Content-Type": "application/json"
						},
						method: "post",
						body: JSON.stringify(this.carts)
					})
					.then(response => response.json())
					.then(response => {
						if (response.status === "OK")
							location.href = "/checkout";
					})
				}
			}
		})
	</script>
</body>
</html>
