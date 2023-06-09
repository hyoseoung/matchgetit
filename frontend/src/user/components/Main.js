import React, { useEffect, useRef, useState } from 'react';
import axios from 'axios';
import '../styles/swipePage.css';
import LogIn from './LogIn/LogIn';
import Match from './Match/Match';
import Mypage from "../pages/MyPage/Mypage";
import Swiper from 'swiper';
import 'swiper/swiper-bundle.css';
import '../styles/LogIn/logInButton.css';
import '../styles/CommonFactor/logInOutBtn.css';
import MenuBar from "./MenuBar";
import Chatbot from "./chatbot/chatbot"


function Main({ onLogin, onLogout, isLoggedIn }) {
    const [isLoginSlide, setIsLoginSlide] = useState(true);
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
                cubeEffect: {
                    slideShadows: false,
                    shadow: false,
                    shadowOffset: 20,
                    shadowScale: 0.94,
                },
                pagination: {
                    el: '.swiper-pagination',
                    clickable: true,
                },
                threshold: 70,
                slidesPerView: 'auto',
            });
        };

        initSwiper();

        const storedToken = sessionStorage.getItem('csrfToken');
        if (storedToken) {
            onLogin();
            setIsLoginSlide(false);
        }

        return () => {
            if (swiperRef.current) {
                swiperRef.current.destroy();
                swiperRef.current = null;
            }
        };
    }, []);

    const handleLogin = (email, password) => {
        axios
            .post('/matchGetIt/login', { email, password })
            .then((response) => {
                sessionStorage.setItem('csrfToken', response.data);
                onLogin();
                setIsLoginSlide(false);
                if (swiperRef.current) {
                    swiperRef.current.slideTo(0);
                    swiperRef.current.update();
                }
            })
            .catch((error) => {
                onLogout();
            });
    };

    const handleLogout = () => {
        axios
            .post('/matchGetIt/logout')
            .then(() => {
                sessionStorage.removeItem('csrfToken');
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

    const handleSlideChange = (index) => {
        if (swiperRef.current) {
            swiperRef.current.slideTo(index);
        }
    };

    return (
        <div>
            <div className="logInOutBtnArea">
                {isLoggedIn ? (
                    <button type="button" className="logInOutBtn" onClick={handleLogout}>
                        로그아웃
                    </button>
                ) : (
                    <></>
                )}
            </div>

            <div className="swiper-container">
                <div className="swiper-wrapper">
                    {!isLoggedIn || isLoginSlide ? (
                        <div className="swiper-slide">
                            <div className="slideContainer">
                                <div className="slide-page">
                                    <LogIn onLogin={handleLogin} />
                                </div>
                            </div>
                        </div>
                    ) : (
                        <>
                            <div className="swiper-slide">
                                <div className="slideContainer">
                                    <div className="slide-page">랭킹 게시판 페이지</div>
                                </div>
                            </div>
                            <div className="swiper-slide">
                                <div className="slideContainer">
                                    <div className="slide-page">
                                        <Match />
                                    </div>
                                </div>
                            </div>
                            <div className="swiper-slide">
                                <div className="slideContainer">
                                    <div className="slide-page"><Chatbot /></div>
                                </div>
                            </div>
                        </>
                    )}
                </div>
                <div className="swiper-pagination"></div>
            </div>
            <MenuBar isLoggedIn={isLoggedIn} onSlideChange={handleSlideChange} />
        </div>
    );
}

export default Main;
