<script type="text/javascript">

    window.onload =function() {
        searchByName(1);
    }

    //버튼 값들 바꾸는 함수
    function editPageBtn(currentPageNo, startPage, endPage, pageCount, totalPages) {
        $(".pagination").empty();

        var paging = "";
        paging += '<input type="hidden" id="currentPageNo" value='+currentPageNo+'>';
        paging += '<input type="hidden" id="startPage" value='+startPage+'>';
        paging += '<input type="hidden" id="endPage" value='+endPage+'>';
        paging += '<input type="hidden" id="pageCount" value='+pageCount+'>';
        paging += '<input type="hidden" id="totalPages" value='+totalPages+'>';
        if(startPage == 1){
            paging += '<li class="page-item disabled"><a href="#" class="page-link">Previous</a></li>';
            <!-- a 태그 말고 버튼 태그로 바꿩 힝-->
        }else paging += '<li class="page-item" id="preBtn"><a href="#" class="page-link">Previous</a></li>';


        for (var i= startPage; i<Number(endPage) + 1; i++){
            if(currentPageNo == i)
                paging += "<li class='page-item active' ><button href='' id='pageBtn' class='page-link pageBtn'  >"+i+"</button></li>";
            else paging += "<li class='page-item'><button href='' id='pageBtn' class='page-link pageBtn' >"+i+"</button></li>";
            if(i == endPage) break; //불필요한 버튼은 만들 필요가 없음
        }

        //마지막 블록에서는 Next 버튼 불필요
        if(endPage==totalPages){ //마지막 블록에서는 Next 버튼 불필요
            paging += '<li class="page-item disabled"><a href="#" class="page-link" >Next</a></li>';
        }else paging += '<li class="page-item" id="nextBtn"><a href="#" class="page-link">Next</a></li>';


        $(".pagination").append(paging)

        $("#preBtn").click(function() {
            var currentPageNo = ($("#currentPageNo").val());
            var pageCount = ($("#pageCount").val());
            var startPage = Number($("#startPage").val()) - Number(pageCount);
            var endPage = Number(startPage) + Number(pageCount) - 1;
            editPageBtn(currentPageNo, startPage, endPage, pageCount, totalPages)
        });
        $("#nextBtn").click(function() {
            var currentPageNo = $("#currentPageNo").val();
            var startPage = Number($("#startPage").val()) + Number(pageCount);
            var endPage = Number($("#endPage").val()) + Number(pageCount);
            var totalPages = Number($("#totalPages").val());
            if(endPage>totalPages) endPage=totalPages;
            editPageBtn(currentPageNo, startPage, endPage, pageCount, totalPages);
        });
        $(".pageBtn").click(function () {
            var pageBtn = $(this);
            var currentPageNo = pageBtn.text();
            console.log(currentPageNo)
            searchByName(currentPageNo);
        });

    }

    //seacrh employee by name
    function searchByName(pageNo) {
        if(typeof  pageNo === "object"){
            pageNo = 1;
        }
        console.log("search by name pageNo = "+pageNo)
        $.ajax({
            type : "post",
            url : "ingredient/list",
            dataType : "json",
            data : {
                selectName : $("#selectName").val(),
                pageNo : pageNo
            },
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",


            success : function(data, status, xhr) {
                $("#ingredientTable>tbody").empty();
                var page = data
                console.log("get page success", page);
                editPageBtn(page.currentPageNo, page.startPage, page.endPage, page.pageCount, page.totalPages);

                for ( var i in page.items) {
                    var dto = page.items[i];

                    var html = "";

                    html += '<tr>';
                    html += '<td>'+
                        '<span class="custom-checkbox">'+
                        '<input type="checkbox" id="checkbox'+''+'" name="options[]" value="'+dto.id+'">'+
                        '<label for="checkbox"></label>'+
                        '</span></td>'
                    html += '<td name="ids" value='+dto.id +'>' + dto.id + '</td>';
                    html += '<td>' + dto.name + '</td>';
                    html += '<td>' + dto.quantity + '</td>';
                    html += '<td>' + dto.partner + '</td>';
                    html += '<td>' + dto.unit + '</td>';
                    html += '<td>' + dto.price + '</td>';
                    html += '<td>' + dto.price * dto.quantity + '</td>';
                    html += '<td>'+
                        "<a href='#updateModal' class='update_m' data-toggle='modal' id ='update_m'>"+
                        '<i class="material-icons" data-toggle="tooltip" title="update">'+'&#xE254;'+'</i>'+'</a>'+
                        '<a href="#deleteModal" class="delete" data-toggle="modal">'+
                        '<i class="material-icons" data-toggle="tooltip" title="delete">'+'&#xE872;'+'</i>'+'</a>'+
                        '</td>';
                    html += '</tr>';

                    $("#ingredientTable>tbody").append(html);
                }
                var checkedList =new Array()
                // Select/Deselect checkboxes
                var checkbox = $('table tbody input[type="checkbox"]');
                $("#selectAll").click(function(){
                    checkedList=[]
                    if(this.checked){
                        $("input[type=checkbox]").prop("checked",true);
                        checkbox.each(function(){
                            this.checked = true;
                        });
                        var options = document.getElementsByName('ids')
                        for (i=0 ; i<options.length; i++){
                            checkedList.push(options[i].innerText)
                        }
                        console.log(checkedList)
                    }

                    else{
                        $("input[type=checkbox]").prop("checked",false);
                    }
                });
                checkbox.click(function(){
                    if(!this.checked){
                        $("#selectAll").prop("checked", false);
                    }
                    var check = $(this);
                    console.log("ch",check)
                    var tr = check.parent().parent().parent()
                    var td  = tr.children()
                    console.log("td",td)
                    var tdArr = new Array()

                    for (i = 1 ; i<td.length-1; i++)
                        tdArr.push(td[i].innerHTML)
                    checkedList.push(tdArr[0])
                    console.log(checkedList)
                    console.log(tdArr)

                });
                $(".deleteAll").click(function (){
                    console.log(checkedList)
                    $("#delete_list").val(checkedList)
                })
                $(".delete").click(function (){
                    var checkBtn = $(this);
                    var tr = $(this).parent().parent();
                    var td = tr.children();
                    $('#delete_id').val(td[1].innerHTML)
                })
                $(".update_m").click(function (){
                    var checkBtn = $(this);
                    console.log(checkBtn)
                    console.log("=========")
                    var tr = $(this).parent().parent();
                    var td = tr.children();
                    var tdArr= new Array();
                    for (i = 1 ; i<td.length-1; i++)
                        tdArr.push(td[i].innerHTML)
                    $('#update_id').val(td[1].innerHTML)
                    $('#update_name').val(td[2].innerHTML)
                    $('#update_quantity').val(td[3].innerHTML)
                    $('#update_partner').val(td[4].innerHTML)
                    $('#update_price').val(td[6].innerHTML)
                    $('#update_unit').val(td[5].innerHTML)
                })

                $("#update_submit").click(function (){
                    var json = {
                        'id' : $('#update_id').val() *1,
                        'name' : $('#update_name').val(),
                        'quantity' : $('#update_quantity').val()*1,
                        'partner' : $('#update_partner').val(),
                        'price' :  $('#update_price').val()*1,
                        'unit' : $('#update_unit').val()
                    }
                    console.log(json)
                    $.ajax({
                        type : 'post',
                        url : '/ingredient/updateone',
                        contentType: 'application/json',
                        data: JSON.stringify(json),
                        success(data){
                            console.log(data)
                        }

                    })
                    location.reload()
                })

                $("#insert_submit").click(function (){
                    var json = {
                        'id' : 0,
                        'name' : $('#insert_name').val(),
                        'quantity' : $('#insert_quantity').val()*1,
                        'partner' : $('#insert_partner').val(),
                        'price' :  $('#insert_price').val()*1,
                        'unit' : $('#insert_unit').val()
                    }
                    console.log(json)
                    $.ajax({
                        type : 'post',
                        url : '/ingredient/insertone',
                        contentType: 'application/json',
                        data: JSON.stringify(json),
                        success(data){
                            console.log(data)
                        }

                    })
                })


    },
            error : function(xhr, status, error) {
                console.log(error);
                console.log(status);
            }

        })
    }
    //end seacrh employee by name


    $(document).ready(function(){
        $("#selectName").keyup(searchByName)


    });
    //CheckBox Select Fn
</script>