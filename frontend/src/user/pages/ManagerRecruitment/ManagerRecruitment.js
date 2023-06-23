import React from 'react';
import '../../styles/css/CommonFactor/menuBar.css';
import '../../styles/css/CommonFactor/button.css';
import '../../styles/css/CommonFactor/header.css';
import '../../styles/css/swipePage.css';
import '../../styles/css/rankBoardPage.css';
import '../../styles/css/MatchingPage/matchingPage.css';
//import '../../styles/css/thirdSlide.css';

import '../../styles/css/LogIn/logInButton.css';

import './css/ManagerRecruitment.css';

import rankIcon from '../../styles/img/rankIcon.svg';
import BallIcon from '../../styles/img/BallIcon.svg';
import ProfileIcon from '../../styles/img/ProfileIcon.svg';
import PageLogo from '../../styles/img/PageLogo.png';

import Swiper from 'swiper';
import 'swiper/swiper-bundle.min.css';

function ManagerRecruitment() {
  return (
    <div>
      {/* 좌상단 메뉴바 part */}
      <div id="menu-bar">
        {/* Menu Icon */}
        <div className="menu-icon">
          <span className="menu-icon__line menu-icon__line-left"></span>
          <span className="menu-icon__line"></span>
          <span className="menu-icon__line menu-icon__line-right"></span>
        </div>
        {/* Navigation */}
        <div className="nav">
          <div className="nav__content">
            <div className="nav__list">
              <div className="nav__list-item">홈페이지</div>
              <div className="nav__list-item">게시판</div>
              <div className="nav__list-item">마이페이지</div>
              <div className="nav__list-item">로그인/회원가입</div>
            </div>
          </div>
        </div>
      </div>

      {/* 로그아웃 버튼 */}
      <div className="btnArea">
        <button className="logOutBtn">Log In</button>
      </div>

      {/* 지원서 폼 */}
      <div className="ApplyManager">
        <form action="submit-form.php" method="POST">
          <div style={{ marginBottom: '15px' }}>
            <h1 className="form-title">지원 양식</h1>
          </div>
          <br />
          <br />
          <br />
          <div className="form-row">
            <label htmlFor="name">성함: </label>
            <input
              type="text"
              id="name"
              name="name"
              value="(로그인된유저 이름)"
              readOnly
            />
          </div>
          <br />
          <br />
          <div className="form-row">
            <label htmlFor="contact">연락처: </label>
            <input
              type="text"
              id="contact"
              name="contact"
              value="(로그인된 유저의 연락처)"
              readOnly
            />
          </div>
          <br />
          <br />
          <div className="form-row">
            <label htmlFor="gender">성별: </label>
            <input
              type="text"
              id="gender"
              name="gender"
              value="(로그인된 유저의 성별)"
              readOnly
            />
          </div>
          <br />
          <br />
          <div className="form-row">
            <label htmlFor="region">활동권역: </label>
            <select id="region" name="region">
              <option value="서울">서울</option>
              <option value="경기">경기</option>
              <option value="인천">인천</option>
              {/* 여기에 다른 지역 옵션 추가 */}
            </select>
            <br />
            <br />
            <br />
          </div>
          <div className="form-row form-row-button">
            <input type="submit" value="제출하기" />
          </div>
        </form>
      </div>

      <div className="menu">
                <a href="./MainPage.html" className="link" data-slide="1">
                  <span className="link-icon">
                    <img src={rankIcon} alt="rankIcon" />
                  </span>
                  <span className="link-title">Rank</span>
                </a>
                <a href="#" className="link" data-slide="2">
                  <span className="link-icon">
                    <img src={BallIcon} alt="BallIcon" />
                  </span>
                  <span className="link-title">Match</span>
                </a>
                <a href="#" className="link" data-slide="3">
                  <span className="link-icon">
                    <img src={ProfileIcon} alt="ProfileIcon" />
                  </span>
                  <span className="link-title">Profile</span>
                </a>
              </div>

    </div>
  );
}

export default ManagerRecruitment;
