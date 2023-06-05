import React, { useEffect } from 'react';
import '../styles/CommonFactor/menuBar.css';
import Swiper from 'swiper';

function Footer() {
  useEffect(() => {
    var swiper = new Swiper('.swiper-container', {
      effect: 'slide',
      cubeEffect: {
        slideShadows: false,
        shadow: false,
        shadowOffset: 20,
        shadowScale: 0.94,
      },
      loop: true,
      pagination: {
        el: '.swiper-pagination',
        clickable: true,
      },
      threshold: 70, // Set the desired threshold value for swipe gestures
    });

    // 메뉴바 링크 클릭 시 해당 슬라이드로 이동하는 함수
    var links = document.querySelectorAll('.link');

    links.forEach(function (link) {
      link.addEventListener('click', function (e) {
        e.preventDefault();
        var slideIndex = parseInt(link.dataset.slide);
        swiper.slideToLoop(slideIndex);
        setActiveLink(slideIndex);
      });

      link.addEventListener('mouseenter', function (e) {
        var slideIndex = parseInt(link.dataset.slide);
        setActiveLink(slideIndex);
      });

      link.addEventListener('mouseleave', function (e) {
        setActiveLink(null);
      });
    });

    // 슬라이드 변경 시 링크 활성화/비활성화 처리
    swiper.on('slideChange', function () {
      var activeIndex = swiper.realIndex;
      setActiveLink(activeIndex);
    });

    // 활성화된 링크 스타일 설정 함수
    function setActiveLink(activeIndex) {
      var links = document.querySelectorAll('.link');
      links.forEach(function (link) {
        link.classList.remove('active');
      });

      if (activeIndex !== null) {
        var activeLink = document.querySelector('.link[data-slide="' + activeIndex + '"]');
        if (activeLink) {
          activeLink.classList.add('active');
        }
      }
    }

    var secondSlide = document.querySelector('.secondSlide');

    if (secondSlide) {
      secondSlide.addEventListener('click', function () {
        swiper.slideToLoop(1);
      });
    }
  }, []);

  return (
    <div className="menu">
      <button className="link" data-slide="1">
        <span className="link-icon">
          <img src={process.env.PUBLIC_URL + '/images/rankIcon.svg'} alt="rankIcon" />
        </span>
        <span className="link-title">Rank</span>
      </button>
      <button className="link" data-slide="2">
        <span className="link-icon">
          <img src={process.env.PUBLIC_URL + '/images/BallIcon.svg'} alt="BallIcon" />
        </span>
        <span className="link-title">Match</span>
      </button>

      <button className="link" data-slide="3">
        <span className="link-icon">
          <img src={process.env.PUBLIC_URL + '/images/ProfileIcon.svg'} alt="ProfileIcon" />
        </span>
        <span className="link-title">Profile</span>
      </button>
    </div>
  );
}

export default Footer;
