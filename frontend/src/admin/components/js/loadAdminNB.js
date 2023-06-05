// loadAdminNB.js
$(document).ready(function() {
  // NavigationBar.html 로드
  $('#admin-nb').load('../../components/Sidebar/NavigationBar.html', function() {
    // NavigationBar.html 로드 완료 후 AdminHeader.html 로드
    $('#admin-header').load('../../components/Header/AdminHeader.html');
  });
});
