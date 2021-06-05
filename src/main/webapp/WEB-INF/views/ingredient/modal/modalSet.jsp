<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!-- Modal utility page -->
<!--
            재고 추가 Modal
-->
<div class="modal fade" id="createModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="createModalTitle">재고 추가</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="${pageContext.request.contextPath}/ingredient/insertone" method="post">
                <div class="modal-body">
                    <label for="insert_name" class="col-form-label">제품명</label>
                    <input type="text" class="form-control" id="insert_name" name="insert_name">
                    <label for="insert_quantity" class="col-form-label">재고</label>
                    <input type="text" class="form-control" id="insert_quantity" name="insert_quantity">
                    <label for="insert_partner" class="col-form-label">판매처</label>
                    <input type="text" class="form-control" id="insert_partner" name="insert_partner">
                    <label for="insert_price" class="col-form-label">개당가격</label>
                    <input type="text" class="form-control" id="insert_price" name="insert_price">
                    <label for="insert_unit" class="col-form-label">단위</label>
                    <input type="text" class="form-control" id="insert_unit" name="insert_unit">
                </div>
                <div class="modal-footer">
                    <button type="reset" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" id ="insert_submit" data-dismiss="modal" class="btn btn-primary">Update</button>

                </div>
            </form>
        </div>
    </div>
</div><!--
            재고 삭제 Modal
-->

<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/ingredient/deleteone" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteModalTitle">재고 삭제</h5>
                    <br>
                    삭제할 데이터의 id 입력 <br>
                    <div class="form-group">
                        <input type="text" name="name" class="form-control" id="delete_id" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <input type="submit" class="btn btn-danger" value="삭제"/>
                </div>
            </form>
        </div>
    </div>
</div>
<!--
            전체삭제 modal
-->
<div class="modal fade" id="deleteAllModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/ingredient/deleteall" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteAllModalTitle">재고 삭제</h5>
                    <br>
                    삭제할 데이터의 id 입력 <br>
                    <div class="form-group">
                        <input type="text" name="name" class="form-control" id="delete_list" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <input type="submit" class="btn btn-danger" value="삭제"/>
                </div>
            </form>
        </div>
    </div>
</div>
<!--
            재고 수정 Modal
-->

<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/ingredient/updateone" method="post">
            <div class="modal-header">
                <h5 class="modal-title" id="updateModalTitle">재고 수정</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <label class="col-form-label" for="update_id"></label>
                <input name = "update_id" type="text" class="form-control" id="update_id" readonly>
                <label for="update_name" class="col-form-label">제품명</label>
                <input type="text" class="form-control" name ="update_name" id="update_name">
                <label for="update_quantity" class="col-form-label">재고</label>
                <input type="text" class="form-control" name ="update_quantity" id="update_quantity">
                <label for="update_partner" class="col-form-label">판매처</label>
                <input type="text" class="form-control" name ="update_partner" id="update_partner">
                <label for="update_price" class="col-form-label">개당가격</label>
                <input type="text" class="form-control" name ="update_price" id="update_price">
                <label for="update_unit" class="col-form-label">단위</label>
                <input type="text" class="form-control" name ="update_unit" id="update_unit">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" id ="update_submit" data-dismiss="modal" class="btn btn-primary">Update</button>
            </div>
            </form>
        </div>
    </div>
</div>
