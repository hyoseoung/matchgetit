import React, { useState } from 'react';
import './profile.css';

function MyPage() {
    const [userEmail, setUserEmail] = useState('');
    const [userName, setUserName] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [userPassword, setUserPassword] = useState('');
    const [userAccount, setUserAccount] = useState('');
    const [gender, setGender] = useState('1');
    const [skillLevel, setSkillLevel] = useState('expert');

    const handleSave = () => {
        // 회원정보 저장 로직 구현
    };

    const handleDelete = () => {
        // 회원 탈퇴 로직 구현
    };

    return (
        <div>
            <h3>내 정보창</h3>
            <section className="my-page">
                <div className="my-page_body">
                    <div className="my-info">
                        <div className="forms">
                            <fieldset>
                                <div className="input">
                                    <div className="input_wrap">
                                        <label>이메일</label>
                                        <input
                                            type="email"
                                            id="useremail"
                                            value={userEmail}
                                            onChange={(e) => setUserEmail(e.target.value)}
                                            placeholder="example@email.com"
                                        />
                                    </div>
                                </div>
                                <div className="input">
                                    <div className="input_wrap">
                                        <label>이름</label>
                                        <input
                                            type="text"
                                            id="username"
                                            value={userName}
                                            onChange={(e) => setUserName(e.target.value)}
                                            placeholder="김 풋살"
                                        />
                                    </div>
                                </div>
                                <div className="input">
                                    <div className="input_wrap">
                                        <label>전화번호</label>
                                        <div className="input-button-container">
                                            <input
                                                type="tel"
                                                id="phonenumber"
                                                value={phoneNumber}
                                                onChange={(e) => setPhoneNumber(e.target.value)}
                                                placeholder="010-5678-1234"
                                            />
                                            <input
                                                type="button"
                                                value="전화번호 인증"
                                                className="button"
                                            />
                                        </div>
                                    </div>
                                </div>
                                <div className="input">
                                    <div className="input_wrap">
                                        <label>비밀번호</label>
                                        <div className="input-button-container">
                                            <input
                                                type="password"
                                                id="userpassword"
                                                value={userPassword}
                                                onChange={(e) => setUserPassword(e.target.value)}
                                                placeholder="********"
                                            />
                                            <input
                                                type="button"
                                                value="비밀번호 변경"
                                                className="button"
                                            />
                                        </div>
                                    </div>
                                </div>
                                <div className="input">
                                    <div className="input_wrap">
                                        <label>계좌</label>
                                        <input
                                            type="text"
                                            id="useraccount"
                                            value={userAccount}
                                            onChange={(e) => setUserAccount(e.target.value)}
                                            placeholder="12**-**-****567"
                                        />
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                    </div>
                </div>
            </section>
            <section className="my-page">
                <div className="my-page_body">
                    <div className="my-info">
                        <div className="forms">
                            <fieldset>
                                <div className="input">
                                    <div className="input_wrap">
                                        <label>성별</label>
                                        <ul className="chip">
                                            <li className="chip_item">
                                                <input
                                                    type="radio"
                                                    id="1"
                                                    className="chip_item-radio"
                                                    name="gender"
                                                    value="1"
                                                    checked={gender === '1'}
                                                    onChange={() => setGender('1')}
                                                />
                                                <label htmlFor="1" className="chip_item-label">
                                                    남자
                                                </label>
                                            </li>
                                            <li className="chip_item">
                                                <input
                                                    type="radio"
                                                    id="2"
                                                    className="chip_item-radio"
                                                    name="gender"
                                                    value="2"
                                                    checked={gender === '2'}
                                                    onChange={() => setGender('2')}
                                                />
                                                <label htmlFor="2" className="chip_item-label">
                                                    여자
                                                </label>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div className="input" style={{ marginBottom: '30px' }}>
                                    <div className="input_wrap">
                                        <label>숙련도</label>
                                        <ul className="chip">
                                            <li className="chip_item">
                                                <input
                                                    type="radio"
                                                    id="expert"
                                                    className="chip_item-radio"
                                                    name="skill-level"
                                                    value="expert"
                                                    checked={skillLevel === 'expert'}
                                                    onChange={() => setSkillLevel('expert')}
                                                />
                                                <label htmlFor="expert" className="chip_item-label">
                                                    상
                                                </label>
                                            </li>
                                            <li className="chip_item">
                                                <input
                                                    type="radio"
                                                    id="middle"
                                                    className="chip_item-radio"
                                                    name="skill-level"
                                                    value="middle"
                                                    checked={skillLevel === 'middle'}
                                                    onChange={() => setSkillLevel('middle')}
                                                />
                                                <label htmlFor="middle" className="chip_item-label">
                                                    중
                                                </label>
                                            </li>
                                            <li className="chip_item">
                                                <input
                                                    type="radio"
                                                    id="basic"
                                                    className="chip_item-radio"
                                                    name="skill-level"
                                                    value="basic"
                                                    checked={skillLevel === 'basic'}
                                                    onChange={() => setSkillLevel('basic')}
                                                />
                                                <label htmlFor="basic" className="chip_item-label">
                                                    하
                                                </label>
                                            </li>
                                        </ul>
                                        <a class="change">※ 숙련도는 리그 매치 전에 1회에 한하여 <br/>  변경 가능합니다.</a>
                                        <input
                                            type="button"
                                            value="회원정보 저장"
                                            className="button2"
                                            onClick={handleSave}
                                        />
                                        <input
                                            type="button"
                                            value="회원 탈퇴"
                                            className="button2"
                                            onClick={handleDelete}
                                        />
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    );
}

export default MyPage;
