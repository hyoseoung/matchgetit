import React, { useEffect } from 'react';
import '../styles/swipePage.css';
import LogIn from './LogIn/LogIn';
import Match from './Match/Match';
import Swiper from 'swiper';
import 'swiper/swiper-bundle.css';

function Main() {
  useEffect(() => {
    const videoElement = document.createElement('video');
    videoElement.src = process.env.PUBLIC_URL + '/background/soccerBackground.mp4';
    videoElement.loop = true;
    videoElement.muted = true;
    videoElement.autoplay = true;
    videoElement.style.objectFit = 'cover';

    const container = document.querySelector('.swiper-container');
    container.appendChild(videoElement);

    const swiper = new Swiper('.swiper-container', {
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

    return () => {
      swiper.destroy();
    };
  }, []);

  return (
      <div className="swiper-container">
        <div className="swiper-wrapper">
          <div className="swiper-slide">
            <div className="slideContainer">
              <div className="slide-page">
                <LogIn />
              </div>
            </div>
          </div>
          <div className="swiper-slide">
            <div className="slideContainer">
              <div className="slide-page">랭킹 게시판 페이지</div>
            </div>
          </div>
          <div className="swiper-slide">
            <div className="slideContainer">
              <div className="slide-page">
                <Match />
              </div>
            </div>
          </div>
          <div className="swiper-slide">
            <div className="slideContainer">
              <div className="slide-page">마이 페이지</div>
            </div>
          </div>
        </div>
        <div className="swiper-pagination"></div>
      </div>
  );
};

export default Main;
