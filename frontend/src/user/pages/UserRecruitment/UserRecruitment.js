import React, { useState, useEffect } from 'react';

import Header from "../../components/Header.js";
import './css/UserRecruitment.css';

import Pagination from './js/Pagination';

function UserRecruitment() {
    const [searchTerm, setSearchTerm] = useState('');
    const [searchCondition, setSearchCondition] = useState('all');
    const [selectedDate, setSelectedDate] = useState('');
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



    ]);

    const [currentPage, setCurrentPage] = useState(1); // 현재 페이지 번호
    const [itemsPerPage, setItemsPerPage] = useState(10); // 페이지당 아이템 수


    const handleSearchChange = (e) => {
        setSearchTerm(e.target.value);
    };

    const handleSearchConditionChange = (e) => {
        setSearchCondition(e.target.value);
    };

    const handleDateChange = (e) => {
        const selectedValue = e.target.value;
        setSelectedDate(selectedValue);
    };

    const handleResetClick = () => {
        setSearchTerm('');
        setSearchCondition('all');
        setSelectedDate('');
    };

    useEffect(() => {
        // 데이터 가져오는 비동기 함수 호출
        const fetchData = async () => {
            try {
            const response = await fetch('https://api.example.com/data');
            const data = await response.json();
            setTableData(data);
            } catch (error) {
            console.error('Error fetching data:', error);
            }
        };
        fetchData();
    }, []);

    useEffect(() => {
        // 날짜 선택 시 데이터 필터링
        const selectedDateFormatted = selectedDate.replaceAll('-', '.'); // 선택한 날짜 형식을 변경

        const filteredDataByDate = tableData.filter((data) =>
            selectedDate ? data.date === selectedDateFormatted : true
        );
        setTableData(filteredDataByDate);
    }, [selectedDate]);


    // 페이지 변경 시 실행되는 콜백 함수
        const handlePageChange = (pageNumber) => {
        setCurrentPage(pageNumber);
    };

    // 현재 페이지의 아이템을 가져옵니다.
    const getCurrentItems = () => {
        let filteredData = tableData;

        if (searchCondition === 'league') {
            filteredData = filteredData.filter((data) => data.type === '리그');
            } else if (searchCondition === 'friendly') {
            filteredData = filteredData.filter((data) => data.type === '친선');
            }

        const filteredAndSearchedData = filteredData.filter((data) => {
            if (searchTerm === '') {
                return true;
            } else {
            return data.location.toLowerCase().includes(searchTerm.toLowerCase());
            }
        });


        const startIndex = (currentPage - 1) * itemsPerPage;
        const endIndex = startIndex + itemsPerPage;
        return filteredAndSearchedData.slice(startIndex, endIndex);
    };

    const totalItems = tableData.length;
    const totalPages = Math.ceil(totalItems / itemsPerPage);
    const currentItems = getCurrentItems();


    return (
        <div>
        <Header/>
        <div className="bulletin-board">
        <h1>인원 급구 게시판</h1>


        <div className="search-container">
        <label htmlFor="searchCondition">검색 조건: </label>
        <select name="searchCondition"
            id="searchCondition"
            value={searchCondition}
            onChange={handleSearchConditionChange}>
            <option value="all">전체</option>
            <option value="league">리그</option>
            <option value="friendly">친선</option>
        </select>

        <label htmlFor="datePicker">경기 날짜</label>
        <input type="date"
        id="datePicker"
        value={selectedDate}
        onChange={handleDateChange} />
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

        <button onClick={handleResetClick}>초기화</button>
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
                {currentItems.map((data, index) => (
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


        <Pagination
        totalItems={totalItems}
        itemsPerPage={itemsPerPage}
        currentPage={currentPage}
        onPageChange={handlePageChange}
        />

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