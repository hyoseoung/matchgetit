import React, { useState } from 'react';
import '../../styles/CommonFactor/radioButton.css';
import '../../styles/LogIn/signUp.css';
import axiosInstance from '../axiosInstance';

const SignUp = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [pwCheck, setPwCheck] = useState('');
    const [name, setName] = useState('');
    const [pn, setPn] = useState('');
    const [gender, setGender] = useState('');
    const [proficiency, setProficiency] = useState('');
    const [birthDay, setBirthDay] = useState('');
    const [isSuccess, setIsSuccess] = useState(false);
    const [isFailure, setIsFailure] = useState(false);
    const [emailVal, setEmailVal] = useState(false);
    const [phoneNumVal, setPhoneNumVal] = useState(false);
    const [isValidate, setIsValidate] = useState(false);
    const [isPwCk, setIsPwCk] = useState(false);
    const [validateStatus, setValidateStatus] = useState(null);
    const [errorReason, setErrorReason] = useState('');

    const handleSignUp = (e) => {
        e.preventDefault();
        if (emailVal && phoneNumVal) {
            if (pwCheck === password) {
                axiosInstance
                    .post('/matchGetIt/auth/signUp', {
                        email,
                        password,
                        name,
                        pn,
                        gender,
                        proficiency,
                        birthDay,
                    })
                    .then((response) => {
                        console.log(response.data);
                        setIsSuccess(true);
                        setIsFailure(false);
                        setTimeout(() => {
                            setIsSuccess(false);
                        }, 3000);
                    })
                    .catch((error) => {
                        console.error(error.response.data);
                        setIsSuccess(false);
                        setIsFailure(true);
                        setErrorReason(error.response.data); // 서버에서 에러 메시지 받아옴
                        setTimeout(() => {
                            setIsFailure(false);
                            setErrorReason('');
                        }, 3000);
                    });
            } else {
                setIsPwCk(true);
                setTimeout(() => {
                    setIsPwCk(false);
                }, 3000);
            }
        } else {
            setIsValidate(true);
            setTimeout(() => {
                setIsValidate(false);
            }, 3000);
        }
    };

    const validateEmail = () => {
        axiosInstance
            .post('/matchGetIt/auth/vaildateEmail', null, { params: { email: email } })
            .then((response) => {
                setEmailVal(true);
                setValidateStatus(false);
                setErrorReason(''); // 초기화
                setTimeout(() => {
                    setValidateStatus(null);
                }, 3000);
            })
            .catch((error) => {
                setEmailVal(false);
                setValidateStatus(true);
                setTimeout(() => {
                    setValidateStatus(null);
                }, 3000);
            });
    };

    const validatePhoneNumber = () => {
        axiosInstance
            .post('/matchGetIt/auth/validatePhoneNumber', null, { params: { phoneNumber: pn } })
            .then((response) => {
                setPhoneNumVal(true);
                setValidateStatus(false);
                setErrorReason(''); // 초기화
                setTimeout(() => {
                    setValidateStatus(null);
                }, 3000);
            })
            .catch((error) => {
                setPhoneNumVal(false);
                setValidateStatus(true);
                setTimeout(() => {
                    setValidateStatus(null);
                }, 3000);
            });
    };

    return (
        <div className="signUp-wrap" style={{ display: isSuccess ? 'block' : 'none' }}>
            <div className="signUp-box">
                <div className="signUpContainer">
                    <div className="signUpTitle">회원가입</div>
                    {validateStatus !== null && (
                        <div style={{ color: validateStatus ? 'red' : 'green', fontSize: '30px' }}>
                            {validateStatus ? '사용불가능' : '사용가능'}
                        </div>
                    )}
                    {errorReason && (
                        <div style={{ color: 'red', fontSize: '30px' }}>회원가입 실패 : {errorReason}</div>
                    )}
                    {isFailure && <div style={{ color: 'red', fontSize: '20px' }}>회원가입 실패</div>}
                    {isSuccess && <div style={{ color: 'green', fontSize: '20px' }}>회원가입 성공</div>}
                    {isValidate && <div style={{ color: 'red', fontSize: '20px' }}>중복검사가 필요합니다</div>}
                    {isPwCk && <div style={{ color: 'red', fontSize: '20px' }}>비밀번호가 일치하지 않습니다</div>}
                    <form onSubmit={handleSignUp}>
                        {/* 이메일 입력 */}
                        <div className="inputData">
                            <input
                                className="signUpInput"
                                type="email"
                                placeholder="이메일 00000@example.com"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                required
                            />
                            <button className="duplicateButton" type="button" onClick={validateEmail}>
                                중복확인
                            </button>
                        </div>
                        {/* 비밀번호 입력 */}
                        <div className="inputData">
                            <input
                                className="signUpInput"
                                type="password"
                                placeholder="비밀번호(영문+숫자+특수문자 포함 8자 이상)"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                required
                            />
                        </div>
                        <div className="inputData">
                            <input
                                className="signUpInput"
                                type="password"
                                placeholder="비밀번호 확인"
                                value={pwCheck}
                                onChange={(e) => setPwCheck(e.target.value)}
                                required
                            />
                        </div>
                        {/* 이름 입력 */}
                        <div className="inputData">
                            <input
                                className="signUpInput"
                                type="text"
                                placeholder="이름"
                                value={name}
                                onChange={(e) => setName(e.target.value)}
                                required
                            />
                        </div>
                        {/* 전화번호 입력 */}
                        <div className="inputData">
                            <input
                                className="signUpInput"
                                type="tel"
                                placeholder="전화번호 000-0000-0000"
                                value={pn}
                                onChange={(e) => setPn(e.target.value)}
                                required
                            />
                            <button className="duplicateButton" type="button" onClick={validatePhoneNumber}>
                                중복확인
                            </button>
                        </div>
                        {/* 생일 입력 */}
                        <div className="birthDay">
                            <span className="birthDayText">생일 입력:</span>
                            <input
                                type="date"
                                className="timeSelect mDate"
                                value={birthDay}
                                onChange={(e) => setBirthDay(e.target.value)}
                                required
                            />
                        </div>
                        {/* 성별 선택 */}
                        <div className="radioBtn gender">
                            성별:
                            <input
                                type="radio"
                                id="male"
                                name="gender"
                                value="MALE"
                                checked={gender === 'MALE'}
                                onChange={(e) => setGender(e.target.value)}
                                required
                            />
                            <label htmlFor="male">남</label>
                            <input
                                type="radio"
                                id="female"
                                name="gender"
                                value="FEMALE"
                                checked={gender === 'FEMALE'}
                                onChange={(e) => setGender(e.target.value)}
                                required
                            />
                            <label htmlFor="female">여</label>
                        </div>
                        {/* 숙련도 선택 */}
                        <div className="radioBtn proficiency">숙련도:
                            <input
                                type="radio"
                                id="advanced"
                                name="proficiency"
                                value="ADVANCED"
                                checked={proficiency === 'ADVANCED'}
                                onChange={(e) => setProficiency(e.target.value)}
                                required
                            />
                            <label htmlFor="advanced">상</label>
                            <input
                                type="radio"
                                id="middle"
                                name="proficiency"
                                value="MIDDLE"
                                checked={proficiency === 'MIDDLE'}
                                onChange={(e) => setProficiency(e.target.value)}
                                required
                            />
                            <label htmlFor="middle">중</label>
                            <input
                                type="radio"
                                id="beginner"
                                name="proficiency"
                                value="BEGINNER"
                                checked={proficiency === 'BEGINNER'}
                                onChange={(e) => setProficiency(e.target.value)}
                                required
                            />
                            <label htmlFor="beginner">하</label>
                        </div>
                        {/* 회원가입 버튼 */}
                        <button className="submitBtn" type="submit">
                            회원가입
                        </button>
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
