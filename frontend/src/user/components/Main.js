import React, { useEffect, useRef, useState } from 'react';
import axiosInstance from './axiosInstance';
import '../styles/swipePage.css';
import LogIn from './LogIn/LogIn';
import Match from './Match/Match';
import Mypage from "../pages/MyPage/Mypage";
import Swiper from 'swiper';
import 'swiper/swiper-bundle.css';
import '../styles/LogIn/logInButton.css';
import '../styles/CommonFactor/logInOutBtn.css';
import MenuBar from "./MenuBar";
import Rank from '../components/Rank/RankingBoard';
import Party from "./Party/Party";
import Chatbot from "../components/chatbot/chatbot"

function Main({ onLogin, onLogout, isLoggedIn }) {
    const [isLoginSlide, setIsLoginSlide] = useState(true);
    const [status, setStatus] = useState('');
    const [session, setSession] = useState('');
    const [partyData, setPartyData] = useState(null);
    const [isPartyMenuOpen, setIsPartyMenuOpen] = useState(false); // 추가된 부분
    const errorTimerRef = useRef(null);
    const swiperRef = useRef(null);

    useEffect(() => {
        const videoElement = document.createElement('video');
        videoElement.src = process.env.PUBLIC_URL + '/background/soccerBackground.mp4';
        videoElement.loop = true;
        videoElement.muted = true;
        videoElement.autoplay = true;
        videoElement.style.objectFit = 'cover';

        const container = document.querySelector('.swiper-container');
        container.appendChild(videoElement);

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
        renewPartyData();

        const storedToken = sessionStorage.getItem('X-XSRF-TOKEN');
        if (isLoggedIn && storedToken) {
            setIsLoginSlide(false);
        } else {
            getSession();
        }
        initSwiper();


        return () => {

            if (swiperRef.current) {
                swiperRef.current.destroy();
                swiperRef.current = null;
            }
        };
    }, [onLogin, onLogout, isLoggedIn]);

    const renewPartyData = () =>{
        if(session.userId!=null){
            axiosInstance.post("/matchGetIt/party/renewInviteData",null,{params: {id:session.userId}})
            .then((response)=>{
                console.log(response.data);
                setPartyData(response.data);
            }).catch((error) => {
                console.log('파티 초대 없음');
        });
        }
    }

    const getSession = () => {
        axiosInstance.post('/matchGetIt/auth/session')
            .then(response => {
                if (response.status === 200) {
                    const csrfToken = response.headers['x-xsrf-token'];
                    axiosInstance.defaults.headers['X-XSRF-TOKEN'] = csrfToken;
                    onLogin();
                    setIsLoginSlide(false);
                    console.log(response.data);
                    setSession(response.data);
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
                sessionStorage.setItem('X-XSRF-TOKEN', response.headers['x-xsrf-token']);
                console.log(response.data);
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
                    startErrorTimer();
                } else {
                    onLogout();
                }
            });
    };

    const startErrorTimer = () => {
        clearTimeout(errorTimerRef.current);

        setStatus('로그인 정보가 올바르지 않습니다.');
        errorTimerRef.current = setTimeout(() => {
            setStatus('');
        }, 3000);
    };

    const handleLogout = () => {
        axiosInstance
            .post('/matchGetIt/auth/logout')
            .then(() => {
                sessionStorage.removeItem('X-XSRF-TOKEN');
                onLogout();
                setIsLoginSlide(true);
                if (swiperRef.current) {
                    swiperRef.current.slideTo(0);
                    swiperRef.current.update();
                }
                window.location.reload();
            })
            .catch((error) => {
                // handle error
            });
    };
    const findMembers = () => {
        axiosInstance
            .post('/matchGetIt/match/getMember', null, { params: { id: session.userId} })
            .then((response) => {
                console.log(response.data);
            })
            .catch((error) => {
                console.log('파티 없음');
            });
    }

    const handleSlideChange = (index) => {
        if (swiperRef.current) {
            swiperRef.current.slideTo(index);
        }
    };

    const handlePartyMenuToggle = () => {
        setIsPartyMenuOpen(!isPartyMenuOpen);
    };

    return (
        <>
            <div className="logInOutBtnArea">
                {isLoggedIn ? (
                    <>
                        <span>{session.userId} : {session.name}</span>
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
                {isPartyMenuOpen && <Party session={session} partyData={partyData} isPartyMenuOpen={isPartyMenuOpen} setIsPartyMenuOpen={setIsPartyMenuOpen} setPartyData={setPartyData}/>}
                <div className="swiper-wrapper">
                    {!isLoggedIn || isLoginSlide ? (
                        <div className="swiper-slide">
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
                                    <div className="slide-page"><Rank/></div>
                                </div>
                            </div>
                            <div className="swiper-slide">
                                <div className="slideContainer">
                                    <div className="slide-page">
                                        <Match session={session}/>
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
            <MenuBar isLoggedIn={isLoggedIn} onSlideChange={handleSlideChange} />
        </>
    );
}

export default Main;
