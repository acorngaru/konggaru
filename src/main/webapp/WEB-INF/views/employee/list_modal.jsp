<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<<script type="text/javascript">

$(function() {

if($("#emp_role").val()=="manager"){
$("#emp_role").val("manager").prop("selected",true);
}else{
$("#emp_role").val("part_time").prop("selected",true);
}

})

</script>
<!-- Add Modal HTML -->
<div id="addEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="/employee/addEmp" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">근로자 추가</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>이름</label>
                        <input type="text" name="name" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>전화번호</label>
                        <input type="text" name="phone" class="form-control" required></input>
                    </div>
                    <div class="form-group">
                        <label>직책</label>
                        <select name="role" class="form-control">
                            <option value="MANAGER">매니저</option>
                            <option value="PART_TIME">파트타임</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>급여</label>
                        <input type="text" name="salary" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>고용일</label>
                        <input type="text" name="hiredate" class="form-control" required>
                    </div>
                    <input type="hidden" name="resignation" value="today">
                    <input type="hidden" name="work_time" value="0">
                    <input type="hidden" name="id" value=0>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="취소">
                    <input type="submit" class="btn btn-success" value="추가">
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Emp detail Modal HTML -->
<div id="editEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="/employee/updateEmp" method="post">
                <div class="modal-header">
                    <h4 class="modal-title"></h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="closeBtn">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>사번</label>
                        <input type="text" name="id" class="form-control" id="detail_emp_id" readonly>
                    </div>
                    <div class="form-group">
                        <input type="hidden" name="name" class="form-control" id="detail_emp_name">
                    </div>
                    <div class="form-group">
                        <label>전화번호</label>
                        <input type="text" name="phone" class="form-control" id="detail_emp_phone" readonly>
                    </div>
                    <div class="form-group">
                        <label>직책</label>
                        <input type="text" name="role" class="form-control" id="detail_emp_role" readonly>
                    </div>
                    <div class="form-group">

                        <label>급여</label>
                        <input type="text" name="salary" class="form-control" id="detail_emp_sal" readonly>
                    </div>
                    <div class="form-group">
                        <label>고용일</label>
                        <input type="text" name="hiredate" class="form-control" id="detail_emp_hiredate" readonly>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="close">
                    <input type="button" class="btn btn-info" value="Edit" id="toEditBtn">
                    <script type="text/javascript">
                        $(document).on("click","#toEditBtn",toEditBtn);
                    </script>
                </div>
            </form>
        </div>
    </div>
</div>



<!-- Delete Modal HTML -->
<div id="deleteEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form>
                <div class="modal-header">
                    <h4 class="modal-title">Delete Employee</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p>Are you sure you want to delete these Records?</p>
                    <p class="text-warning"><small>This action cannot be undone.</small></p>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="취소">
                    <input type="submit" class="btn btn-danger" value="삭제">
                </div>
            </form>
        </div>
    </div>
</div>