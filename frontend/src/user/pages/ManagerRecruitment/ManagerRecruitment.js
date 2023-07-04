import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './ManagerRecruitment.css';

function ManagerRecruitment({session}) {
    const [showPopup, setShowPopup] = useState(false);
    const [activityZone, setActivityZone] = useState(""); // 활동권역 상태값

    const [showActivityZonePopup, setShowActivityZonePopup] = useState(false); // 활동권역 선택 안내 팝업 상태값
    console.log(session);
    const [userName, setUserName] = useState(session.name);
    const [phoneNumber, setPhoneNumber] = useState(session.pn);
    const [gender, setGender] = useState(session.gender);

    // 사용자 정보 및 매니저 지원 상태 업데이트 함수
    const updateUserAndSubmitForm = async () => {
        if (activityZone === "") {
            setShowActivityZonePopup(true); // 활동권역 선택 안내 팝업 표시
            return;
        }

        try {
            // 매니저 지원 양식 제출 API 호출
            await axios.post(`/matchGetIt/manager/submitForm/${session.userId}`, { activityZone });

            setShowPopup(true); // 지원 완료 팝업 표시
        } catch (error) {
            console.error(error);
        }
    };



    return (
        <div>
            {/* 지원서 폼 */}
            <div className="ApplyManager">
                <form onSubmit={updateUserAndSubmitForm}>
                    <div style={{ marginBottom: '15px' }}>
                        <h3 className="form-title">지원 양식</h3>
                    </div>

                    <div className="form-row">
                        <label htmlFor="name">성함: </label>
                        <input
                            type="text"
                            id="name"
                            name="name"
                            value={userName}
                            readOnly
                        />
                    </div>
                    <div className="form-row">
                        <label htmlFor="pn">연락처: </label>
                        <input
                            type="text"
                            id="pn"
                            name="pn"
                            value={phoneNumber}
                            readOnly
                        />
                    </div>
                    <div className="form-row">
                        <label htmlFor="gender">성별: </label>
                        <input
                            type="text"
                            id="gender"
                            name="gender"
                            value={gender}
                            readOnly
                        />
                    </div>

                        <br />
                    <div className="form-row">
                        <label htmlFor="region">활동권역: </label>
                        <select
                            id="region"
                            name="region"
                            value={activityZone}
                            onChange={(e) => setActivityZone(e.target.value)}
                        >
                            <option value="">선택하세요</option>
                            <option value="서울">서울</option>
                            <option value="경기">경기</option>
                            <option value="인천">인천</option>
                        </select>
                        <br />
                        <br />
                    </div>
                    <div className="form-row form-row-button">
                        <input type="button" value="제출하기" onClick={updateUserAndSubmitForm} />
                    </div>
                </form>
            </div>

            {/* 활동권역 선택 안내 팝업 */}
            {showActivityZonePopup && (
                <div className="popup">
                    <p>활동권역이 선택되지 않았습니다. 선택해주세요.</p>
                </div>
            )}

            {/* 지원 완료 팝업 */}
            {showPopup && (
                <div className="popup">
                    <p>지원이 완료되었습니다. 합격자 한으로 추후에 개별 연락드리겠습니다. 감사합니다.</p>
                </div>
            )}
        </div>
    );
}

export default ManagerRecruitment;
