import React, { useEffect } from 'react';
import '../styles/CommonFactor/header.css';

function Header() {
  useEffect(() => {
    const bodyElement = document.querySelector('body');
    const menuIconElement = document.querySelector('.menu-icon');

    const toggleClass = (element, className) => {
      if (element.classList.contains(className))
        element.classList.remove(className);
      else
        element.classList.add(className);
    };

    const applyListeners = () => {
      menuIconElement.addEventListener('click', () => {
        toggleClass(bodyElement, 'nav-active');
      });
    };

    return () => {
      applyListeners();
    };
  }, []);

  return (
      <div id="menu-bar">
        <div className="menu-icon">
          <span className="menu-icon__line menu-icon__line-left"></span>
          <span className="menu-icon__line"></span>
          <span className="menu-icon__line menu-icon__line-right"></span>
        </div>
        <div className="nav">
          <div className="nav__content">
            <div className="nav__list">
              <div className="nav__list-item">홈페이지</div>
              <div className="nav__list-item">게시판</div>
              <div className="nav__list-item">매니저 지원하기</div>
              <div className="nav__list-item">로그인/회원가입</div>
            </div>
          </div>
        </div>
      </div>
  );
}

export default Header;