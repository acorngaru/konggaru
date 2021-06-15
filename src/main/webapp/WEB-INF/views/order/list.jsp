<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>Acorngaru</title>
</head>
<body>
	<div class="container">
		<h2>Order List</h2>
		<p></p>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>No.</th>
					<th>Order Date</th>
					<th colspan="2">Product</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Amount</th>
				</tr>
			</thead>
			<tbody id="orderList">
				<!-- orderlist append -->
			</tbody>
		</table>
	</div>
</body>
<script type="text/javascript">

$(function() {
	
	function getOrderList(memberId) {
		fetch("/order/show", {
			headers: {
				"Content-Type" : "application/json; charset=utf-8"
			},
			method: "post",
			body: memberId
		})
		.then(response => response.json())
		.then(data => {
			$.each(data, function(i, elt) {
				var orderId = elt.id;
				var createdAt = elt.createdAt;
				$.each(elt.orderDetails, function(i, elt) {
					$("#orderList").append(
									"<tr><td>" + orderId + "</td>" +
									"<td>" + createdAt + "</td>" +
									"<td><img src='" + elt.product.imageUrl + "' width='100px' height='100px'></td>" +
									"<td>" + elt.product.name + "</td>" +
									"<td>" + elt.product.price + "</td>" +
									"<td>" + elt.productQuantity + "</td>" +
									"<td>" + elt.product.price * elt.productQuantity + "</td></tr>"
					); //
				})
			}) // end each
		})
		.catch(error => console.log("e >>>", error));
	}
	
	// 주문 내역 조회
	getOrderList(1);
})
</script>
</html>