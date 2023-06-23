import React from 'react';
import '../../../styles/LogIn/socialButton.css';

const SocialLogIn = () => {
    const handleNaverButtonClick = () => {
        const clientId = 'QMzZCB86eMc75Lc6X1y7';
        const redirectUri = 'http://localhost:8081/matchGetIt/naver'; // 콜백 주소를 적절히 수정해주세요

        const authorizeUrl = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${clientId}&redirect_uri=${encodeURIComponent(
            redirectUri
        )}&state=state`;
        window.location.href = authorizeUrl;
    };

    return (
        <>
            <button className="socialButton kakaoButton">kakao</button>
            <button className="socialButton googleButton">google</button>
            <button className="socialButton naverButton" onClick={handleNaverButtonClick}>
                Naver
            </button>
        </>
    );
};

export default SocialLogIn;
