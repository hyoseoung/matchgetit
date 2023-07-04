import React, { useState,useEffect } from 'react';
import '../../styles/MatchingPage/Wait/waitingPage.css';
import '../../styles/MatchingPage/Application/friendlyMatching.css';
import axiosInstance from "../axiosInstance";
import '../../styles/CommonFactor/grade.css';

const MatchWait = ({ session, party,setParty,setIsParty,findMatch }) => {
    const [matchStatus, setMatchStatus] = useState('');
    const [partyData, setPartyData] = useState(null);
    const [matchAgreeData, setMatchAgreeData]= useState(null);
    const [matchAccept, setMatchAccept] = useState(false); // 추가된 부분
    const [agreeVisible, setAgreeVisible] = useState(true);
    useEffect(() => {
        findPartyData();
        findMatch();

        const interval = setInterval(() => {
            findMatch();
            handleRenew();

        }, 5000);

        return () => clearInterval(interval);
    }, []);

    const findPartyData = () => {
        axiosInstance
            .post('/matchGetIt/match/getParty', null, { params: { id: session.userId } })
            .then((response) => {
                if (response.data.length !== 0) {
                    console.log(response.data);
                    setPartyData(response.data);
                }
            })
            .catch((error) => {
                console.log('파티 없음 또는 서버오류');
            });
    }

    const handleRenew = () => {
        axiosInstance
            .post("/matchGetIt/match/renewMatchList", null, { params: { id: session.userId } })
            .then((response) => {
                console.log('데이터 옴');
                console.log(';');
                console.log(response.data);
                if(response.data.length>=1){
                    setMatchAgreeData(response.data);
                    setMatchAccept(true);
                }else{
                    setMatchAccept(false);
                }
            })
            .catch((error) => {
                console.log('실패');
            });
    };
    const handleAccept = () => {
        axiosInstance.post('/matchGetIt/match/matchAccept',null,{params:{id:session.userId}})
            .then(
                response=>{
                    console.log(response.data);
                    handleRenew();
                    setAgreeVisible(false);
                }
            ).catch(error=>{
        });
    };

    const handleReject = () => {
        axiosInstance.post('/matchGetIt/match/matchReject',null,{params:{id:session.userId}})
            .then(
                response=>{
                    console.log(response.data);
                    cancel();
                    handleRenew();
                }
            ).catch(error=>{
        });
    };


    if (party === null) {
        return (
            <>잘못된 접근입니다.</>
        );
    }

    // applicationTime에 따라 한글로 표현
    let applicationTimeText = '';
    switch (partyData?.applicationTime) {
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
    const cancel=()=>{
        axiosInstance.post("/matchGetIt/match/cancel",null,{
            params:{
                id:session.userId
            }
        }).then(response=>{
            // setMatchStatus('취소 성공');
            setMatchStatus('');
            setParty(null);
            setIsParty(false);
        }).catch(error=>{
            setMatchStatus('취소 실패');
            setTimeout(() => {
                setMatchStatus('');
            }, 3000);
        });
    }
    const renderMembers = () => {
        return party.map((item, index) => {
            let iconUrl = '';

            switch (item.prfcn) {
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
                    <img src={iconUrl} alt={item.prfcn} className={`icon ${item.prfcn}`} />
                    <div className={`matchTeamMember gradeMember ${item.prfcn}`}>
                        <div className="gradeName">{item.name}
                            {/*<div className="TeamMember">대기</div>/!*수락, 대기중, 거절*!/*/}
                            <p>{matchStatus}</p>
                        </div>
                    </div>
                </div>
            );
        });
    };

    return (
        <div className={`applicationContainer waitContainer ${partyData?.gameType}`}>
            {!matchAccept ? (
                    <div className="waitTitle">
                        ⚽ {session.name} 님 매칭 중 ⚽
                    </div>)
                :(<div className="waitTitleContainer">
                    <div className="waitTitle">{session.name}님 <br/>매칭을 수락해주세요!</div>
                    <div className="waitCurrent">현재 매칭 수락 인원 ({matchAgreeData.filter(m=>m.accept=='AGREE').length}/{matchAgreeData.length}{/*잡을 수 설정*/})
                    </div>
                </div>)
            }
            <div className="waitInformationContainer">
                <div className="waitInformationTitle">신청 지역</div>
                    <div className="waitTitleSub">{partyData?.address}</div>
                {matchAccept ?(
                    <>
                    {/*    <div className="waitInformationTitle">매칭 구장</div>*/}
                    {/*<div className="waitTitleSub">{partyData?.stadium}</div>*/}
                    </>)
                    :(null)
                }
                <div className="waitInformationTitle">경기 날짜</div>
                    <div className="waitTitleSub">{partyData?.applicationDate}</div>
                <div className="waitInformationTitle">경기 시간</div>
                    <div className="waitTitleSub">{applicationTimeText}</div>
                {matchAccept ?(
                        <>
                            <div className="waitInformationTitle">Rating Average</div>
                            <div className="waitTitleSub">200</div>{/* 매칭 그룹 으로 설정해도 됨*/}
                        </>)
                    :(null)
                }
                <ul>
                    {party.map((item, index) => (
                        <li key={index}>
                            <div className="TeamMemberTitle">파티원 정보</div>
                            <div className="TeamMember">{renderMembers()}</div>

                        </li>
                    ))}
                </ul>

            {/*<p>{matchStatus}</p>*/}
            {!matchAccept? (
                <button type="button" className="button" onClick={cancel}>
                    매칭 취소
                </button>
            ) : (
                agreeVisible && (
                    <div>
                        <button type="button" className="button waitAgreeBtn"  onClick={handleAccept}/*수락 메소드 기입*/>
                            수락
                        </button>
                        <button type="button" className="button waitRefusalBtn" onClick={handleReject}/*거절 메소드 기입*/>
                            거절
                        </button>
                    </div>
                )
            )}
            </div>
        </div>
    );
};

export default MatchWait;