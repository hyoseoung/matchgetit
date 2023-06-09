import React from 'react';
import '../styles/CommonFactor/menuBar.css';

function MenuBar({ isLoggedIn, onSlideChange }) {
    const handleSlideClick = (index) => {
        onSlideChange(index);
    };

    return (
        <div className="menu">
            {isLoggedIn ? (
                <>
                    <button className="link" onClick={() => handleSlideClick(0)}>
            <span className="link-icon">
              <img src={process.env.PUBLIC_URL + '/images/rankIcon.svg'} alt="rankIcon" />
            </span>
                        <span className="link-title">랭킹</span>
                    </button>
                    <button className="link" onClick={() => handleSlideClick(1)}>
            <span className="link-icon">
              <img src={process.env.PUBLIC_URL + '/images/BallIcon.svg'} alt="BallIcon" />
            </span>
                        <span className="link-title">매치</span>
                    </button>
                    <button className="link" onClick={() => handleSlideClick(2)}>
            <span className="link-icon">
              <img src={process.env.PUBLIC_URL + '/images/ProfileIcon.svg'} alt="ProfileIcon" />
            </span>
                        <span className="link-title">프로필</span>
                    </button>
                </>
            ) : (
                <button className="link">
          <span className="link-icon">
            <img src={process.env.PUBLIC_URL + '/images/loginIcon.svg'} alt="LoginIcon" />
          </span>
                    <span className="link-title">로그인 해</span>
                </button>
            )}
        </div>
    );
}

export default MenuBar;
