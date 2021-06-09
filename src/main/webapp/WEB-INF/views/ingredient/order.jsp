<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
<jsp:include page="/WEB-INF/views/common/head.jsp" />
<body class="hold-transition sidebar-mini">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">



    $(document).ready(function(){

        var created_at = null;
        $.ajax({
            type: "post",
            url: "/ingredient/selectAll",
            success : function(data, status, xhr) {
                for ( var i in data.items) {
                    var dto = data.items[i];
                    console.log(dto.name)

                    var html = "";
                    html += '<option value="'+dto.name+'" id="'+dto.id+'">'+dto.name+'</option>';
                    $("#select_ingre").append(html);
                }
            },//success
            error:function(data, status, xhr){
                console.log("selectAll>>>>>에러!!")
            }

        }) //select all  -> 데이터 가져오는 용도(ingredient)


        $.ajax({
            type: "post",
            url: "/ingredient/selectAllOrder",
            success : function(data, status, xhr) {
                for ( var i in data.items) {
                    var dto = data.items[i];
                    console.log("selectAllOrder==="+dto)
                    created_at = dto.created_at;

                    var html = "";
                    html += "<tr>";
                    html += "<td width='500'>"+dto.ingredient_name+"</td>";
                    html += "<td width='400'>"+dto.ingredient_quantity+"</td>";
                    html += "<td width='400'><span classc'result"+i+"'>"+dto.created_at+"</span></td>";
                    html += '<td width="400"><button type="button" class="confirm_btn" id="'+dto.ingredient_id+'">승인</button></td>';
                    html += '</tr>';


                    $("#dynamicTable").append(html);
                }
            },//success
            error:function(data, status, xhr){
                console.log("selectAllOrder>>>>>에러!!")
            }
        }) //select all  -> 데이터 가져오는 용도(ingredient_order)





////////////////////////////////////////////////////////////////////////////////////////////

        const Now_Date = new Date();
        var year = Now_Date.getFullYear();
        var month = Now_Date.getMonth()+1;
        var day = Now_Date.getDate();
        var mesg = year+"년"+month+"월"+day+"일";
        console.log(mesg);

        var i =0;


//////////////////////////////////////////////////////////


        //DB Insert
        //누적테이블UI

        $(".order").on("click",function(){


            var html = '';

            var select = $("#select_ingre").val();
            var quantity = $("#quantity").val();
            var ingreId = $("#select_ingre option:selected").attr("id")
            created_at="N";

            html += '<tr>';
            html += '<td width="500">'+select+'</td>';
            html += '<td width="400">'+quantity+'</td>';
            html += "<td width='400'><span>"+created_at+"</span></td>";
            html += '<td width="400"><button type="button" class="confirm_btn">승인</button></td>';
            html += '</tr>';


            $("#dynamicTable").append(html);

            var json = {
                "ingredient_id" : ingreId,
                "ingredient_name": $("#select_ingre").val(),
                "ingredient_quantity" : $("#quantity").val(),
                "created_at" : "N",
                "addmission_at" : "N"
            }
            console.log("order.jsp =>"+json)

            $.ajax({
                type: 'post',
                url: 'ingredient/orderAdd',
                contentType: 'application/json',
                data: JSON.stringify(json),
                success(data) {
                    //console.log(data)
                }
            });
            location.reload()
        })//order

////////////////////////////////////////////////////////////////////////////////////////////

        //DB 승인요청
        $(document).on("click",".confirm_btn",function(){




            console.log("id값"+$(this).attr("id"))
            console.log("confirm_btn 실행됨!")

            $.ajax({
                type: 'post',
                url: '/ingredient/orderCreatedAt',
                data: {
                    'ingredient_id': $(this).attr("id"),
                    'created_at': mesg
                    },
                success(data) {
                    //console.log("confirm===="+data)
                    location.reload()
                }
            });

        })//confirm_btn




////////////////////////////////////////////////////////////////////////////////////////////
        //DB 승인완료
        $(".last_order").on("click",function(){

            var data = {
                'addmission_at' : mesg
            }

            Swal.fire({
                title: '발주를 계속 진행하겠습니까?',
                text: "You won't be able to revert this!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonText: '취소',
                confirmButtonText: '확인'
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire({
                        title: '발주완료!',
                        text: 'Your file has been completed.',
                        icon: 'success'
                    }).then((result) => {
                        $.ajax({
                            type: "post",
                            url: "/ingredient/addMissionAt",
                            data: {
                                'addmission_at' : mesg
                            },
                            success(data) {
                                console.log("모달성공>>>>>")
                                location.reload()
                            },
                            error:function (){
                                console.log("모달 에러!!!")
                            }
                        }) //ajax

                    })
                }//if
            })//end swal
        });//orderquantity

////////////////////////////////////////////////////////////////////////////////////////////






    });//end fn



</script>
<div class="wrapper">
    <!-- Sidebar -->
    <jsp:include page="/WEB-INF/views/common/sidebar.jsp" />

    <!-- Header -->
    <jsp:include page="/WEB-INF/views/ingredient/common/header.jsp" />

    <!-- Content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1 class="m-0">재고관리 Page</h1>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.container-fluid -->
        </div>
        <!-- /.content-header -->

        <!-- Main content -->
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div>
                        <strong>발주</strong> 부족한 재고를 발주 합니다.
                    </div>
                    <form>
                    <table class="table table-striped">
                        <thead>
                        <tr bgcolor="#BDBDBD" font color="white">
                            <th>품명</th>
                            <th>발주 요청 수량</th>
                            <td></td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <select id="select_ingre" class="form-select" aria-label="Default select example">
                                    <option selected>추가하고 싶은 재고를 선택하세요</option>



                                </select>
                            </td>
                            <td>
                                <div class="input-group mb-3">
                                    <span class="input-group-text" id="basic-addon1"></span>
                                    <input type="text" id="quantity" name="quantity" class="form-control" placeholder="발주 요청 수량" aria-label="order" aria-describedby="basic-addon1">
                                </div>

                            </td>
                            <td>
                                <div style="width:100px; height:50px; float:right">
                                    <a href="#" class="order"><button type="button" it="addBtn" class="btn btn-primary float-right">추가</button></a>
                                </div>
                            </td>
                        </tr>

                        <!--발주 테이블-->
                        <tr>

                            <table class="table table-striped" id="dynamicTable">
                                <thead>
                                <tr>
                                    <div>

                                    </div>
                                    <div>
                                        <strong>발주리스트</strong>
                                    </div>
                                </tr>
                                <tr bgcolor="#BDBDBD" font color="white">
                                    <th width="500">품명</th>
                                    <th width="400">수량</th>
                                    <th width="400">승인날짜</th>
                                    <th width="400">승인여부</th>
                                </tr>
                                </thead>
                                <tbody id="dynamicTbody">

                                </tbody>
                                </table>

                        </tr>
                        <!--발주 테이블-->

                        </tbody>
                    </table>
                    </form>
                </div>             <!-- /.row -->

                <div style="width:100px; height:50px; float:right">
                    <a href="#" class="last_order"><button type="button" class="btn btn-primary float-right">발주</button></a>
                </div>

                <!--발주 테이블-->


                <!--발주 테이블-->

            </div><!-- /.container-fluid -->
        </div>
        <!-- /.content -->
    </div>

    <!-- Footer -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</div>
</body>
</html>
