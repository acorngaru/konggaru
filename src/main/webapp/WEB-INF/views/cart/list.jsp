<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Acorngaru</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- <link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Poppins:100,200,400,300,500,600,700">
<link rel="stylesheet" href="/css/owl.carousel.css">
<link rel="stylesheet" href="/css/nice-select.css">
<link rel="stylesheet" href="/css/magnific-popup.css">
<link rel="stylesheet" href="/css/main.css" -->
<!-- <link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/linearicons.css">
<link rel="stylesheet" href="css/bootstrap.css"> -->
<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="container">
		<h2>Cart List</h2>
		<p></p>
		<table class="table table-hover">
			<thead>
				<tr>
					<th><input type="checkbox" id="checkAll"></th>
					<th>No.</th>
					<th colspan="2">Product</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Amount</th>
					<th>Select</th>
				</tr>
			</thead>
			<tbody id="cartList">
				<!-- cartlist append -->
			</tbody>
		</table>
		
		<ul class="list-group">
		  <li class="list-group-item" id="totalAmount"></li>
		  <li class="list-group-item"><button type="button" class="btn btn-light checkOut">Checkout</button></li>
		</ul>
		
	</div>
</body>
<script type="text/javascript">

$(function() {
	
	function getCartList(memberId) {
		fetch("/cart/show", {
			headers: {
				"Content-Type" : "application/json; charset=utf-8"
			},
			method: "post",
			body: memberId
		})
		.then(response => response.json())
		.then(data => {
			console.log(data)
			$.each(data, function(i, elt) {
				$("#cartList").append(
								"<tr><td><input type='checkbox' class='p_check' value='" + elt.id + "'></td>" +
								"<td>" + elt.id + "</td>" +
								"<td><img src='" + elt.product.imageUrl + "' width='100px' height='100px'></td>" +
								"<td>" + elt.product.name + "</td>" +
								"<td>" + elt.product.price + "</td>" +
								"<td><input type='text' style='width: 50px;' id='cartQuantity" + elt.id + "' value='" + elt.productQuantity + "'>" +
								"<button type='button' class='btn upBtn' value='" + elt.id + "'><i class='fas fa-angle-up'></i></button>" +
								"<button type='button' class='btn downBtn' value='" + elt.id + "'><i class='fas fa-angle-down'></i></button>" +
								"<td class='sum' id='cartAmount" + elt.id + "'>" + elt.product.price * elt.productQuantity + "</td>" +
 								"<td><button type='button' class='btn btn-light modifyBtn' value='" + elt.id + "' data-price='" + elt.product.price + "'>modify</button><br>" +
								"<button type='button' class='btn btn-light deleteBtn' value='" + elt.id + "'>delete</button><br>" +
								"<button type='button' class='btn btn-light orderBtn' value='" + elt.id + "'>order</button></td></tr>"

				); //
			}) // end each
			
			// 총합
			function totalAmount() {
				var totalAmount = 0;
				$(".sum").each(function(idx, data) {
					totalAmount += Number.parseInt($(data).text());
				})
				$("#totalAmount").text("Total: " + totalAmount);
			}
			totalAmount();
			
			// 장바구니 수량 수정 (입력)
			$(".modifyBtn").on("click", function() {
				var cartId = $(this).val(); // 장바구니 번호
				var cartQuantity = $("#cartQuantity" + cartId).val(); // 장바구니 수량
				var cartPrice = $(this).attr("data-price");
				fetch("/cart/update", {
					headers: {
						"Content-Type" : "application/json; charset=utf-8"
					},
					method: "post",
					body: JSON.stringify({
						cartId : cartId,
						cartQuantity : cartQuantity
					})
				})
				.then(response => response.text())
				.then(data => {
					$("#cartAmount" + cartId).text(cartQuantity * cartPrice);
					totalAmount();
				})
				.catch(error => console.log("e >>>", error));
			}); //
			
 			// 장바구니 수량 수정 (버튼)
			$(".upBtn").on("click", function() {
				var cartId = parseInt($(this).val()); // 장바구니 번호
				var cartQuantity = parseInt($("#cartQuantity" + cartId).val()); // 장바구니 수량
				$("#cartQuantity" + cartId).val(cartQuantity + 1)
				totalAmount();
			});
			$(".downBtn").on("click", function() {
				var cartId = parseInt($(this).val()); // 장바구니 번호
				var cartQuantity = parseInt($("#cartQuantity" + cartId).val()); // 장바구니 수량
				if ($("#cartQuantity" + cartId).val() != 1) {
					$("#cartQuantity" + cartId).val(cartQuantity - 1)
					totalAmount();
				}
			});
				
			// 장바구니 삭제
			$(".deleteBtn").on("click", function() {
				var cartId = $(this).val(); // 장바구니 번호
				console.log(cartId)
 				fetch("/cart", {
					headers: {
						"Content-Type" : "application/json; charset=utf-8"
					},
					method: "delete",
					body: cartId
				})
				.then(response => response.text())
				.then(data => {
					$("#cartList").empty();
					getCartList(1); // getCartList(회원 아이디)
				})
				.catch(error => console.log("e >>>", error));
			}); //
		})
		.catch(error => console.log("e >>>", error));
	} //
	
	// 장바구니 조회
	getCartList(1); // getCartList(회원 아이디)
	
	// 전체 선택
    $("#checkAll").on("click", function() {
        $(".p_check").prop('checked', this.checked);
    }) //
    
    // 선택 주문
    $(".checkOut").on("click", function() {
        var products = [];
        $(".p_check:checked").each(function() {
            products.push($(this).val());
        })
        console.log(products)
    }) //
    
})

</script>
</html>