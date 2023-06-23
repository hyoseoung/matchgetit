import React, { useEffect, useState } from 'react';
import '../../styles/LogIn/logInPage.css';
import '../../styles/LogIn/logInButton.css';
import SignUp from './SignUp';
import SocialLogIn from './SocialLogIn/SocialLogIn';

const LogIn = ({ onLogin }) => {
  useEffect(() => {
    const signUpBtn = document.querySelector('.signUp');
    const signUpWrap = document.querySelector('.signUp-wrap');
    const signUpBox = document.querySelector('.signUp-box');
    const signUpClose = document.querySelector('.closeBtn');

    signUpBtn.addEventListener('click', (e) => {
      signUpWrap.style.display = 'block';
      signUpBox.classList.add('transform-in');
      signUpBox.classList.remove('transform-out');
      e.preventDefault();
    });

    signUpClose.addEventListener('click', (e) => {
      signUpWrap.style.display = 'none';
      signUpBox.classList.add('transform-out');
      signUpBox.classList.remove('transform-in');
      e.preventDefault();
    });
  }, []);

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onLogin(email, password);
  };

  useEffect(() => {
    const storedToken = sessionStorage.getItem('csrfToken');
    if (storedToken) {
      // 이미 토큰이 존재할 경우 초기화하지 않음
      return;
    }

    // 토큰이 없으면 초기화
    const token = 'your_token_value'; // 적절한 토큰 값을 설정해주세요
    sessionStorage.setItem('csrfToken', token);
  }, []);

  return (
      <div className="LogInContainer">
        <p className="title">풋살 예약을 간편하게</p>
        <p className="logoMark">Match Get It</p>
        <form onSubmit={handleSubmit}>
          <div className="inputBlock">
            <input
                className="logInInput"
                type="email"
                placeholder="이메일"
                value={email}
                onChange={handleEmailChange}
            />
            <input
                className="logInInput"
                type="password"
                placeholder="비밀번호"
                value={password}
                onChange={handlePasswordChange}
            />
          </div>
          <div className="LogInbtnArea">
            <button className="logInBtn" type="submit">
              로그인
            </button>
          </div>
        </form>
        <div className="tabMenu">
          <div className="tabButton findIDPW">아이디/비밀번호 찾기</div>
          <div className="tabButton signUp">회원가입</div>
          <SignUp />
        </div>
        <SocialLogIn />
      </div>
  );
};

export default LogIn;
