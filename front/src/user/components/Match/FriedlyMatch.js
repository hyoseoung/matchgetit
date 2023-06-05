import React from 'react';
import '../../styles/MatchingPage/Application/friendlyMatching.css';
import '../../styles/CommonFactor/radioButton.css';

function FriendlyMatch() {

  return (
    <div className="applicationContainer fContainer">
      <div className="matchingTitle fTitle">Friendly Match</div>
      <form action="">
        <div className="address">
          <div className="fSubTitle subTitle addressTitle">선택한 지역</div>
          <input
        type="text"
        className="fInputAddress inputAddress"
        value=''
        readOnly
        required
      />
      <input type="hidden" id="fInputLat" name="fx" value='' />
      <input type="hidden" id="fInputLon" name="fy" value='' />
      <button className="mBtn fButton addressBtn" type="button" onClick=''>
        주소 검색
      </button>
        </div>
        <div className="time">
          <div className="fSubTitle subTitle timeTitle">선택 시간</div>
          <input type="date" className="timeSelect mDate" name="fDate" value="" />
          <select className="mTime" name="fTime">
            <option value="A">오전 10시~오후 12시</option>
            <option value="B">오후 12시~오후 2시</option>
            <option value="C">오후 2시~오후 4시</option>
            <option value="D">오후 4시~오후 6시</option>
            <option value="E">오후 6시~오후 8시</option>
            <option value="F">오후 8시~오후 10시</option>
          </select>
        </div>
        <div className="party">
          <div className="fSubTitle subTitle partyTitle">파티원 검색</div>
          <button className="mBtn fButton pBtn" type="button">
            파티원 검색
          </button>
        </div>
        <button className="mBtn fButton pointBtn" type="button">
          ???님 잔여 포인트
        </button>
        <button className="mBtn fButton mSubBtn" type="submit">
          매칭
        </button>
      </form>
    </div>
  );
};

export default FriendlyMatch;