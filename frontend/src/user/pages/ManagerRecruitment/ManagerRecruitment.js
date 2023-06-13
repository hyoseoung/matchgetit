import React, { useState } from 'react';
import Header from "../../components/Header.js";
import './css/ManagerRecruitment.css';
import Swiper from 'swiper';
import 'swiper/swiper-bundle.min.css';

function ManagerRecruitment() {
    const [showPopup, setShowPopup] = useState(false);

    const handleSubmit = (e) => {
        e.preventDefault();
        setShowPopup(true);
    };

  return (
    <div>
      <Header/>

      {/* 지원서 폼 */}
      <div className="ApplyManager">
        <form action="submit-form.php" method="POST">
          <div style={{ marginBottom: '15px' }}>
            <h1 className="form-title">지원 양식</h1>
          </div>
          <br />
          <br />
          <br />
          <div className="form-row">
            <label htmlFor="name">성함: </label>
            <input
              type="text"
              id="name"
              name="name"
              value="(로그인된유저 이름)"
              readOnly
            />
          </div>
          <br />
          <br />
          <div className="form-row">
            <label htmlFor="contact">연락처: </label>
            <input
              type="text"
              id="contact"
              name="contact"
              value="(로그인된 유저의 연락처)"
              readOnly
            />
          </div>
          <br />
          <br />
          <div className="form-row">
            <label htmlFor="gender">성별: </label>
            <input
              type="text"
              id="gender"
              name="gender"
              value="(로그인된 유저의 성별)"
              readOnly
            />
          </div>
          <br />
          <br />
          <div className="form-row">
            <label htmlFor="region">활동권역: </label>
            <select id="region" name="region">
              <option value="서울">서울</option>
              <option value="경기">경기</option>
              <option value="인천">인천</option>
              {/* 여기에 다른 지역 옵션 추가 */}
            </select>
            <br />
            <br />
            <br />
          </div>
          <div className="form-row form-row-button">
            <input type="submit" value="제출하기" />
          </div>
        </form>
      </div>

      {showPopup && (
          <div className="popup">
            <p>지원이 완료되었습니다. 합격자 한으로 추후에 개별 연락드리겠습니다. 감사합니다.</p>
          </div>
        )}
    </div>
  );
}

export default ManagerRecruitment;
