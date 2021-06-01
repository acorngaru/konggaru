function toDetailBtn() {
    $(".modal-footer").empty();
    $("#detail_emp_id").attr("readonly", true);
    $("#detail_emp_phone").attr("readonly", true);
    $("#detail_emp_role").attr("readonly", true);
    $("#detail_emp_sal").attr("readonly", true);
    $("#detail_emp_hiredate").attr("readonly", true);

    detailBtn = ''
    detailBtn += '<input type="button" class="btn btn-default" data-dismiss="modal" value="close">'
    detailBtn += '<input type="button" class="btn btn-info edit" value="Edit" id="toEditBtn">'
    $(".modal-footer").append(detailBtn);
    $(document).on("click",".btn btn-info edit",toEditBtn);

}