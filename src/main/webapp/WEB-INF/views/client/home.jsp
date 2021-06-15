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
    <style>
        .quantity:focus {
            outline: 0 none;
        }
    </style>
</head>
<body>
    <div id="app">
        <header id="header">
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
                            <li class="menu-active"><a href="${pageContext.request.contextPath}/">Home</a></li>
                            <li><a href="${pageContext.request.contextPath}/member/mypage">My Page</a></li>
                            <li><a href="${pageContext.request.contextPath}/cart/list">My Cart</a></li>
                        </ul>
                    </nav><!-- #nav-menu-container -->
                </div>
            </div>
        </header><!-- #header -->


        <!-- start banner Area -->
        <section class="banner-area" id="home" style="background: url(/image/header-bg.jpg) center; background-size: cover">
            <div class="container">
                <div class="row fullscreen d-flex align-items-center justify-content-start">
                    <div class="banner-content col-lg-7">
                        <h6 class="text-white text-uppercase">Now you can feel the Energy</h6>
                        <h1>
                            Start your day with <br>
                            an Americano
                        </h1>
                        <button class="primary-btn text-uppercase" @click="showBannerCoffee">Buy Now</button>
                    </div>
                </div>
            </div>
        </section>
        <!-- End banner Area -->

        <!-- Start video-sec Area -->
        <section class="video-sec-area pb-100 pt-40" id="about">
            <div class="container">
                <div class="row justify-content-start align-items-center">
                    <div class="col-lg-6 video-right justify-content-center align-items-center d-flex" style="background: url(/image/location.png) no-repeat center; background-size: cover;">
                    </div>
                    <div class="col-lg-6 video-left">
                        <h6>Live Coffee making process.</h6>
                        <h1>We Telecast our <br>
                            Coffee Making Live</h1>
                        <p><span>We are here to listen from you deliver exellence</span></p>
                        <p>
                            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod temp or incididunt ut labore et dolore magna aliqua. Ut enim ad minim.
                        </p>
                        <img class="img-fluid" src="${pageContext.request.contextPath}/image/signature.png" alt="">
                    </div>
                </div>
            </div>
        </section>
        <!-- End video-sec Area -->

        <!-- Start menu Area -->
        <section class="menu-area section-gap" id="coffee" style="background: url(/image/menu-bg.jpg) center; background-size: cover;">
            <div class="container">
                <div class="row d-flex justify-content-center">
                    <div class="menu-content pb-10 col-lg-10">
                        <div class="title text-center">
                            <h1 class="mb-10">KonggMenu Cafe Menu</h1>
                            <p>Store fresh-roasted beans</p>
                        </div>
                    </div>
                </div>

                <div class="row pb-50 d-flex justify-content-center">
                    <div class="btn-group" data-togle="buttons">
                        <button class="genric-btn primary" @click="fetchProductsByCategoryId(1)">BEVERAGE</button>
                        <button class="genric-btn primary" @click="fetchProductsByCategoryId(2)">FOOD</button>
                        <button class="genric-btn primary" @click="fetchProductsByCategoryId(3)">GOODS</button>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-4" v-for="product in products">
                        <div class="single-menu">
                            <div class="title-div justify-content-between d-flex">
                                <h4>{{ product.name }}</h4>
                                <p class="price float-right">
                                    $ {{ product.price }}
                                </p>
                            </div>
                            <p>
                                {{ product.description }}
                            </p>
                            <div class="mt-4 d-flex justify-content-end">
                                <button class="genric-btn primary small circle" @click="showProductDetails(product)">Details</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="modal" role="dialog">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="row">
                            <div class="col-12">
                                <img class="w-100" :src="productDetails.product.imageUrl" alt="" style="height: 330px; object-fit: fill"/>
                            </div>
                        </div>

                        <div class="modal-body">
                            <div class="row px-3">
                                <div class="typography">
                                    <h2 class="mb-2">{{ productDetails.product.name }}</h2>

                                    <h4 class="mb-2"><span class="badge badge-warning">{{ productDetails.product.categoryName }}</span></h4>

                                    <h5 class="mb-2"><span class="mr-1">&#8361;</span>{{ productDetails.product.price }}</h5>

                                    <p>{{ productDetails.product.description }}</p>
                                </div>
                            </div>
                        </div>

                        <div class="modal-footer d-flex justify-content-between">
                            <div class="btn-group">
                                <button class="genric-btn primary-border small px-2" @click="plus">+</button>
                                <input type="text" class="quantity input-number border border-white text-center small px-2" style="width: 55px"
                                       v-model="productDetails.productQuantity"
                                       @input="checkQuantityRange" />
                                <button class="genric-btn primary-border small px-2" @click="minus">-</button>
                            </div>

                            <div class="btn-group">
                                <button class="genric-btn primary-border small" @click="clickAddToCart">ADD TO CART</button>
                                <button class="genric-btn primary small" @click="clickBuyNow">BUY NOW</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End menu Area -->

        <!-- Start gallery Area -->
        <section class="gallery-area section-gap" id="gallery">
            <div class="container">
                <div class="row d-flex justify-content-center">
                    <div class="menu-content pb-60 col-lg-10">
                        <div class="title text-center">
                            <h1 class="mb-10">What kind of Coffee we serve for you</h1>
                            <p>Who are in extremely love with eco friendly system.</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-4">
                        <a href="${pageContext.request.contextPath}/image/g1.jpg" class="img-pop-home">
                            <img class="img-fluid" src="${pageContext.request.contextPath}/image/g1.jpg" alt="">
                        </a>
                        <a href="${pageContext.request.contextPath}/image/g2.jpg" class="img-pop-home">
                            <img class="img-fluid" src="${pageContext.request.contextPath}/image/g2.jpg" alt="">
                        </a>
                    </div>
                    <div class="col-lg-8">
                        <a href="${pageContext.request.contextPath}/image/g3.jpg" class="img-pop-home">
                            <img class="img-fluid" src="${pageContext.request.contextPath}/image/g3.jpg" alt="">
                        </a>
                        <div class="row">
                            <div class="col-lg-6">
                                <a href="${pageContext.request.contextPath}/image/g4.jpg" class="img-pop-home">
                                    <img class="img-fluid" src="${pageContext.request.contextPath}/image/g4.jpg" alt="">
                                </a>
                            </div>
                            <div class="col-lg-6">
                                <a href="${pageContext.request.contextPath}/image/g5.jpg" class="img-pop-home">
                                    <img class="img-fluid" src="${pageContext.request.contextPath}/image/g5.jpg" alt="">
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    <!-- End gallery Area -->
    </div>

    <!-- start footer Area -->
    <footer class="footer-area section-gap" style="background: url('/image/footer-bg.jpg'); background-size: cover">
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
    <script type="text/javascript">
        new Vue({
            el: "#app",
            data: {
                products: [],
                productDetails: {
                    productQuantity: 1,
                    product: {}
                }
            },
            methods: {
                fetchProductsByCategoryId: function (categoryId) {
                    fetch("/product/all?categoryId=" + categoryId)
                    .then(response => response.json())
                    .then(response => {
                        this.products = response.data;
                    });
                },
                showProductDetails: function (product) {
                    this.productDetails = {
                        productQuantity: 1,
                        productId: product.id,
                        product: {
                            ...product
                        }
                    }
                    $("#modal").modal("show");
                },
                plus: function () {
                    this.productDetails.productQuantity < 99 && this.productDetails.productQuantity++;
                },
                minus: function () {
                    this.productDetails.productQuantity > 1 && this.productDetails.productQuantity--;
                },
                checkQuantityRange: function () {
                    if (this.productDetails.productQuantity > 99)
                        this.productDetails.productQuantity = 99;
                    if (this.productDetails.productQuantity < 1)
                        this.productDetails.productQuantity = 1;
                },
                showBannerCoffee: function () {
                    fetch("/product?searchTerm=아메리카노 S")
                    .then(response => response.json())
                    .then(response => {
                        this.showProductDetails(response.data.items[0]);
                    });
                },
                clickAddToCart: function () {
                    swal("Success", "The product has been successfully added.", "success");
                },
                clickBuyNow: function () {
                    fetch("/checkout", {
                        headers: {
                            "Content-Type": "application/json"
                        },
                        method: "post",
                        body: JSON.stringify([this.productDetails])
                    })
                    .then(response => response.json())
                    .then(response => {
                        console.log(response)
                        if (response.status === "OK")
                            location.href = "/checkout";
                    })
                    .catch(() => location.href = "/login");
                }
            },
            created: function () {
                this.fetchProductsByCategoryId(1);
            }
        })
    </script>
</body>
</html>
