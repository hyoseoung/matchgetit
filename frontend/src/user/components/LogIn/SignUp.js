import React, { useEffect } from 'react';
import '../../styles/CommonFactor/radioButton.css';
import '../../styles/LogIn/signUp.css';

const SignUp = (props) => {
    return(
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
    );
};
export default SignUp;