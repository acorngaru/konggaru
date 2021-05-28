<%--
  Created by IntelliJ IDEA.
  User: nbeom
  Date: 2021/05/22
  Time: 7:06 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.bundle.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"></script>
<jsp:include page="/WEB-INF/views/common/head.jsp" />
<body  class="hold-transition sidebar-mini">
<div class="wrapper">
    <!-- Sidebar -->
    <jsp:include page="/WEB-INF/views/common/sidebar.jsp" />

    <!-- Content -->
    <div class="wrapper">

        <!-- Content -->
        <div class="content-wrapper">
            <!-- Content Header (Page header) -->
            <div class="content-header">
                <div class="container-fluid">
                    <div class="row mb-2">
                        <div class="col-sm-6">
                            <h1 class="m-0">월별 매출</h1>
                        </div><!-- /.col -->
                    </div><!-- /.row -->
                </div><!-- /.container-fluid -->
            </div>
            <!-- /.content-header -->
            <!-- Main content -->
            <div class="content">
                <div class="container">
                    <div class="row">
                        <script>
                            $(document).ready(function (){
                                $.getJSON("dashboard/product_rank",function(data){
                                    var a =0
                                    $.each(data,function(key,value){
                                        //img 700 400
                                        a+=1
                                        html = ''
                                        html +='<a href="#"><img id ="imgs'+a+'" class="card-img-top" src="'+value+'"></a>'
                                        html +='<div class="card-body">'+
                                            '<h4 class="card-title">'+key+'</h4>'
                                        html +='<p class="card-text">매출'+a + '위</p>'
                                        html +='</div>'

                                        $("#card"+a).append(html)

                                        var size =document.getElementById("imgs"+a);
                                        size.style.width =360
                                        size.style.height =350
                                    })
                                    })

                            })
                        </script>
                        <div class="col-lg-4 col-sm-6">
                            <div class="card" id = "card1">
                            </div>
                        </div>

                        <div class="col-lg-4 col-sm-6">
                            <div class="card" id = "card2">

                            </div>
                        </div>


                        <div class="col-lg-4 col-sm-6">
                            <div class="card" id = "card3">

                            </div>
                        </div>
                    </div>
                </div>
                <div class="chart-container" style="position: relative; height:200px; width:60vw">
                    <canvas id="myChart"></canvas>
                </div>
                <script>
                    $(document).ready(function (){
                        $.ajax({
                            type :"get",
                            url : "dashboard/chart",
                            dataType :"json",
                            success : function (data,status,xhr){
                                var jsonData = data
                                var jsonData1 = {
                                    '2021-01' : data['2021-01'],
                                    '2021-02' : data['2021-02'],
                                    '2021-03' : data['2021-03'],
                                    '2021-04' : data['2021-04'],
                                    '2021-05' : data['2021-05'],
                                    '2021-06' : data['2021-06'],
                                    '2021-07' : data['2021-07'],
                                    '2021-08' : data['2021-08'],
                                    '2021-09' : data['2021-09'],
                                    '2021-10' : data['2021-10'],
                                    '2021-11' : data['2021-11'],
                                    '2021-12' : data['2021-12'],
                                }
                                console.log(jsonData1)
                                var jsonData2 = {
                                    '2021-01':1000000,
                                    '2021-02':3000000,
                                    '2021-03':4000000,
                                    '2021-04':5000000,
                                    '2021-05':5000000,
                                    '2021-06':5000000,
                                    '2021-07':5000000,
                                    '2021-08':5000000,
                                    '2021-09':5000000,
                                    '2021-10':5000000,
                                    '2021-11':5000000,
                                    '2021-12':5000000,
                                }
                                var ctx = document.getElementById('myChart');

                                var config = {
                                    type: 'line',
                                    data: {
                                        labels: Object.keys(jsonData2),
                                        datasets: [{
                                            label: '매출 총액',
                                            backgroundColor: 'rgba(75, 192, 192, 1)',
                                            borderColor: 'rgba(75, 192, 192, 1)',
                                            fill: false,
                                            data: Object.values(jsonData1),
                                        }, {
                                            label: '목표',
                                            backgroundColor: 'rgba(255, 99, 132, 1)',
                                            borderColor: 'rgba(255, 99, 132, 1)',
                                            fill: false,
                                            data: Object.values(jsonData2),
                                        }]
                                    },
                                    options: {
                                        maintainAspectRatio: false,
                                        title: {
                                            text: 'Chart.js Time Scale'
                                        },
                                        scales: {
                                            yAxes: [{
                                                scaleLabel: {
                                                    display: true,
                                                    labelString: '차트'
                                                }
                                            }]
                                        },
                                    }
                                };

                                //차트 그리기
                                var myChart = new Chart(ctx, config);

                            }
                        })
                    })

                    </script>
            </div>
        <!-- /.content -->
        <jsp:include page="/WEB-INF/views/ingredient/modal/modalSet.jsp"></jsp:include>

        <!-- Footer -->
        <jsp:include page="/WEB-INF/views/common/footer.jsp" />
    </div>
    </body>

</html>

