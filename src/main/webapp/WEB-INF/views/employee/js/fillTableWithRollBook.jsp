//Employees의 정보를 테이블에 출력해주는 함수
function fillTableWithRollBook(data) {
    for (var i in data) {
        var dto = data[i];
        var html = "";

        html += '<tr>';
        html += '<td style="display: none">' + dto.Id + '</td>';
        html += '<td>' + dto.id + '</td>';
        html += '<td>' + dto.name + '</td>';
        html += '<td>' + dto.clockIn + '</td>';
        html += '<td>' + dto.clockOut + '</td>';
        html += '<td>' + dto.workTime + '</td>';
        html += '</tr>';

        $("#rollBookPrintTable>tbody").append(html);
    }
}