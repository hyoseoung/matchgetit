import React, { useEffect } from 'react';
import '../../styles/LogIn/logInPage.css';
import '../../styles/LogIn/logInButton.css';
import '../../styles/CommonFactor/radioButton.css';
import '../../styles/LogIn/signUp.css';
import '../../styles/LogIn/socialButton.css';

const LogIn = () => {
  useEffect(() => {
    const popupBtn = document.querySelector('.signUp');
    const popupWrap = document.querySelector('.signUp-wrap');
    const popupBox = document.querySelector('.signUp-box');
    const popupClose = document.querySelector('.closeBtn');

    popupBtn.addEventListener('click', (e) => {
      popupWrap.style.display = 'block';
      popupBox.classList.add('transform-in');
      popupBox.classList.remove('transform-out');
      e.preventDefault();
    });

    popupClose.addEventListener('click', (e) => {
      popupWrap.style.display = 'none';
      popupBox.classList.add('transform-out');
      popupBox.classList.remove('transform-in');
      e.preventDefault();
    });
  }, []);

  return (
    <div className="LogInContainer">
      <p className="title">풋살 예약을 간편하게</p>
      <p className="logoMark">Match Get It</p>
      <form action="">
        <div className="inputBlock">
          <input className="logInInput" type="email" placeholder="이메일" />
          <input className="logInInput" type="password" placeholder="비밀번호" />
        </div>
      </form>
      <div className="LogInbtnArea">
        <button className="logInBtn">로그인</button>
      </div>
      <div className="tabMenu">
        <div className="tabButton findIDPW">아이디/비밀번호 찾기</div>
        <div className="tabButton signUp">회원가입</div>
        <div className="signUp-wrap">
          <div className="signUp-box">
            <div className="signUpContainer">
              로고 자리
              <div className="signUpTitle">회원가입</div>
              <form action="#">
                <div className="signUp-Items">
                  <div className="inputData">
                    <input
                      className="signUpInput"
                      type="email"
                      placeholder="이메일 00000@example.com"
                    />
                    <button className="duplicateButton" type="button">
                      중복확인
                    </button>
                  </div>
                  <div className="inputData">
                    <input
                      className="signUpInput"
                      type="password"
                      placeholder="비밀번호"
                    />
                  </div>
                  <div className="inputData">
                    <input
                      className="signUpInput"
                      type="text"
                      placeholder="이름"
                    />
                  </div>
                  <div className="inputData">
                    <input
                      className="signUpInput"
                      type="tel"
                      placeholder="전화번호 000-0000-0000"
                    />
                    <button className="duplicateButton" type="button">
                      중복확인
                    </button>
                  </div>
                  <div className="radioBtn gender">
                    성별:
                    <input type="radio" id="male" name="gender" />
                    <label htmlFor="male">남</label>
                    <input type="radio" id="female" name="gender" />
                    <label htmlFor="female">여</label>
                  </div>
                  <div className="radioBtn proficiency">
                    숙련도:
                    <input type="radio" id="advanced" name="proficiency" />
                    <label htmlFor="advanced">상</label>
                    <input type="radio" id="middle" name="proficiency" />
                    <label htmlFor="middle">중</label>
                    <input type="radio" id="begginer" name="proficiency" />
                    <label htmlFor="begginer">하</label>
                  </div>
                  <button className="submitBtn" type="submit">
                    회원가입
                  </button>
                </div>
              </form>
            </div>
            <div className="closeBtn">
              <div className="inner">
                <label>Back</label>
              </div>
            </div>
          </div>
        </div>
      </div>
      <button className="socialButton kakaoButton">kakao</button>
      <button className="socialButton googleButton">google</button>
      <button className="socialButton naverButton">Naver</button>
    </div>
  );
};

export default LogIn;
