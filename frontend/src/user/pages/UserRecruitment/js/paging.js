$(document).ready(function() {
  // 더미 데이터 개수 확인
  var dummyDataCount = $("#postTableBody tr").length;
  var itemsPerPage = 10; // 페이지당 표시될 아이템 수
  var pageCount = Math.ceil(dummyDataCount / itemsPerPage); // 전체 페이지 수

  // 첫 페이지에만 게시글 보이기
  $("#postTableBody tr").hide();
  $("#postTableBody tr").slice(0, itemsPerPage).show();

  // 더미 데이터 개수가 10개 이상인 경우에만 페이징 처리
  if (dummyDataCount > itemsPerPage) {
    // 페이징 생성
    var pagination = "";

    for (var i = 1; i <= pageCount; i++) {
      pagination += '<li><a href="#" class="page-link">' + i + '</a></li>';
    }

    $("#pagingul").html(pagination);

    // 현재 페이지 표시를 위한 변수
    var currentPage = 1;

    // 페이지 이동 함수
    function goToPage(page) {
      // 페이지에 해당하는 아이템 표시
      var startIndex = (page - 1) * itemsPerPage;
      var endIndex = startIndex + itemsPerPage;

      $("#postTableBody tr").hide();
      $("#postTableBody tr").slice(startIndex, endIndex).show();

      // 현재 페이지 업데이트
      currentPage = page;
      updatePageStatus();
    }

    // 페이지 클릭 이벤트 처리
    $("#pagingul").on("click", ".page-link", function() {
      var clickedPage = parseInt($(this).text());
      goToPage(clickedPage);
    });


        // 맨 처음 페이지 이동
        function firstPage() {
            if (currentPage !== 1) {
                goToPage(1);
            }
        }

        // 이전 페이지 이동
        function previousPage() {
            if (currentPage > 1) {
                goToPage(currentPage - 1);
            }
        }

        // 다음 페이지 이동
        function nextPage() {
            if (currentPage < pageCount) {
                goToPage(currentPage + 1);
            }
        }

        // 맨 끝 페이지 이동
        function lastPage() {
            if (currentPage !== pageCount) {
                goToPage(pageCount);
            }
        }
        // 페이지 클릭 이벤트 처리
        $("#pagingul").on("click", ".page-link", function() {
            var clickedPage = parseInt($(this).text());
            goToPage(clickedPage);
        });



        // 페이지 상태 업데이트
        function updatePageStatus() {
            $("#pagingul li a").removeClass("active");
            $("#pagingul li a").eq(currentPage - 1).addClass("active");
        }

            // 맨 처음 버튼 클릭 이벤트 처리
            $("#pagingul").before('<button id="firstBtn" class="pagination-button">맨 처음</button>');
            $("#firstBtn").click(firstPage);

            // 이전 버튼 클릭 이벤트 처리
            $("#pagingul").before('<button id="prevBtn" class="pagination-button">이전</button>');
            $("#prevBtn").click(previousPage);

            // 맨 끝 버튼 클릭 이벤트 처리
            $("#pagingul").after('<button id="lastBtn" class="pagination-button">맨 끝</button>');
            $("#lastBtn").click(lastPage);

            // 다음 버튼 클릭 이벤트 처리
            $("#pagingul").after('<button id="nextBtn" class="pagination-button">다음</button>');
            $("#nextBtn").click(nextPage);

            // 초기 페이지 상태 업데이트
            updatePageStatus();
        }
        // 페이지 상태 업데이트
          function updatePageStatus() {
            $("#pagingul li a").removeClass("active");
            $("#pagingul li a").eq(currentPage - 1).addClass("active");
          }
    });