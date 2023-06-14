// 폼 서브밋 시 이벤트 처리
$("form").submit(function(event) {
    event.preventDefault(); // 폼 서브밋 기본 동작 방지

    // 수정할 매니저 정보 가져오기
    var managerId = ${manager.managerId};
    var name = $("#name").val();
    var contact = $("#contact").val();
    var gender = $("input[name='gender']:checked").val();
    var employmentStatus = $("#status").val();
    var leaveStartDate = $("#leaveStartDate").val();
    var leaveEndDate = $("#leaveEndDate").val();
    var leaveReason = $("#leaveReason").val();

    // 휴직 기간이 선택된 경우 권한 박탈
    if (employmentStatus === 'leave' && leaveStartDate && leaveEndDate) {
        // AJAX를 사용하여 매니저 정보 업데이트
        $.ajax({
            type: "PUT",
            url: "/api/manager/" + managerId + "/deactivate",
            success: function(response) {
                if (response === "success") {
                    alert("매니저 정보가 성공적으로 업데이트되었습니다.");
                    window.location.reload(); // 페이지 새로고침
                } else {
                    alert("매니저 정보 업데이트 중 오류가 발생했습니다.");
                }
            },
            error: function(xhr, status, error) {
                console.error(error);
                alert("매니저 정보 업데이트 중 오류가 발생했습니다.");
            }
        });
    } else {
        // AJAX를 사용하여 매니저 정보 업데이트
        $.ajax({
            type: "PUT",
            url: "/api/manager/" + managerId,
            data: JSON.stringify({
                name: name,
                contact: contact,
                gender: gender,
                employmentStatus: employmentStatus,
                leaveStartDate: leaveStartDate,
                leaveEndDate: leaveEndDate,
                leaveReason: leaveReason
            }),
            contentType: "application/json",
            success: function(response) {
                if (response === "success") {
                    alert("매니저 정보가 성공적으로 업데이트되었습니다.");
                    window.location.reload(); // 페이지 새로고침
                } else {
                    alert("매니저 정보 업데이트 중 오류가 발생했습니다.");
                }
            },
            error: function(xhr, status, error) {
                console.error(error);
                alert("매니저 정보 업데이트 중 오류가 발생했습니다.");
            }
        });
    }
});

// 매니저 삭제
function deleteManager() {
    if (confirm("정말로 매니저를 삭제하시겠습니까?")) {
        $.ajax({
            type: "DELETE",
            url: "/api/manager/${manager.id}",
            success: function(response) {
                if (response === "success") {
                    alert("매니저가 성공적으로 삭제되었습니다.");
                    window.location.href = "Manager.html"; // 매니저 목록 페이지로 이동
                }
            },
            error: function(xhr, status, error) {
                console.error(error);
                alert("매니저 삭제 중 오류가 발생했습니다.");
            }
        });
    }
}


