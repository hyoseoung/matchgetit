import React, { useState } from 'react';
import axiosInstance from '../axiosInstance';
import '../../styles/CommonFactor/popup.css';

const Party = ({ partyData, setPartyData, isPartyMenuOpen, setIsPartyMenuOpen, session }) => {
    const handleClose = () => {
        setIsPartyMenuOpen(false);
    };

    const handleRenew = () => {
        axiosInstance
            .post("/matchGetIt/party/renewInviteData", null, { params: { id: session.userId } })
            .then((response) => {
                setPartyData(response.data);
            })
            .catch((error) => {
                console.log('실패');
            });
    };

    const handleAccept = async (partyAcceptId) => {
        try {
            await axiosInstance.put(`/matchGetIt/party/accept/${partyAcceptId}`);
            // 성공 처리 또는 상태 업데이트
            handleRenew();
        } catch (error) {
            // 에러 처리
        }
    };

    const handleReject = async (partyAcceptId) => {
        try {
            await axiosInstance.put(`/matchGetIt/party/reject/${partyAcceptId}`);
            // 성공 처리 또는 상태 업데이트
            handleRenew();
        } catch (error) {
            // 에러 처리
        }
    };

    return (
        <div className={`popUp-box ${isPartyMenuOpen ? 'popUp-transform-in' : 'popUp-transform-out'}`}>
            <div className="popUpContainer">
                <div className="popUpTitle">파티 수락 리스트</div>
                {partyData && partyData.length > 0 ? (
                    <ul>
                        {partyData.map((item, index) => (
                            <li key={index}>
                                <div className="acceptArray">

                                <div className="popUpTitle">{item.partyLeaderId}의 초대</div>
                                {item.agreement === 'WAIT' ? (
                                    <div className="btnArray">
                                        <button className="agreeBtn" onClick={() => handleAccept(item.partyAcceptId)}>수락</button>
                                        <button className="agreeBtn" onClick={() => handleReject(item.partyAcceptId)}>거절</button>
                                    </div>
                                ) : (
                                    item.agreement === 'AGREE' ? (
                                        <div>수락 처리</div>
                                    ) : (
                                        <div>거절 처리</div>
                                    )
                                )}
                                </div>
                            </li>
                        ))}
                    </ul>
                ) : (
                    <p>파티 수락 리스트가 없습니다.</p>
                )}
            </div>
            <div className="popUpCloseBtn" onClick={handleClose}>
                <div className="inner">
                    <label>뒤로</label>
                </div>
            </div>
        </div>
    );
};

export default Party;
