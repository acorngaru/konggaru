//페이징과 관련한 버튼들의(Previous, Next, 페이지 번호
// ) 값들 바꾸는 함수
function editPageBtn(currentPageNo, startPage, endPage, pageCount, totalPages) {
    $(".pagination").empty();

    var paging = "";
    paging += '<input type="hidden" id="currentPageNo" value='+currentPageNo+'>';
    paging += '<input type="hidden" id="startPage" value='+startPage+'>';
    paging += '<input type="hidden" id="endPage" value='+endPage+'>';
    paging += '<input type="hidden" id="pageCount" value='+pageCount+'>';
    paging += '<input type="hidden" id="totalPages" value='+totalPages+'>';

    if(startPage == 1){ //첫 블럭에서는 PrevBtn 필요 없음
        paging += '<li class="page-item disabled"><a href="#">Previous</a></li>';
    }else paging += '<li class="page-item" id="preBtn"><a href="#">Previous</a></li>';

    for(var i=startPage; i<Number(endPage) + 1; i++){
        if(currentPageNo == i)
            paging += "<li class='page-item active'><a href='#' class='pageBtn'>"+i+"</a></li>";
        else paging += "<li class='page-item'><a href='#' class='pageBtn'>"+i+"</a></li>";
        if(i == endPage) break; //불필요한 버튼은 만들 필요가 없음
    }

    if(endPage==totalPages){ //마지막 블록에서는 Next 버튼 불필요
        paging += '<li class="page-item disabled"><a href="#">Next</a></li>';
    }else paging += '<li class="page-item" id="nextBtn"><a href="#">Next</a></li>';

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
        clockSearchByName(currentPageNo);
        
    });
}