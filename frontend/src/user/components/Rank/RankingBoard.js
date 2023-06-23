import React, {useEffect, useState} from 'react';
import '../../styles/RankingBoard.css';


function RankingBoard (){
    const [activeTab, setActiveTab] = useState(0);


    /*참고
    (function() {
        var tableHeaders = document.getElementsByClassName("c-table__header");
        var tableCells = document.getElementsByClassName("c-table__cell");
        var span = document.createElement("span");

        for (var i = 0; i < tableCells.length; i++) {
            span = document.createElement("span");
            span.classList.add("c-table__label");
            tableCells[i].prepend(span);
        }

        var tableLabels = tableHeaders[0].getElementsByClassName("c-table__col-label");
        var spanMod = document.getElementsByClassName("c-table__label");

        for (var i = 0; i < tableLabels.length; i++) {
            for (var a = 0; a < tableCells.length; a++) {
                spanMod[a].innerHTML = tableLabels[i].innerHTML;
            }
        }

        var b = tableLabels.length;
        for (var a = 0; a < tableCells.length; a++) {
            spanMod[a].innerHTML = tableLabels[a%b].innerHTML;
        }
    })();
    */




    //레벨별 랭킹보기
    useEffect(() => {
        const tabMenu = document.querySelector('.tab-menu');
        const tabs = tabMenu.querySelectorAll('li');

        const showTabContent = (index) => {
            const tabContents = tabMenu.querySelectorAll('.rank_table');
            tabContents.forEach((content, i) => {
                if (i === index) {
                    content.style.display = 'table';
                } else {
                    content.style.display = 'none';
                }
            });
        };
        tabs.forEach((tab, index) => {
            tab.addEventListener('click', (e) => {
                e.preventDefault();
                setActiveTab(index);
                showTabContent(index);
            });
        });
        showTabContent(activeTab);
    }, [activeTab]);


    return (
        <div className="matchBoard_outline">
            <div className="boardName">
                <h6>Ranking Board</h6>
            </div>
            <div className="please">
            <div className="rank_table_wrap">
                <div className="ranking_menu">
                <ul>
                    <li className={activeTab === 0 ? 'active' : ''}>
                        <a href="#" onClick={() => setActiveTab(0)} className="Ranking_ALL">ALL</a>
                    </li>
                    <li className={activeTab === 1 ? 'active' : ''}>
                        <a href="#" onClick={() => setActiveTab(1)} className="Ranking_A">A</a>
                    </li>
                    <li className={activeTab === 2 ? 'active' : ''}>
                        <a href="#" onClick={() => setActiveTab(2)} className="Ranking_B">B</a>
                    </li>
                    <li className={activeTab === 3 ? 'active' : ''}>
                        <a href="#" onClick={() => setActiveTab(3)} className="Ranking_C">C</a>
                    </li>
                    <li className={activeTab === 4 ? 'active' : ''}>
                        <a href="#" onClick={() => setActiveTab(4)} className="Ranking_D">D</a>
                    </li>
                </ul>
                </div>
                <table className="rank_myRank">
                    <caption></caption>
                    <thead>
                        <tr className="rank_top">
                            <th>등급</th>
                            <th>순위</th>
                            <th>이름</th>
                            <th>포인트</th>
                            <th>승률</th>
                            <th>최근 10경기</th>
                        </tr>
                    </thead>
                    <tbody>
                    <tr className="rank01">
                        <td>
                            <p className="ranking_grade">
                                D
                            </p>
                        </td>
                        <td>25</td>
                        <td>이효성</td>
                        <td>4000</td>
                        <td>80%</td>
                        <td>10승 0패</td>
                    </tr>
                    </tbody>
                </table>

                <div className="tab-menu">

                        <table className="rank_table" >
                            <caption></caption>
                            <thead className="rank_topBar">
                            <tr>
                                <th>등급</th>
                                <th>순위</th>
                                <th>이름</th>
                                <th>포인트</th>
                                <th>승률</th>
                                <th>최근 10경기</th>
                            </tr>
                            </thead>
                            <tbody className="rank_tbody">
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        E
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        E
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        E
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        E
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        E
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        E
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        E
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        E
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        E
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        E
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        E
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        E
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        E
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>
                            </tbody>
                        </table>

                        <table className="rank_table">
                            <caption></caption>
                            <thead>
                                <tr>
                                    <th>등급</th>
                                    <th>순위</th>
                                    <th>이름</th>
                                    <th>포인트</th>
                                    <th>승률</th>
                                    <th>최근 10경기</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr className="rank01">
                                    <td>
                                        <p className="ranking_grade">
                                            A
                                        </p>
                                    </td>
                                    <td>1</td>
                                    <td>공승환</td>
                                    <td>5000</td>
                                    <td>90%</td>
                                    <td>10승 0패</td>
                                </tr>
                                <tr className="rank01">
                                    <td>
                                        <p className="ranking_grade">
                                            A
                                        </p>
                                    </td>
                                    <td>1</td>
                                    <td>공승환</td>
                                    <td>5000</td>
                                    <td>90%</td>
                                    <td>10승 0패</td>
                                </tr>
                                <tr className="rank01">
                                    <td>
                                        <p className="ranking_grade">
                                            A
                                        </p>
                                    </td>
                                    <td>1</td>
                                    <td>공승환</td>
                                    <td>5000</td>
                                    <td>90%</td>
                                    <td>10승 0패</td>
                                </tr>
                                <tr className="rank01">
                                    <td>
                                        <p className="ranking_grade">
                                            A
                                        </p>
                                    </td>
                                    <td>1</td>
                                    <td>공승환</td>
                                    <td>5000</td>
                                    <td>90%</td>
                                    <td>10승 0패</td>
                                </tr>

                            </tbody>
                        </table>

                        <table className="rank_table" style={{ display: activeTab === 0 ? 'table' : 'none' }}>
                            <caption></caption>
                            <thead>
                            <tr>
                                <th>등급</th>
                                <th>순위</th>
                                <th>이름</th>
                                <th>포인트</th>
                                <th>승률</th>
                                <th>최근 10경기</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        B
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        B
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        B
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        B
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>

                            </tbody>
                        </table>

                        <table className="rank_table">
                            <caption></caption>
                            <thead>
                            <tr>
                                <th>등급</th>
                                <th>순위</th>
                                <th>이름</th>
                                <th>포인트</th>
                                <th>승률</th>
                                <th>최근 10경기</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        C
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        C
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        C
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        C
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>

                            </tbody>
                        </table>

                        <table className="rank_table">
                            <caption></caption>
                            <thead>
                            <tr>
                                <th>등급</th>
                                <th>순위</th>
                                <th>이름</th>
                                <th>포인트</th>
                                <th>승률</th>
                                <th>최근 10경기</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        D
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        D
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        D
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>
                            <tr className="rank01">
                                <td>
                                    <p className="ranking_grade">
                                        D
                                    </p>
                                </td>
                                <td>1</td>
                                <td>공승환</td>
                                <td>5000</td>
                                <td>90%</td>
                                <td>10승 0패</td>
                            </tr>
                            </tbody>
                        </table>
                </div>
            </div>
            </div>
        </div>
    );
};

export default RankingBoard;