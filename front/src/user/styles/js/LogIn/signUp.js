document.addEventListener('DOMContentLoaded', function() {
  var popupBtn = document.querySelector('.signUp');
  var popupWrap = document.querySelector('.signUp-wrap');
  var popupBox = document.querySelector('.signUp-box');
  var popupClose = document.querySelector('.closeBtn');

  popupBtn.addEventListener('click', function(e) {
    popupWrap.style.display = 'block';
    popupBox.classList.add('transform-in');
    popupBox.classList.remove('transform-out');
    e.preventDefault();
  });

  popupClose.addEventListener('click', function(e) {
    popupWrap.style.display = 'none';
    popupBox.classList.add('transform-out');
    popupBox.classList.remove('transform-in');
    e.preventDefault();
  });
});