import React, { useEffect, useState } from 'react';

function Paging({ tableData }) {
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [visiblePages, setVisiblePages] = useState([]);

  useEffect(() => {
    // 총 페이지 수를 계산하고 설정합니다.
    const totalItems = tableData.length; // 테이블 데이터의 총 개수
    const itemsPerPage = 10; // 페이지 당 표시할 아이템 수
    const totalPages = Math.ceil(totalItems / itemsPerPage);
    setTotalPages(totalPages);

    // 보여줄 페이지 번호를 계산하고 설정합니다.
    const visiblePages = calculateVisiblePages(currentPage, totalPages);
    setVisiblePages(visiblePages);
  }, [tableData, currentPage]);

  const calculateVisiblePages = (currentPage, totalPages) => {
    const visiblePageCount = 5; // 보여줄 페이지 번호 개수
    const halfVisibleCount = Math.floor(visiblePageCount / 2);
    let startPage = currentPage - halfVisibleCount;
    let endPage = currentPage + halfVisibleCount;

    if (startPage <= 0) {
      startPage = 1;
      endPage = visiblePageCount;
    }

    if (endPage > totalPages) {
      endPage = totalPages;
      startPage = totalPages - visiblePageCount + 1;
      if (startPage <= 0) {
        startPage = 1;
      }
    }

    return Array.from({ length: endPage - startPage + 1 }, (_, i) => startPage + i);
  };

  const handlePageClick = (page) => {
    setCurrentPage(page);
  };

  return (
    <div className="paging">
      {visiblePages.map((page) => (
        <button
          key={page}
          className={page === currentPage ? 'active' : ''}
          onClick={() => handlePageClick(page)}
        >
          {page}
        </button>
      ))}
    </div>
  );
}

export default Paging;
