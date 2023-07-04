import React from 'react';
import '../../styles/MatchingPage/MatchingResult/matchingResult.css';
import '../../styles/CommonFactor/grade.css';
import axiosInstance from "../axiosInstance";

const MatchResult = ({ session, matchWaitData, setIsMatch, setMatchWaitData, party }) => {
    if (matchWaitData === null) {
        return (
            <>잘못된 접근입니다.</>
        );
    }

    // applicationTime에 따라 한글로 표현
    let applicationTimeText = '';
    switch (matchWaitData[0].party?.applicationTime) {
        case 'A':
            applicationTimeText = '오전 10시 ~ 오후 12시';
            break;
        case 'B':
            applicationTimeText = '오후 12시 ~ 오후 2시';
            break;
        case 'C':
            applicationTimeText = '오후 2시 ~ 오후 4시';
            break;
        case 'D':
            applicationTimeText = '오후 4시 ~ 오후 6시';
            break;
        case 'E':
            applicationTimeText = '오후 6시 ~ 오후 8시';
            break;
        case 'F':
            applicationTimeText = '오후 8시 ~ 오후 10시';
            break;
        default:
            applicationTimeText = '';
            break;
    }

    const cancelMatch = () => {
        axiosInstance.post("/matchGetIt/match/cancelMatchWait", null, { params: { id: session.userId } })
            .then(response => {
                console.log('취소 성공');
                window.location.reload();
            }).catch(error => {
            console.log('취소 실패');
        });
    };

    const renderTeamMembers = (team) => {
        const members = matchWaitData.filter(m => m.team === team);
        return members.map((item, index) => {
            let iconUrl = '';

            switch (item.member.prfcn) {
                case 'BEGINNER':
                    iconUrl = process.env.PUBLIC_URL + '/images/beginner.png';
                    break;
                case 'MIDDLE':
                    iconUrl = process.env.PUBLIC_URL + '/images/middle.png';
                    break;
                case 'ADVANCED':
                    iconUrl = process.env.PUBLIC_URL + '/images/advanced.png';
                    break;
                case 'PROFESSIONAL':
                    iconUrl = process.env.PUBLIC_URL + '/images/professional.png';
                    break;
                default:
                    break;
            }

            return (
                <div key={index} className={`grade`}>
                    <img src={iconUrl} alt={item.member.prfcn} className={`icon ${item.member.prfcn}`} />
                    <div className={`matchTeamMember gradeMember ${item.member.prfcn}`}>
                        <div className="gradeName">{item.member.name}</div>
                    </div>
                </div>
            );
        });
    };

    return (
        <div className={`applicationContainer resultContainer ${matchWaitData[0].party.gameType}`}>
            <div className="resultStatiumimg">구장 이미지
                {/*<img src={matchWaitData.stadium.stdImgUrl} alt="Stadium" />*/}
            </div>
            <div className="resultInformationContainer">
            <div className="resultInformationTitle">경기날짜</div>
                <div className="resultTitleSub">{matchWaitData[0].party?.applicationDate}</div>
                <div className="resultInformationTitle">경기시간</div>
                <div className="resultTitleSub">{applicationTimeText}</div>

            <div className="resultInformationTitle">매칭구장</div>
                <div className="resultTitleSub">{matchWaitData[0].stadium.stdName}</div>
            <div className="lineUpTitle">LINE_UP</div>
                <div className="uniformContainer">
                    <div className="uniform">
                        <img src="images/uniformA.png" alt="Ateam" />
                    </div>
                    <div className="uniform">
                        <img src="images/uniformB.png" alt="Bteam" />
                    </div>
                </div>
                <div className="teamContainer">
                    <div className="aTeam">
                        <div className="aTeam resultMember">
                            <ul>{renderTeamMembers("A")}</ul>
                        </div>
                    </div>
                    <div className="bTeam">
                        <div className="bTeam resultMember">
                            <ul>{renderTeamMembers("B")}</ul>
                        </div>
                    </div>
                </div>
                <button type="button" className="button cancelButton" onClick={cancelMatch}>경기 취소</button>

        </div>
        </div>
    );
};

export default MatchResult;
