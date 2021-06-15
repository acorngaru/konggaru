//seacrh employee by name
function searchByName(currentPageNo) {
    if(typeof  currentPageNo === "object"){
        currentPageNo = 1;
    }
    console.log("search by name currentPageNo = "+currentPageNo + ", selectName = " + $("#selectName").val())
    $.ajax({
        type : "post",
        url : "employee/list",
        dataType : "json",
        data : {
            selectName : $("#selectName").val(),
            currentPageNo : currentPageNo
        },
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",

        success : function(data, status, xhr) {
            $("#employeePrintTable>tbody").empty();
            var page = data
            console.log("get page success", page);
            editPageBtn(page.currentPageNo, page.startPage, page.endPage, page.pageCount, page.totalPages);

            //Employees의 정보를 테이블에 출력해주는 함수
            fillTableWithEmp(page.items)

            //선택한 emp의 정보를 모달에 입력해주는 함수 editModalEvent를 editBtn에 달아줌
            $(".editBtn").click(editModalEvent);
        },
        error : function(xhr, status, error) {
            console.log(error);
            console.log(status);
        }
    })
}
searchByName(1);
$("#selectName").keyup(searchByName)
//end seacrh employee by name