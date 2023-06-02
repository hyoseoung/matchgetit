window.addEventListener('DOMContentLoaded', function() {
  var background = document.querySelector('.background');
  setTimeout(function() {
    background.classList.add('hidden');
    setTimeout(function() {
      background.style.display = 'none';
    }, 1000); // Additional delay of 1 second (total 3 seconds)
  }, 2000); // 2초 후에 배경 이미지와 내용이 서서히 사라집니다.
});