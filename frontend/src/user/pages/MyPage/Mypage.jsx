import React, {useState} from "react";
import "./Mypage.css";
import "./profile.css"
import "../Payments/CreditCharge.css"
import Profile from "./Profile";
import CreditHistory from "../Payments/CreditHistory";
import CreditCharge from "../Payments/CreditCharge";
import ApplyManager from "../ManagerRecruitment/ApplyManager"


function Mypage({session}) {
    const [isProfileOpen, setProfileOpen] = useState(false);
    const [isChargeOpen, setChargeOpen] = useState(true);
    // const [isAdmin, setIsAdmin] = useState(session && session.loginType.toUpperCase() === "ADMIN");
    const [isAdmin, setIsAdmin] = useState(true);
    const [isApplyManager, setApplyManager] = useState(false);
    const [isHistoryOpen, setHistoryOpen] = useState(false);

    const handleApplyManagerToggle = () => {
        setApplyManager(!isApplyManager);
    };

    const handleProfileToggle = () => {
        setProfileOpen(!isProfileOpen);
    };
    const handleChargeToggle = () => {
        setChargeOpen(!isChargeOpen);
    };

    const handleOpenadmin=(url)=>{
        window.open(url,"_blank","noopner,noreferrer");
    };
    const handleHistoryToggle = () => {
        setHistoryOpen(!isHistoryOpen);
    };

    return (
        <div className="content-body_left-wrap">
            <section>
                <div style={{ display: 'flex', justifyContent: 'space-between' }}>
                    <div className="my-profile">
                        <div style={{ display: 'flex', alignItems: 'center' }}>
                            <h1 className="my-profile__name">{session.name}</h1>
                        </div>
                        <div className="my-account_type">
                            <p className="text-caption1">2752547203</p>
                            <span className="badge badge-kakao sm">{session.accountType}</span>
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
                        {/*<a href="/mypage/mymanner/">*/}
                            <div className="my-status_label">
                                <p style={{ fontSize: '12px' }}>매너</p>
                            </div>
                            <div className="my-status_content">
                                <img src="https://plab-football.s3.amazonaws.com/static/img/ic_manner_card.svg" alt="매너" />
                                좋아요
                            </div>
                        {/*</a>*/}
                    </li>
                    <li className="my-status_item my-status_item-double">
                        <div className="my-status_label">
                            <a href="/magazine/1/" style={{ fontSize: '12px' }}>레벨</a>
                            <img src="https://plab-football.s3.amazonaws.com/static/img/ic_level_show.svg" alt="레벨" />
                        </div>
                        {/*<a href="/mypage/mylevel/">*/}
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
                        {/*</a>*/}
                    </li>
                </div>
                {isChargeOpen && (
                    <li className="my-status_item my-status_item-cash">
                        <div>
                            <p style={{ fontSize: '17px', fontWeight: "bold"}}>나의 캐시</p>
                            <p style={{ fontSize: '20px', fontWeight: '700' }}>0원</p>
                        </div>

                        <div className="my-cash">
                            <span onClick={handleChargeToggle}>충전하기</span>
                        </div>
                    </li>
                )}
                {!isChargeOpen && (
                    <li className="my-status CreditCharge-settings">
                        <div className="CreditCharge-settings">
                            <CreditCharge session={session} />
                            <div className="charge__back" onClick={handleChargeToggle}>
                                닫기
                            </div>
                        </div>
                    </li>
                )}

                <div className="profile-view">
                    <button className="btn sm gray" onClick={handleProfileToggle}>
                        <p>프로필 보기</p>
                    </button>
                </div>
                {isProfileOpen && (
                    <div className="profile-settings">
                        <Profile session={session}/>
                    </div>
                )}
                {/*<div className="profile-view">*/}
                {/*    <button className="btn sm gray" onClick={handleApplyManagerToggle}>*/}
                {/*        <p>매니저 지원하기</p>*/}
                {/*    </button>*/}
                {/*</div>*/}
                {/*{isApplyManager && (*/}
                {/*    <div className="ApplyManager">*/}
                {/*        <ApplyManager session={session}/>*/}
                {/*    </div>*/}
                {/*)}*/}

            </section>
            <section>
                <div>
                    <div className="my-content-title">my content</div>
                        <div className="content-label">
                            <img src="https://plab-football.s3.amazonaws.com/static/img/ic_myplab_color.svg" alt="신청 내역"/>
                            <p>신청 내역</p>
                        </div>
                        <div>
                            <a className="content-label" onClick={handleHistoryToggle}>
                                <img src="https://plab-football.s3.amazonaws.com/static/img/ic_point_color.svg" alt="결제 내역"/>
                                <p>결제 내역</p>
                            </a>
                            {isHistoryOpen && (
                                <div className="profile-settings">
                                    <CreditHistory session={session}/>
                                </div>
                            )}
                        </div>

                        <div>
                            <a className="content-label" onClick={handleApplyManagerToggle}>
                                <img src="https://plab-football.s3.amazonaws.com/static/img/explore_fire.svg" alt="결제 내역"/>
                                <p>매니저 지원하기</p>
                            </a>
                            {isApplyManager && (
                                <div className="ApplyManager">
                                    <ApplyManager session={session}/>
                                </div>
                            )}
                        </div>
                    {isAdmin && (
                        <a href="http://localhost:8081/matchGetIt/manager/managers">
                            <div className="content-label">
                                <img src="https://plab-football.s3.amazonaws.com/static/img/ic_myplab_color.svg" alt="신청 내역" />
                                <p>어드민 페이지 이동</p>
                            </div>
                        </a>
                    )}
                </div>
            </section>
        </div>
    );
}


export default Mypage;