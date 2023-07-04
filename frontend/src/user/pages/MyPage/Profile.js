import React, { useState } from 'react';
import './profile.css';
import axios from "axios";

function MyPage({session}) {
    const [email, setEmail] = useState(session.email || '');
    const [name, setName] = useState(session.name || '');
    const [pn, setPn] = useState(session.pn || '');
    const [password, setPassword] = useState('');
    const [gender, setGender] = useState((session.gender === 'MALE') ? '1' : '2');
    const [prfcn, setProficiency] = useState(session.prfcn || '');

    const handleEdit = () => {
        if (window.confirm("수정하시겠습니까?")) {
            //회원정보 저장 로직 구현
            axios
                .put(`/matchGetIt/auth/update`, {
                        email,
                        name,
                        pn
                    },
                    {params: {id: session.userId}})
                .then((response) => {
                    alert('수정되었습니다.');
                    console.log(response.data);
                })
                .catch((error) => {
                    console.error(error.response.data);
                });
        }
    };

    const handleDelete = () => {
        if (window.confirm("확인을 누르면 회원 정보가 삭제됩니다.")) {
            // 회원 탈퇴 로직 구현
            axios
                .delete('/matchGetIt/auth/delete', {
                    params: {id: session.userId}
                })
                .then(() => {
                    alert('탈퇴되었습니다.');
                    window.location.href = '/user/Main.js';
                })
                .catch((error) => {
                    console.error("실패");
                });
        }
    };

    const passwordEdit = () => {
        if (window.confirm("비밀번호를 변경하시겠습니까?")) {
            // 비밀번호 변경 로직 구현
            axios
                .put(`/matchGetIt/auth/updatePw`, {
                        password
                    },
                    {params: {id: session.userId}})
                .then((response) => {
                    alert('수정되었습니다.');
                    console.log(response.data);
                })
                .catch((error) => {
                    console.error(error.response.data);
                });
        }
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
                                            id="email"
                                            value={email}
                                            onChange={(e) => setEmail(e.target.value)}
                                            placeholder="example@email.com"
                                        />
                                    </div>
                                </div>
                                <div className="input">
                                    <div className="input_wrap">
                                        <label>이름</label>
                                        <input
                                            type="text"
                                            id="name"
                                            value={name}
                                            onChange={(e) => setName(e.target.value)}
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
                                                id="pn"
                                                value={pn}
                                                onChange={(e) => setPn(e.target.value)}
                                                placeholder="010-5678-1234"
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
                                                value={password}
                                                onChange={(e) => setPassword(e.target.value)}
                                                placeholder="********"
                                            />
                                            <button onClick={passwordEdit}
                                                    type="button"
                                                    className="button"
                                            >비밀번호 변경
                                            </button>
                                        </div>
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
                                <div className="input" style={{marginBottom: '30px'}}>
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
                                                    checked={prfcn === 'ADVANCED'}
                                                    onChange={() => prfcn('ADVANCED')}
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
                                                    checked={prfcn === 'MIDDLE'}
                                                    onChange={() => setProficiency('MIDDLE')}
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
                                                    checked={prfcn === 'BEGINNER'}
                                                    onChange={() => setProficiency('BEGINNER')}
                                                />
                                                <label htmlFor="basic" className="chip_item-label">
                                                    하
                                                </label>
                                            </li>
                                        </ul>
                                        <a className="change">※ 숙련도는 리그 매치 전에 1회에 한하여 <br/> 변경 가능합니다.</a>
                                        <button
                                            type="button"
                                            className="button2"
                                            onClick={handleEdit}>회원정보 저장
                                        </button>
                                        <button
                                            type="button"
                                            className="button2"
                                            onClick={handleDelete}>회원 탈퇴
                                        </button>
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