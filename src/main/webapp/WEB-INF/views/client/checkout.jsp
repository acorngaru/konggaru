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
                        <li class="menu-active"><a href="#home">Home</a></li>
                        <li><a href="#about">About</a></li>
                        <li><a href="#coffee">Coffee</a></li>
                        <li><a href="#">My Page</a></li>
                        <li><a href="#">My Order</a></li>
                    </ul>
                </nav><!-- #nav-menu-container -->
            </div>
        </div>
    </header>
    <!-- #header -->

    <div class="container" id="app">
        <div class="py-5 text-left">
            <h2>Checkout</h2>
        </div>

        <div class="row">
            <div class="col-md-4 order-md-2 mb-4">
                <h4 class="d-flex justify-content-between align-items-center mb-3">
                    <span class="text-muted">Your cart</span>
                    <span class="badge badge-warning badge-pill">{{ order.orderDetails.length }}</span>
                </h4>
                <ul class="list-group mb-3">
                    <li class="list-group-item d-flex justify-content-between lh-condensed" v-for="orderDetail in order.orderDetails">
                        <div>
                            <h6 class="my-0">{{ orderDetail.product.name }}</h6>
                            <small class="text-muted">Quantity: {{ orderDetail.productQuantity }}</small>
                        </div>
                        <span class="text-muted">&#8361; {{ orderDetail.product.price }}</span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between">
                        <span>Total (KRW)</span>
                        <strong>&#8361; {{ order.totalPrice }}</strong>
                    </li>
                </ul>
            </div>

            <div class="col-md-8 order-md-1">
                <h4 class="mb-3">Billing address</h4>
                <form class="needs-validation" novalidate>
                    <div class="mb-3">
                        <label for="name">Name</label>
                        <input type="text" class="form-control" id="name" placeholder="Name">
                    </div>

                    <div class="mb-3">
                        <label for="nickname">Nickname</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="nickname" placeholder="Nickname" required>
                            <div class="invalid-feedback" style="width: 100%;">
                                Your nickname is required.
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="pickupTime">Pickup Time</label>
                        <div class="row">
                            <div class="input-group clockpicker col-md-4">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="fas fa-clock"></i>
                                    </span>
                                </div>
                                <input id="pickupTime" type="text" class="form-control" v-model="order.pickupTime">
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="phoneNumber">Phone Number</label>
                        <input type="tel" class="form-control" id="phoneNumber" placeholder="01012345678">
                        <div class="invalid-feedback">
                            Please enter a valid email address for shipping updates.
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="email">Email <span class="text-muted">(Optional)</span></label>
                        <input type="email" class="form-control" id="email" placeholder="you@example.com">
                        <div class="invalid-feedback">
                            Please enter a valid email address for shipping updates.
                        </div>
                    </div>

                    <hr class="mb-4">

                    <h4 class="mb-3">Payment</h4>

                    <div class="d-block my-3">
                        <div class="custom-control custom-radio">
                            <input id="credit" name="paymentMethod" type="radio" class="custom-control-input" checked required>
                            <label class="custom-control-label" for="credit">Credit card</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input id="debit" name="paymentMethod" type="radio" class="custom-control-input" required>
                            <label class="custom-control-label" for="debit">Debit card</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input id="paypal" name="paymentMethod" type="radio" class="custom-control-input" required>
                            <label class="custom-control-label" for="paypal">PayPal</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="cc-name">Name on card</label>
                            <input type="text" class="form-control" id="cc-name" placeholder="" required>
                            <small class="text-muted">Full name as displayed on card</small>
                            <div class="invalid-feedback">
                                Name on card is required
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="cc-number">Credit card number</label>
                            <input type="text" class="form-control" id="cc-number" placeholder="" required>
                            <div class="invalid-feedback">
                                Credit card number is required
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3 mb-3">
                            <label for="cc-expiration">Expiration</label>
                            <input type="text" class="form-control" id="cc-expiration" placeholder="" required>
                            <div class="invalid-feedback">
                                Expiration date required
                            </div>
                        </div>
                        <div class="col-md-3 mb-3">
                            <label for="cc-cvv">CVV</label>
                            <input type="text" class="form-control" id="cc-cvv" placeholder="" required>
                            <div class="invalid-feedback">
                                Security code required
                            </div>
                        </div>
                    </div>

                    <hr class="mb-4">

                    <button class="genric-btn primary-border circle w-100 mb-3" type="submit">Continue to checkout</button>
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
    <script type="text/javascript">
        $(() => {
            $('.clockpicker').clockpicker({
                placement: 'bottom',
                align: 'left',
                autoclose: true
            });
        })
    </script>
    <script type="text/javascript">
        const date = new Date();
        const orderDetails = JSON.parse('<%= session.getAttribute("orderDetails") %>');
        const totalPrice = orderDetails && orderDetails.reduce((acc, cur) => {
            return acc + (cur.productQuantity * cur.product.price);
        }, 0)

        new Vue({
            el: "#app",
            data: {
                order: {
                    memberId: 0,
                    totalPrice: totalPrice,
                    pickupTime: date.getHours() + ":" + date.getMinutes(),
                    orderDetails: orderDetails || [],
                    length: orderDetails ? orderDetails.length : 0
                }
            },
            methods: {
                submit: function () {
                    date.setHours(parseInt(this.order.pickupTime.split(':')[0]));
                    date.setMinutes(parseInt(this.order.pickupTime.split(':')[1]));

                    fetch("/order", {
                        method: "post",
                        headers: {
                            "Content-Type": "application/json"
                        },
                        body: JSON.stringify(this.order)
                    })
                    .then(response => response.json())
                    .then(response => console.log(response));
                }
            }
        })
    </script>
</body>
</html>
