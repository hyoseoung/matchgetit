import React from 'react';

function Pagination({ totalItems, itemsPerPage, currentPage, onPageChange }) {
  const totalPages = Math.ceil(totalItems / itemsPerPage);

  const handlePageChange = (page) => {
    if (page < 1 || page > totalPages) {
      return;
    }
    onPageChange(page);
  };

  const renderPageNumbers = () => {
    const pageNumbers = [];
    for (let i = 1; i <= totalPages; i++) {
      pageNumbers.push(
        <button
          key={i}
          className={i === currentPage ? 'active' : ''}
          onClick={() => handlePageChange(i)}
        >
          {i}
        </button>
      );
    }
    return pageNumbers;
  };

  const renderPaginationButtons = () => {
    return (
      <div className="pagination-buttons">
        <button
          className="first-page"
          disabled={currentPage === 1}
          onClick={() => handlePageChange(1)}
        >
          맨 처음
        </button>
        <button
          className="prev-page"
          disabled={currentPage === 1}
          onClick={() => handlePageChange(currentPage - 1)}
        >
          이전
        </button>
        {renderPageNumbers()}
        <button
          className="next-page"
          disabled={currentPage === totalPages}
          onClick={() => handlePageChange(currentPage + 1)}
        >
          다음
        </button>
        <button
          className="last-page"
          disabled={currentPage === totalPages}
          onClick={() => handlePageChange(totalPages)}
        >
          맨 끝
        </button>
      </div>
    );
  };

  return (
    <div className="pagination">
      {renderPaginationButtons()}
    </div>
  );
}

export default Pagination;
