// 선택한 emp의 정보를 모달에 출력하는 함수

function editModalEvent() {
    var checkBtn = $(this);
    // checkBtn.parent() : checkBtn의 부모는 <td>이다.
    // checkBtn.parent().parent() : <td>의 부모이므로 <tr>이다.
    var tr = checkBtn.parent().parent();
    var td = tr.children();
    var tdArr = new Array();
    td.each(function(i){
        tdArr.push(td.eq(i).text());
    });
    $('#detail_emp_id').val(tdArr[0]);
    $('#detail_emp_name').val(tdArr[1]);
    $('.modal-title').text(tdArr[1]);
    $('#detail_emp_phone').val(tdArr[2]);
    $('#detail_emp_role').val(tdArr[3]);
    $('#detail_emp_sal').val(tdArr[4]);
    $('#detail_emp_hiredate').val(tdArr[5]);
    $('#passwd').val(tdArr[7]);
    $('#auth').val(tdArr[8]);



    }