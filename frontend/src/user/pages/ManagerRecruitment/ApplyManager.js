import React from 'react';
import { Link } from 'react-router-dom';
import './css/ApplyManager.css';
import Footer from "../../components/Footer";

//import rankIcon from '../../../public/images/rankIcon.svg';
//import BallIcon from '../../../public/images/BallIcon.svg';
//import ProfileIcon from '../../../public/images/ProfileIcon.svg';
//import PageLogo from '../../public/images/PageLogo.png';

import Swiper from 'swiper';
import 'swiper/swiper-bundle.min.css';



class ApplyManager extends React.Component {
  componentDidMount() {
    new Swiper('.swiper-container', {
      // swiper options here...
    });
  }

  render() {
    return (
      <div>

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
                <div className="nav__list-item">마이페이지</div>
                <div className="nav__list-item">로그인/회원가입</div>
              </div>
            </div>
          </div>
        </div>

        <div className="btnArea">
          <button className="logOutBtn">Log In</button>
        </div>

        <div className="ApplyManager">
          <h1>매니저 모집</h1>
          <h1>매치기릿 매니저를 모집합니다 !</h1>
          <br />
          <br />
          <br />
          <br />
          <ul>
            <li>※ 활동비 - 1경기(2시간) / 25,000원</li>
            <br />
            <br />
            <br />
            <li>※ 활동내용 - 경기진행, 회원출결관리, 경기장비관리, 경기기록작성</li>
            <br />
            <br />
            <br />
            <li>※ 인원 부족한 경우 직접 경기참여 의무가 있습니다.</li>
            <br />
            <br />
            <br />
          </ul>
          <button className="applyBtn">
            <a href="/ManagerRecruitment.js">매니저 지원하기</a>
          </button>
        </div>

        <Footer/>



        <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
        <script src="./styles/js/CommonFactor/header.js"></script>
        <script src="./styles/js/swiperPage.js"></script>
        <script src="./styles/js/subPage.js"></script>
        <script src="./styles/js/LogIn/signUp.js"></script>
      </div>
    );
  }
}

export default ApplyManager;
