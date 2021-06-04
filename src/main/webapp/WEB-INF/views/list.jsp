<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<jsp:include page="/WEB-INF/views/common/head.jsp" />
<style>
.page-link.active {
	color: white !important;
	background-color: #343a40 !important;
}

.page-link {
	color: #343a40 !important;
}
</style>
<body class="hold-transition sidebar-mini">
	<div class="wrapper">
		<!-- Sidebar -->
		<jsp:include page="/WEB-INF/views/common/sidebar.jsp" />

		<!-- Header -->
		<jsp:include page="/WEB-INF/views/product/common/header.jsp" />

		<!-- Content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">Product List</h1>
						</div>
						<!-- /.col -->
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a
									href="${pageContext.request.contextPath}/">Home</a></li>
								<li class="breadcrumb-item active">Product List</li>
							</ol>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<div class="content">

				<div class="container-fluid">
					<div class="card">

						<div id="loader"
							style="position: absolute; z-index: 123; display: flex; justify-content: center; align-items: center; width: 100%; height: 100%">
							<div class="spinner-grow text-secondary" role="status">
								<span class="sr-only">Loading...</span>
							</div>
						</div>

						<div class="card-body">
							<div class="d-flex">
								<div class="p-2 flex-fill">
									<form id="searchBy" method="post">
										<div class="input-group mb-3 input-group-sm"
											style="width: 500px">
											<select name="searchName" id="searchName"
												class="custom-select" style="width: 50px;">
												<option value="name">Name</option>
												<option value="id">Category ID</option>
											</select> <input type="text" name="searchValue" id="searchValue"
												class="form-control" style="width: 200px;">
											<div class="input-group-prepend">
												<span class="input-group-text"><i
													class="fas fa-search"></i></span>
											</div>
										</div>
									</form>
								</div>
								<div></div>
								<div align="right" class="p-2 flex-fill">
									<select id="sortBy" class="custom-select" style="width: 200px;">
										<option value="low">low price</option>
										<option value="high">high price</option>
									</select>
								</div>
							</div>

							<div>
								<table class="table table-striped">
									<thead align="center">
										<tr>
											<th width="5%"><input type="checkbox" id="checkAll"></th>
											<th width="20%">image</th>
											<th width="50%">product</th>
											<th width="10%">price</th>
											<th width="10%">details</th>
											<th width="5%"></th>
										</tr>
									</thead>
									<tbody align="center" id="p_info">
										<!-- append -->
									</tbody>
									<thead align="center">
										<tr>
											<td id="page" colspan="6"></td>
										</tr>
									</thead>
								</table>
							</div>

							<div align="center">
								<!-- deleteAll -->
								<button type="button" class="btn btn-dark deleteAll">delete
									All</button>
								<!-- deleteSelect -->
								<button type="button" class="btn btn-secondary deleteSelect">delete
									Select</button>
							</div>
						</div>
					</div>
					<!-- /.container-fluid -->
				</div>
			</div>
			<!-- /.content -->
		</div>

		<!-- Footer -->
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>
	<script type="text/javascript">
    var searchName
    var searchValue
    var currentPageNo
    var sortBy
    var product

    var getProductList = function(searchType, searchTerm, requestPageNo, sortBy) {
        $.ajax ({
            url: "/product",
            type: "get",
            data: {
                searchType : searchType,
                searchTerm : searchTerm,
                currentPageNo : requestPageNo,
                sortBy : sortBy,
                rows: 4
            },
            dataType: "json",
            beforeSend: function () {
                $("#loader").show();
            },
            complete: function () {
                $("#loader").hide();
            },
            success: function(data) {
                // 테이블 내용 삭제
                $("#p_info").empty()

                currentPageNo = data.currentPageNo

                if (data.totalItems === 0) {
                    $("#p_info").append("<tr><td colspan='6' align='center'>No results found.</tr>")
                    $("#page").empty()
                }
                // 행 추가
                $.each(data.items, function(i, elt) {
                    // product list
                    $("#p_info").append(
                        "<tr><td><input type='checkbox' class='p_check' data-product='" + JSON.stringify(elt) + "' value='" + elt.id + "'></td>" +
                        "<td><img src='" + (elt.imageUrl || "https://usagi-post.com/wp-content/uploads/2020/05/no-image-found-360x250-1-300x208.png") +  "' width='100px' height='100px'></td>" +
                        "<td align='left'>" + elt.name + "</td>" +
                        "<td>" + elt.price + "</td>" +
                        "<td><button type='button' class='btn btn-secondary detailsBtn' value='" + elt.id + "'>details</button></td>" +
                        "<td><button type='button' class='btn deleteBtn' data-product='" + JSON.stringify(elt) + "' value='" + elt.id + "'><i class='far fa-trash-alt'></i></button></td></tr>"
                    );
                }) //
                $("#page").empty()
                let table = ""
                table += `
                        <ul class="pagination">
                            <li class="page-item \${data.hasPrevPage ? '' : 'disabled'}">
                                <button class="page-link" id="prev">
                                    <span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span>
                                </button>
                            </li>
                    `;
                for (var pageNo = data.startPage; pageNo <= data.endPage; pageNo++) {
                    if (pageNo === data.currentPageNo) {
                        table += "<li class='page-item'><button class='page-link active pageNo' value='" + pageNo + "'>" + pageNo +  "</button></li>";
                    } else {
                        table += "<li class='page-item'><button class='page-link pageNo' value='" + pageNo + "'>" + pageNo +  "</button></li>";
                    }
                }
                table += `
                            <li class="page-item \${data.hasNextPage ? '' : 'disabled'}">
                                <button class="page-link" id="next">
                                    <span aria-hidden="true">&raquo;</span>
                                    <span class="sr-only">Next</span>
                                </button>
                            </li>
                        </ul>
                    `;
                $("#page").append(table)
                $(".pageNo").on("click", function(e) {
                    getProductList(searchName, searchValue, $(this).val(), sortBy)
                })
                $("#next").on("click", (e) => {
                    getProductList(searchName, searchValue, data.currentPageNo + 1, sortBy)
                })
                $("#prev").on("click", (e) => {
                    getProductList(searchName, searchValue, data.currentPageNo - 1, sortBy)
                })
                // 7. details button
                $(".detailsBtn").on("click", function() {
                    location.href = `/product/detail?productId=\${$(this).val()}`
                }) //
                // 8. delete button
                $(".deleteBtn").on("click", function() {
        	        swal({
            		  title: "Are you sure?",
            		  icon: "warning",
            		  buttons: true,
            		  dangerMode: true,
            		})
            			.then((willDelete) => {
            		      if (willDelete) {
						    swal("Delete completed.", {
						      icon: "success",
						    })
            		    fetch("/product", {
            		    	headers: {
            		    		"Content-Type": "application/json; charset=utf-8"
            		    	},
        	                method: "delete",
        	                body: JSON.stringify([$(this).data("product")])
        	            })
        	                .then(response => response.text())
        	                .then(status => {
        	                    getProductList(searchName, searchValue, currentPageNo, sortBy)
        	                })
						  } else {
						  }
            		}) // then
                }) //
            },
            error: function(error) {
                console.log("error", error)
            }
        }) //
    }
    
    $(function() {
        // 1. list
        getProductList(searchName, searchValue, 1, sortBy) // 초기 상품 목록
         // 2. search
        $("#searchBy").on("submit", function() {
            searchName = $("#searchName").val()
            searchValue = $("#searchValue").val()
            sortBy = $("#sortBy").val()
            $("#searchValue").val("")
            var getListBySearch = getProductList(searchName, searchValue, 1, sortBy)
            event.preventDefault()
        }) //
        $("#searchBy").on("input", function () {
            searchName = $("#searchName").val()
            searchValue = $("#searchValue").val()
            sortBy = $("#sortBy").val()
            var getListBySearch = getProductList(searchName, searchValue, 1, sortBy)
        })
        // 3. sort
        $("#sortBy").on("change", function() {
            sortBy = $(this).val()

            getProductList(searchName, searchValue, currentPageNo, sortBy)
        }) //
        // 4. check all
        $("#checkAll").on("click", function() {
            $(".p_check").prop('checked', this.checked);
        }) //
        // 5. delete all
        $(".deleteAll").on("click", function() {
            $(".p_check").prop('checked', true);
            var products = [];
            $(".p_check:checked").each(function() {
                products.push($(this).data("product"));
            })
            if (jQuery.isEmptyObject(products)) {
                swal("No product selected.");
                return;
            } else {
            	swal({
            		  title: "Are you sure?",
            		  icon: "warning",
            		  buttons: true,
            		  dangerMode: true,
            		})
            			.then((willDelete) => {
            		      if (willDelete) {
						    swal("Delete completed.", {
						      icon: "success",
						    })
            		    fetch("/product", {
            		    	headers: {
            		    		"Content-Type": "application/json; charset=utf-8"
            		    	},
        	                method: "delete",
        	                body: JSON.stringify(products)
        	            })
        	                .then(response => response.text())
        	                .then(status => {
        	                    getProductList(searchName, searchValue, currentPageNo, sortBy)
        	                })
						  } else {
						  }
            		}) // then
            } //
        }) //
        // 6. delete select
        $(".deleteSelect").on("click", function() {
            var products = []
            $(".p_check:checked").each(function() {
                products.push($(this).data("product"))
            })
            if (jQuery.isEmptyObject(products)) {
                swal("No product selected.");
                return;
            } else {
            	swal({
            		  title: "Are you sure?",
            		  icon: "warning",
            		  buttons: true,
            		  dangerMode: true,
            		})
            			.then((willDelete) => {
            		      if (willDelete) {
						    swal("Delete completed.", {
						      icon: "success",
						    })
            		    fetch("/product", {
            		    	headers: {
            		    		"Content-Type": "application/json; charset=utf-8"
            		    	},
        	                method: "delete",
        	                body: JSON.stringify(products)
        	            })
        	                .then(response => response.text())
        	                .then(status => {
        	                    getProductList(searchName, searchValue, currentPageNo, sortBy)
        	                })
						  } else {
						  }
            		}) // then
            } //
        }) //
    }) // end
</script>
</body>
</html>