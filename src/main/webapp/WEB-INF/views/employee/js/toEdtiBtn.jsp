function toEditBtn() {
    $("#detail_emp_phone").attr("readonly", false);
    $("#detail_emp_passwd").attr("readonly", false);
    $("#detail_emp_role").attr("readonly", false);
    $("#detail_emp_sal").attr("readonly", false);
    $("#detail_emp_hiredate").attr("readonly", false);

    $(".modal-footer").empty();
    editBtn = ''
    editBtn += '<input type="button" class="btn btn-default cancel" value="cancel" id="cancelBtn">'
    editBtn += '<input type="submit" class="btn btn-info confirm" value="confirm" align="ce">'
    $(".modal-footer").append(editBtn);
    $(".btn btn-info confirm").click(toDetailBtn);
    $(".close").click(toDetailBtn);
    $(".btn btn-default cancel").click(toDetailBtn);

    $(document).on("click","#cancelBtn",toDetailBtn);

}