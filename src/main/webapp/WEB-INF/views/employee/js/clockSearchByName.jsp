//seacrh employee by name
function clockSearchByName(currentPageNo) {
    if(typeof  currentPageNo === "object"){
        currentPageNo = 1;
    }
    console.log("search by name currentPageNo = "+currentPageNo + ", selectName = " + $("#ClockselectName").val())
     $.ajax({
        type : "post",
        url : "rollbook/ClockInOut",
        dataType : "json",
        data : {
        	ClockselectName : $("#ClockselectName").val(),
        	ClockSearchType : $("#ClockSearchType").val(),
            currentPageNo : currentPageNo
        },
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",

        success : function(data, status, xhr) {
            $("#rollBookPrintTable>tbody").empty();
            var page = data
            console.log("get page success", page);
            editPageBtn(page.currentPageNo, page.startPage, page.endPage, page.pageCount, page.totalPages);
			
			console.log("page.items????===",page.items);
			
            //Employees의 정보를 테이블에 출력해주는 함수
            fillTableWithRollBook(page.items)

        },
        error : function(xhr, status, error) {
            console.log(error);
            console.log(status);
        }
    }) 
}
clockSearchByName(1);
$("#ClockselectName").keyup(clockSearchByName)
//end seacrh employee by name