// 전체 선택 체크박스 동작
const selectAllCheckbox = document.getElementById('selectAll');
const checkboxes = document.getElementsByName('selectedApplicant');

// 전체 선택 체크박스의 상태가 변경되면 모든 체크박스의 상태를 동기화
selectAllCheckbox.addEventListener('change', function() {
  checkboxes.forEach(checkbox => checkbox.checked = this.checked);
});

// 선택한 지원자 등록 버튼 동작
function registerSelectedApplicants() {
  const selectedApplicants = Array.from(checkboxes)
    .filter(checkbox => checkbox.checked)
    .map(checkbox => checkbox.value);

  if (selectedApplicants.length === 0) {
    alert("선택된 인원이 없습니다.");
    return;
  }

  if (confirm("등록하시겠습니까?")) {
    // 선택한 지원자를 매니저로 등록하는 API 호출
    selectedApplicants.forEach(applicantId => {
      $.ajax({
        url: `/matchGetIt/registerManager/${applicantId}`,
        type: 'PUT',
        dataType: 'json',
        success: function(response) {
          console.log('매니저로 등록된 사용자:', applicantId);
        },
        error: function(error) {
          console.error(error);
        }
      });
    });

    alert("등록이 완료되었습니다.");

    // 페이지 새로고침
    location.reload();
  }
}

// 유저 상세 정보 페이지로 이동
function viewUserDetails(userId) {
  // 해당 유저의 상세 정보 페이지 URL 생성
  const userDetailsUrl = `/ManagerApplicantDetailsView.html?userId=${userId}`;
  // 페이지 이동
  window.location.href = userDetailsUrl;
}

$(document).ready(function() {
  // 페이지 로드 시 지원자 목록을 가져와서 테이블에 표시
  loadApplicants();

  // 지원자 목록을 가져오고 테이블에 렌더링하는 함수
  function loadApplicants() {
    $.ajax({
      url: '/matchGetIt/getSupportedUsers',
      type: 'GET',
      dataType: 'json',
      success: function(response) {
        renderApplicants(response);
      },
      error: function(error) {
        console.error(error);
      }
    });
  }

  // 지원자 목록을 테이블에 렌더링하는 함수
  function renderApplicants(applicants) {
    const tbody = $('table tbody');
    tbody.empty();

    for (const applicant of applicants) {
      const row = `
        <tr>
          <td><input type="checkbox" name="selectedApplicant" value="${applicant.user.userId}" /></td>
          <td>${applicant.user.userId}</td>
          <td>${applicant.user.name}</td>
          <td>${applicant.user.pn}</td>
          <td>${applicant.user.rating}</td>
          <td>${applicant.activityZone}</td>
          <td><button onclick="viewUserDetails('${applicant.user.userId}')">상세 정보</button></td>
        </tr>
      `;
      tbody.append(row);
    }
  }
});
