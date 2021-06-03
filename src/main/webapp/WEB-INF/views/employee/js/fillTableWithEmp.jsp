//Employees의 정보를 테이블에 출력해주는 함수
function fillTableWithEmp(data) {
    for (var i in data) {
        var dto = data[i];
        var html = "";

        html += '<tr>';
        html += '<td style="display: none">' + dto.id + '</td>';
        html += '<td>' + dto.name + '</td>';
        html += '<td>' + dto.phone + '</td>';
        html += '<td>' + dto.role + '</td>';
        html += '<td>' + dto.salary + '</td>';
        html += '<td style="display: none">' + dto.hiredate + '</td>';
        html += '<td style="display: none">' + dto.resignation + '</td>';
        html += '<td>' + dto.work_time + '</td>';
        html += '<td>' + '<a href="#editEmployeeModal" class="editBtn" data-toggle="modal">' +
            '<i class="material-icons" data-toggle="tooltip" title="Edit">' + '&#xE8B6;' + '</i>' + '</a>' +
            '<a href="#deleteEmployeeModal" class="delete" data-toggle="modal">' +
            '<i class="material-icons" data-toggle="tooltip" title="Delete">' + '&#xE872;' + '</i>' + '</a>' +
            '</td>';
        html += '</tr>';

        $("#employeePrintTable>tbody").append(html);
    }
}