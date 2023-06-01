// 네비게이션 바 항목 클릭 시 서브 메뉴 표시
const navItems = document.querySelectorAll('.nav-item');
const subMenus = document.querySelectorAll('.sub-menu');

navItems.forEach(item => {
  item.addEventListener('click', function(event) {
    event.preventDefault();

    // 활성화된 항목 스타일 변경
    navItems.forEach(item => item.classList.remove('active'));
    this.classList.add('active');

    // 해당 탭 표시 및 비표시
    const targetTab = this.getAttribute('data-tab');
    subMenus.forEach(menu => {
      if (menu.id === targetTab) {
        menu.style.display = 'block';
      } else {
        menu.style.display = 'none';
      }
    });
  });
});
// AdminHeader.html 파일 가져오기
const xhr = new XMLHttpRequest();
xhr.onreadystatechange = function() {
  if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
    const header = document.getElementById('admin-header');
    header.innerHTML = xhr.responseText;
  }
};
xhr.open('GET', 'AdminHeader.html', true);
xhr.send();
