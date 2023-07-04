import React, { useEffect, useRef, useState } from 'react';
import axiosInstance from './axiosInstance';
import '../styles/swipePage.css';
import LogIn from './LogIn/LogIn';
import MatchApplication from './Match/MatchApplication';
import MatchWait from './Match/MatchWait';
import Mypage from "../pages/MyPage/Mypage";
import Swiper from 'swiper';
import 'swiper/swiper-bundle.css';
import '../styles/LogIn/logInButton.css';
import '../styles/CommonFactor/logInOutBtn.css';
import MenuBar from "./MenuBar";
import Rank from '../components/Rank/RankingBoard';
import PartyAccept from "./Party/PartyAccept";
import Chatbot from "../components/chatbot/chatbot";
import MatchResult from "./Match/MatchResult";

function Main({ onLogin, onLogout, isLoggedIn }) {
    const [isLoginSlide, setIsLoginSlide] = useState(true);
    const [status, setStatus] = useState('');
    const [session, setSession] = useState('');
    const [partyAcceptData, setPartyAcceptData] = useState(null);
    const [isPartyMenuOpen, setIsPartyMenuOpen] = useState(false); // 추가된 부분
    const [party,setParty] = useState(null);
    const [isParty,setIsParty]=useState(false);
    const [isMatch , setIsMatch] = useState(false);//matchWait에 있는지 없는지 판단하는 상태값
    const [matchWaitData, setMatchWaitData]= useState(null);
    const errorTimerRef = useRef(null);
    const swiperRef = useRef(null);
    const [token, setToken]= useState('');

    useEffect(() => {
        // const videoElement = document.createElement('video');
        // videoElement.src = process.env.PUBLIC_URL + '/background/soccerBackground.mp4';
        // videoElement.loop = true;
        // videoElement.muted = true;
        // videoElement.autoplay = true;
        // videoElement.style.objectFit = 'cover';
        //
        // const container = document.querySelector('.swiper-container');
        // container.appendChild(videoElement); 영상 돌리기

        const initSwiper = () => {
            swiperRef.current = new Swiper('.swiper-container', {
                effect: 'slide',
                pagination: {
                    el: '.swiper-pagination',
                    clickable: true,
                },
                threshold: 30,
                allowTouchMove: true,

            });
        };

        //스타디움 더미데이터 삽입 코드

        axiosInstance.post("/matchGetIt/auth/insert").then(response=>{
            console.log('성공');
        }).catch(error=>{
            console.log('삽입 실패');
        })

        if (isLoggedIn) {
            renewPartyAcceptData();
            findPartyMembers();
            findMatch();
        } else {
            setIsLoginSlide(false);
            if(isLoginSlide){
                getSession();
                getToken();
            }
        }
        initSwiper();

        console.log(token);
        return () => {

            if (swiperRef.current) {
                swiperRef.current.destroy();
                swiperRef.current = null;
            }
        };
    }, [onLogin, onLogout]);
    const findPartyMembers = () => {
        axiosInstance
            .post('/matchGetIt/match/getMember', null, { params: { id: session.userId} })
            .then((response) => {
                if(response.data.length!=0){
                    setParty(response.data);
                    console.log("파티 있음");
                    console.log(response.data);
                    setIsParty(true);
                }
            })
            .catch((error) => {
                console.log('파티 없음 또는 서버오류');
                setParty(false);
            });
    }
    const findMatch = () =>{
        axiosInstance.post("/matchGetIt/match/getMatchWaitList",null,{params: {id:session.userId}})
            .then(res=>{
                console.log('데이터 웨이트 옴');
                if(res!=null&&res.data.length>=1){
                    setIsMatch(true);
                    setMatchWaitData(res.data);
                    console.log(res.data);
                }else{
                    console.log('없음');
                }
            }).catch(error=>{
            console.log('오류!');
        })
    }

    const renewPartyAcceptData = () =>{
        if(session.userId!=null){
            axiosInstance.post("/matchGetIt/partyAccept/renewInviteData",null,{params: {id:session.userId}})
                .then((response)=>{
                    /*console.log("파티 초대 있음");
                    console.log(response.data);*/
                    setPartyAcceptData(response.data);
                }).catch((error) => {
                console.log('파티 초대 없음');
            });
        }
    }

    const getSession = () => {
        axiosInstance.post('/matchGetIt/auth/session',null,{params:{token:token}})
            .then(response => {
                if (response.data!=null) {
                    onLogin();
                    setIsLoginSlide(false);
                    setSession(response.data);
                } else {
                }
            })
            .catch(error => {
            });
    };
    const getToken = () => {
        axiosInstance.post('/matchGetIt/auth/token')
            .then(response => {
                if (response.data!=null) {
                    setToken(response.data);
                    sessionStorage.setItem("JwtToken",response.data);
                } else {
                }
            })
            .catch(error => {
            });
    };

    const handleLogin = (email, password) => {
        axiosInstance
            .post('/matchGetIt/auth/login', { email, password })
            .then((response) => {
                getSession();
                onLogin();
                setIsLoginSlide(false);
                if (swiperRef.current) {
                    swiperRef.current.slideTo(0);
                    swiperRef.current.update();
                }
            })
            .catch((error) => {
                if (error.response && error.response.status === 401) {
                    setStatus('로그인 정보가 올바르지 않습니다.');
                    errorTimerRef.current = setTimeout(() => {
                        setStatus('');
                    }, 3000);
                } else {
                    setStatus('서버 오류!');
                }
            });
    };

    const handleLogout = () => {
        axiosInstance
            .post('/matchGetIt/auth/logout')
            .then(() => {
                sessionStorage.removeItem('JwtToken');
                onLogout();
                setIsLoginSlide(true);
                if (swiperRef.current) {
                    swiperRef.current.slideTo(0);
                    swiperRef.current.update();
                }
                window.location.reload();
            })
            .catch((error) => {
                onLogout();
            });
    };

    const handleSlideChange = (index) => {
        if (swiperRef.current) {
            swiperRef.current.slideTo(index);
        }
    };

    const handlePartyMenuToggle = () => {
        setIsPartyMenuOpen(!isPartyMenuOpen);
    };
    const pay1000 = () =>{
        window.location.href='http://localhost:8081/matchGetIt/pay/payStart/1000';
    }


    return (
        <>
            <div className="logInOutBtnArea">
                {isLoggedIn ? (
                    <>
                        <span className="sessionIdArea">{session.userId} : {session.name}</span>
                        <span><button className="popUpBtn" onClick={handlePartyMenuToggle}>파티 메뉴</button></span>
                        <span>

                    <button type="button" className="logInOutBtn" onClick={handleLogout}>
                        로그아웃
                    </button>
                        </span>
                    </>
                ) : (
                    <></>
                )}
            </div>

            <div className="swiper-container">
                {isPartyMenuOpen && <PartyAccept session={session} partyAcceptData={partyAcceptData} isPartyMenuOpen={isPartyMenuOpen} setIsPartyMenuOpen={setIsPartyMenuOpen} setPartyAcceptData={setPartyAcceptData}/>}
                <div className="swiper-wrapper">
                    <img src="images/Loginback.jpeg" alt="배경 이미지" className="backgroundImg" />
                    {!isLoggedIn ? (
                        <div className="swiper-slide">
                            <img src="images/Loginback.jpeg" alt="배경 이미지" className="backgroundImg" />
                            <div className="slideContainer">
                                <div className="slide-page">
                                    <LogIn onLogin={handleLogin}/>
                                    <p className={`errorText ${status ? 'fade-in' : ''}`}>{status}</p>
                                </div>
                            </div>
                        </div>
                    ) : (
                        <>
                            <div className="swiper-slide">
                                <div className="slideContainer">
                                    <div className="slide-page"><Rank session={session}/></div>
                                </div>
                            </div>
                            <div className="swiper-slide">
                                <div className="slideContainer">
                                    <div className="slide-page">
                                        {isParty ?(
                                                isMatch?(
                                                    <MatchResult session={session} matchWaitData={matchWaitData} setIsMatch={setIsMatch}  setMatchWaitData={setMatchWaitData}/>
                                                ):(
                                                    <MatchWait session={session} party={party} setParty={setParty} setIsParty={setIsParty} findMatch={findMatch}/>
                                                )
                                            ):
                                            (<MatchApplication session={session} />)}
                                    </div>
                                </div>
                            </div>
                            <div className="swiper-slide">
                                <div className="slideContainer">
                                    <div className="slide-page"><Mypage session={session}/></div>
                                </div>
                            </div>
                        </>
                    )}
                </div>
                <div className="swiper-pagination"></div>
            </div>
            <Chatbot />
            {isLoggedIn ? <MenuBar isLoggedIn={isLoggedIn} onSlideChange={handleSlideChange} /> : null}


        </>
    );
}

export default Main;
