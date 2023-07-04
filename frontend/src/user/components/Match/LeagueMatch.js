import React, {useEffect, useRef, useState} from 'react';
import '../../styles/MatchingPage/Application/applicationContainer.css';
import '../../styles/MatchingPage/Application/leagueMatching.css';
import '../../styles/CommonFactor/radioButton.css';
import axiosInstance from "../axiosInstance";
import AddressSearch from "./AddressSearch";

const LeagueMatch = ({ session }) => {
  const [party, setParty] = useState([]);
  const [submitStatus, setSubmitStatus] = useState('');
  const [searchStatus, setSearchStatus] = useState('');
  const [addressVisible, setAddressVisible] = useState(false);
  const [partyopenStatus, setpartyopenStatus] = useState(false);
  const [id, setId] = useState('');
  const [address, setAddress] = useState('');
  const [selectedDate, setSelectedDate] = useState('');
  const [selectedTime, setSelectedTime] = useState('');
  const [x,setX]=useState('');
  const [y,setY]=useState('');
  const datePickerRef = useRef(null);
  const [showTimePopup, setShowTimePopup] = useState(false);
  const toggleTimePopup = () => {
    setShowTimePopup((prevState) => !prevState);
  };
  const handleTimeSelection = (time) => {
    setSelectedTime(time);
    setShowTimePopup(false);
  };

  useEffect( ()=>{
    const today = new Date();
    today.setDate(today.getDate() + 3);
    const formattedDate = today.toISOString().substr(0, 10);

    datePickerRef.current.min = formattedDate;
  })

  const handllepartyopen = () => {
    setpartyopenStatus(!partyopenStatus)
  };
  const getTimeLabel = (time) => {
    switch (time) {
      case "A":
        return "A 타임 : 오전 10시~오후 12시";
      case "B":
        return "B 타임 : 오후 12시~오후 2시";
      case "C":
        return "C 타임 : 오후 2시~오후 4시";
      case "D":
        return "D 타임 : 오후 4시~오후 6시";
      case "E":
        return "E 타임 : 오후 6시~오후 8시";
      case "F":
        return "F 타임 : 오후 8시~오후 10시";
      default:
        return "시간 선택";
    }
  };

  useEffect( ()=>{
    const today = new Date();
    today.setDate(today.getDate() + 3);
    const formattedDate = today.toISOString().substr(0, 10);

    datePickerRef.current.min = formattedDate;
    console.log(party);
  })

  const handleInputChange = (event) => {
    setId(event.target.value);
  };

  const searchId = () => {
    if(session.userId!=id){
      axiosInstance
          .post('/matchGetIt/partyAccept/searchId', null, { params: { id: id, partyLeaderId:session.userId } })
          .then((response) => {
            const memberExists = party.find(
                (member) => member.userId === response.data.userId
            );
            console.log(response.data);
            if (memberExists) {
              setSearchStatus('이미 추가된 파티원입니다.');
            }else if (response.data == null) {
              setSearchStatus('존재하지 않는 계정입니다.');
            } else {
              setParty([...party, response.data]);
              setSearchStatus('파티 초대 성공(수락 필요)');
            }
            setTimeout(() => {
              setSearchStatus('');
            }, 3000);
          })
          .catch((error) => {
            setSearchStatus('없는 파티원이거나 권한이 없다');
          });
    }else{
      setSearchStatus('본인 아이디는 추가 못함');
      setTimeout(() => {
        setSearchStatus('');
      }, 3000);
    }
  };
  const renewData= ()=>{
    axiosInstance
        .post('/matchGetIt/partyAccept/renewPartyAcceptData', null, { params: {partyLeaderId:session.userId}})
        .then((response) => {
          setParty(response.data);
        }).catch((error) => {
      setSearchStatus('갱신 실패');
      setTimeout(() => {
        setSearchStatus('');
      }, 3000);
    });

  }

  const submitMatch = () => {
    const agreement = party.filter(member=>member.agreement!='AGREE');
    if(agreement.length==0){
      if(address&&selectedDate&&x&&y){

        const partyMembers = party.map((member) => ({ id: member.user.userId }));
        const requestData = {
          partyLeader: session.userId,
          address: address,
          x: x,
          y: y,
          selectedDate: selectedDate,
          selectedTime: selectedTime,
          party: partyMembers,
          gameType:'LEAGUE'
        };

        axiosInstance
            .post('/matchGetIt/match/start', requestData)
            .then((response) => {
              window.location.reload();
              setSubmitStatus('성공');
              setTimeout(() => {
                setSubmitStatus('');
              }, 3000);
              console.log('성공');
            })
            .catch((error) => {
              if (error.response && error.response.status === 400) {
                setSubmitStatus(error.response.data);
              } else {
                setSubmitStatus('매칭 실패');
              }
              setTimeout(() => {
                setSubmitStatus('');
              }, 3000);
            });
      }else{
        setSubmitStatus('입력하지 않은 정보가 존재(재확인)');
        setTimeout(() => {
          setSubmitStatus('');
        }, 3000);
      }
    }else{
      setSubmitStatus('수락하지 않은 파티원이 있습니다');
      setTimeout(() => {
        setSubmitStatus('');
      }, 3000);
    }
  };
  const onSelectAddress = (address, x, y) => {
    setAddress(address);
    setX(x);
    setY(y);
    console.log(address+'위도:'+x+'경도:'+y);
  };

  const deleteId = (index) => {
    const updatedParty = [...party];
    updatedParty.splice(index, 1);
    setParty(updatedParty);
  };


  return (
      <>
        <div className="applicationContainer lContainer">
          <form>
            <div className="matchingTitle lTitle">리 그 경 기</div>
            <div className="address">
              <div className="lSubTitle subTitle addressTitle"></div>
              <input
                  type="text"
                  className="lInputData inputData"
                  readOnly
                  required
                  value={address}
                  onClick={() => setAddressVisible(true)}
                  placeholder="주소 검색"
              />
              <AddressSearch visible={addressVisible} setVisible={setAddressVisible} onSelect={onSelectAddress} />
            </div>
            <div className="time">
              <input
                  type="date"
                  className="timeSelect mDate"
                  value={selectedDate}
                  onChange={(event) => setSelectedDate(event.target.value)}
                  ref={datePickerRef}
              />
              <input
                  type="text"
                  placeholder="시간 선택"
                  onClick={toggleTimePopup}
                  className="mTime"
                  value={selectedTime ? getTimeLabel(selectedTime) : ""}
                  onChange={(event) => setSelectedTime(event.target.value)}

              />
              {showTimePopup && (
                  <div className="timepopUp-box timeselectContainer">
                    <div className="closeButton" onClick={toggleTimePopup}>x</div>
                    <div onClick={() => handleTimeSelection("A")}>A타임 : 오전 10시~오후 12시</div>
                    <div onClick={() => handleTimeSelection("B")}>B타임 : 오후 12시~오후 2시</div>
                    <div onClick={() => handleTimeSelection("C")}>C타임 : 오후 2시~오후 4시</div>
                    <div onClick={() => handleTimeSelection("D")}>D타임 : 오후 4시~오후 6시</div>
                    <div onClick={() => handleTimeSelection("E")}>E타임 : 오후 6시~오후 8시</div>
                    <div onClick={() => handleTimeSelection("F")}>F타임 : 오후 8시~오후 10시</div>
                  </div>
              )}
            </div>
            <div className="party">
              <button className="mBtn lButton pBtn" type="button" onClick={handllepartyopen}>
                파티 생성
              </button>
              {partyopenStatus && (
                  <div className="party">
                    <div className="inputWithButton">
                      <input
                          type="text"
                          className="lInputData inputMatchData"
                          value={id}
                          onChange={handleInputChange}
                          placeholder="같이 할 유저의 id 입력!"
                      />
                      <button className="mBtn lButton pBtn" type="button" onClick={searchId}>
                        파티원 추가
                      </button>
                    </div>

                    <button className="mBtn lButton pBtn" type="button" onClick={renewData}>
                      수락여부 갱신
                      <div><span className="systemMessage">{searchStatus}</span></div>
                    </button>

                    <div className="partyList">
                      {party.map((member, index) => (
                          <div key={index}>
                            <div className="Pname">{member.user.name}
                              <div className="Pstate">{member.agreement === 'AGREE' ? (
                                  <div>수락</div>
                              ) : member.agreement === 'DISAGREE' ? (
                                  <div>거절</div>
                              ) : (
                                  <div>대기
                                    <button
                                        className="mBtn lButton deleteBtn"
                                        type="button"
                                        onClick={() => deleteId(index)}
                                    >
                                      삭제
                                    </button>
                                  </div>
                              )}</div>
                            </div>
                          </div>
                      ))}
                    </div>
                  </div>
              )}
            </div>
            <div className="pointContainer">
              <div className="mBtn lButton pointBtn" type="button">
                {session.name}님 잔여 포인트 : {session.ownedPoint}
                <button className="mBtn lButton mSubBtn" type="button" onClick={submitMatch}>
                  매칭
                </button>
              </div>
            </div>
          </form>
        </div>
        </>
  );
}

export default LeagueMatch;
