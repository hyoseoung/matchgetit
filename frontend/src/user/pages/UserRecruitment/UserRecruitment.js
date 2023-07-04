import React, { useState, useEffect } from 'react';
import Pagination from './js/Pagination';
import '../../styles/UserRecruitment.css';

function UserRecruitment() {
  const [tableData, setTableData] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [itemsPerPage, setItemsPerPage] = useState(10);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch('/matchGetIt/UserRecruitmentList');
        const data = await response.json();
        if (Array.isArray(data)) {
          setTableData(data);
        } else {
          setTableData([]);
        }
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
    fetchData();
  }, []);

  const getCurrentItems = () => {
    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    return tableData.slice(startIndex, endIndex);
  };

  const totalItems = tableData.length;
  const totalPages = Math.ceil(totalItems / itemsPerPage);
  const currentItems = getCurrentItems();

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  return (
    <div className="bulletin-board">
      <h1>인원 급구 게시판</h1>



      <table>
        <thead>
          <tr>
            <th>경기 종류</th>
            <th>경기 날짜</th>
            <th>경기 장소</th>
            <th>구인 인원</th>
            <th style={{ padding: '20px' }}></th>
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
    </div>
  );
}

export default UserRecruitment;
