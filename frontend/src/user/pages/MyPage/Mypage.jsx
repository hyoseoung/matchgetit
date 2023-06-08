import React, {useState} from "react";
import "./Mypage.css";
import axios from 'axios';
import Profile from "./Profile";
import CreditHistory from "../Payments/CreditCharge"
import CreditCharge from "../Payments/CreditCharge";

function Mypage() {
    const [isProfileOpen, setProfileOpen] = useState(false);

    const handleProfileToggle = () => {
        setProfileOpen(!isProfileOpen);
    };

    return (
        <div className="content-body_left-wrap">
            <section>
                <div style={{ display: 'flex', justifyContent: 'space-between' }}>
                    <div className="my-profile">
                        <div style={{ display: 'flex', alignItems: 'center' }}>
                            <h1 className="my-profile__name">이효성</h1>
                        </div>
                        <div className="my-account_type">
                            <p className="text-caption1">2752547203</p>
                            <span className="badge badge-kakao sm">KAKAO</span>
                        </div>
                    </div>
                </div>
                <div style={{ marginTop: '10px', display: 'none' }}>
                    <p className="my-profile__notice--friend">
                        <a href="/player/17AcO/friend/?tab=pending">0명의 친구 신청이 있어요</a>
                    </p>
                </div>
                <div className="my-status">
                    <li className="my-status_item my-status_item-double">
                        <a href="/mypage/mymanner/">
                            <div className="my-status_label">
                                <p style={{ fontSize: '12px' }}>매너</p>
                            </div>
                            <div className="my-status_content">
                                <img src="https://plab-football.s3.amazonaws.com/static/img/ic_manner_card.svg" alt="매너" />
                                좋아요
                            </div>
                        </a>
                    </li>
                    <li className="my-status_item my-status_item-double">
                        <div className="my-status_label">
                            <a href="/magazine/1/" style={{ fontSize: '12px' }}>레벨</a>
                            <img src="https://plab-football.s3.amazonaws.com/static/img/ic_level_show.svg" alt="레벨" />
                        </div>
                        <a href="/mypage/mylevel/">
                            <div className="my-status_content">
                                <div className="lv-system_title">
                                    <div>
                                        <span className="badge badge-rookie badge_lv-system">R</span>
                                    </div>
                                    <div>
                                        <div className="lv-system_name">
                                            <h4>루키</h4>
                                        </div>
                                    </div>
                                    <div>
                                        {/* Additional content */}
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li className="my-status_item my-status_item-cash">
                        <div>
                            <p style={{ fontSize: '17px', fontWeight: "bold"}}>나의 캐시</p>
                            <p style={{ fontSize: '20px', fontWeight: '700' }}>0원</p>
                        </div>
                        <a href="../Payments/CreditCharge.jsx">
                            <div className="my-cash">
                                <span>충전하기</span>
                            </div>
                        </a>
                    </li>
                </div>
                <div className="profile-view">x
                    <button className="btn sm gray" onClick={handleProfileToggle}>
                        <p>프로필 보기</p>
                    </button>
                </div>
                {isProfileOpen && (
                    <div className="profile-settings">
                        <Profile />
                    </div>
                )}
            </section>
            <section>
                <div>
                    <div className="my-content-title">my content</div>
                    <a href="/mypage/myplab/">
                        <div className="content-label"><img src="https://plab-football.s3.amazonaws.com/static/img/ic_myplab_color.svg" alt="신청 내역"/>
                            <p>신청 내역</p>
                        </div>
                    </a>
                    <a href="/cash/charge/history">
                        <div className="content-label"><img src="https://plab-football.s3.amazonaws.com/static/img/ic_point_color.svg" alt="결제 내역"/>
                            <p>결제 내역</p>
                        </div>
                    </a>

                </div>
            </section>
        </div>
    );
}


export default Mypage;