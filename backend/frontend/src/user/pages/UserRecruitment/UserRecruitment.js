import React, { useState } from 'react';
import Footer from "../../components/Footer";
import Header from "../../components/Header";
import './css/UserRecruitment.css';
import Swiper from 'swiper';
import 'swiper/swiper-bundle.min.css';

function UserRecruitment() {
  const [searchTerm, setSearchTerm] = useState('');
  const [tableData, setTableData] = useState([
    {
      type: "친선",
      date: "2023.06.01",
      location: "은평구 하늘경기장",
      players: 1,
    },
    {
      type: "리그",
      date: "2302.05.24",
      location: "은하수 배틀기우스",
      players: 3,
    },
    {
      type: "리그",
      date: "2514.08.03",
      location: "달 동무경기장",
      players: 2,
    },
    {
      type: "리그",
      date: "2514.08.03",
      location: "달 동무경기장",
      players: 2,
    },
    {
      type: "리그",
      date: "2514.08.03",
      location: "달 동무경기장",
      players: 2,
    },
    // ... 다른 데이터도 추가하세요
  ]);

  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
  };

  const handleSearchClick = () => {
    // 검색 버튼을 클릭했을 때의 동작을 구현합니다.
    // 검색 조건(searchTerm)을 이용하여 데이터를 검색하고, 검색된 결과를 tableData 상태로 설정합니다.
    const filteredData = tableData.filter((data) => {
      const { type, date, location } = data;
      // 검색 조건에 따라 필터링합니다.
      const searchValue = searchTerm.toLowerCase().trim();
      return (
        type.toLowerCase().includes(searchValue) ||
        date.toLowerCase().includes(searchValue) ||
        location.toLowerCase().includes(searchValue)
      );
    });

    setTableData(filteredData);
  };

  return (
    <div>
      <Header/>
      <div className="bulletin-board">
        <h1>인원 급구 게시판</h1>
        <div className="search-container">
          <label htmlFor="searchCondition">검색 조건: </label>
          <select name="searchCondition" id="searchCondition">
            <option value="all">전체</option>
            <option value="league">리그</option>
            <option value="friendly">친선</option>
          </select>
          <label htmlFor="checkStartDate">경기 날짜</label>
          <input type="date" id="checkStartDate" />
        </div>
        <br />
        <div className="search-container">
          <label htmlFor="searchLocation">경기 장소</label>
          <input
            type="text"
            name="searchLocation"
            id="searchLocation"
            value={searchTerm}
            onChange={handleSearchChange}
          />
          <button onClick={handleSearchClick}>검색</button>
        </div>
        <br />
        <table>
          <caption></caption>
          <thead>
            <tr>
              <th>경기 종류</th>
              <th>경기 날짜</th>
              <th>경기 장소</th>
              <th>구인 인원</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {tableData.map((data, index) => (
              <tr key={index}>
                <td>{data.type}</td>
                <td>{data.date}</td>
                <td>{data.location}</td>
                <td>{data.players}</td>
                <td>
                  <button style={{ marginLeft: '10px' }}>참가</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <Footer />
        <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="../../styles/js/CommonFactor/header.js"></script>
        <script src="../../styles/js/swiperPage.js"></script>
        <script src="../../styles/js/subPage.js"></script>
        <script src="../../styles/js/LogIn/signUp.js"></script>
      </div>
    </div>
  );
}

export default UserRecruitment;
