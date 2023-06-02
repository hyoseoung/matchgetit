document.addEventListener('DOMContentLoaded', function() {
    var redIcon = document.querySelector('.redIcon');
    var blueIcon = document.querySelector('.blueIcon');
    var subPage1 = document.querySelector('.subPage1');
    var subPage2 = document.querySelector('.subPage2');
  
    var togglePages = function(targetPage1, targetPage2) {
      targetPage1.classList.add('active');
      targetPage1.classList.remove('inactive');
      targetPage2.classList.remove('active');
      targetPage2.classList.add('inactive');
    };
  
    var handleBlueIconEvent = function() {
      togglePages(subPage1, subPage2);
    };
    var handleRedIconEvent = function() {
      togglePages(subPage2, subPage1);
    };
  
    redIcon.addEventListener('click', handleRedIconEvent);
    redIcon.addEventListener('touchstart', handleRedIconEvent);
  
    blueIcon.addEventListener('click', handleBlueIconEvent);
    blueIcon.addEventListener('touchstart', handleBlueIconEvent);
      var pointBtns = document.querySelectorAll('.pointBtn');
    
      pointBtns.forEach(function(pointBtn) {
        var originalText = pointBtn.innerText;
    
        pointBtn.addEventListener('mouseover', function() {
          pointBtn.innerText = '포인트 충전하기';
        });
    
        pointBtn.addEventListener('mouseout', function() {
          pointBtn.innerText = originalText;
        });
      });
  });