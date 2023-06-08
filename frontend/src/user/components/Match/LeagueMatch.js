import React from 'react';
import '../../styles/MatchingPage/Application/applicationContainer.css';
import '../../styles/MatchingPage/Application/leagueMatching.css';
import '../../styles/CommonFactor/radioButton.css';

const LeagueMatch = () => {

  return (
    <>
      <div className="applicationContainer lContainer">
        <div className="matchingTitle lTitle">League Match</div>
        <div id="weather"></div>
        <form action="">
          <div className="address">
            <div className="lSubTitle subTitle addressTitle">선택한 지역</div>
            <input
              type="text"
              className="lInputAddress inputAddress"
              id="lInputAddress"
              value=''
              readOnly
              required
            />
            <input type="hidden" id="lInputLat"/>
            <input type="hidden" id="lInputLon"/>
            <button
              className="mBtn lButton addressBtn"
              type="button"
            >
              주소 검색
            </button>
          </div>
          <div className="time">
            <div className="lSubTitle subTitle timeTitle">선택 시간</div>
            <input type="date" className="timeSelect mDate"/>
            <select className="mTime">
              <option value="A">오전 10시~오후 12시</option>
              <option value="B">오후 12시~오후 2시</option>
              <option value="C">오후 2시~오후 4시</option>
              <option value="D">오후 4시~오후 6시</option>
              <option value="E">오후 6시~오후 8시</option>
              <option value="F">오후 8시~오후 10시</option>
            </select>
          </div>
          <div className="party">
            <div className="lSubTitle subTitle partyTitle">파티원 검색</div>
            <button className="mBtn lButton pBtn" type="button">
              파티원 검색
            </button>
          </div>
          <button className="mBtn lButton pointBtn" type="button">
            ???님 잔여 포인트
          </button>
          <button className="mBtn lButton mSubBtn" type="submit">
            매칭
          </button>
        </form>
      </div>
    </>
  );
};

export default LeagueMatch;
