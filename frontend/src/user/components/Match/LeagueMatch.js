import React, {useState} from 'react';
import '../../styles/MatchingPage/Application/applicationContainer.css';
import '../../styles/MatchingPage/Application/leagueMatching.css';
import '../../styles/CommonFactor/radioButton.css';
import axiosInstance from "../axiosInstance";
import AddressSearch from "./AddressSearch";

const LeagueMatch = ({ session }) => {
  const [party, setParty] = useState([]);
  const [searchStatus, setSearchStatus] = useState('');
  const [addressVisible, setAddressVisible] = useState(false);
  const [id, setId] = useState('');
  const [address, setAddress] = useState('');
  const [selectedDate, setSelectedDate] = useState('');
  const [selectedTime, setSelectedTime] = useState('A');
  const [x,setX]=useState('');
  const [y,setY]=useState('');

  const handleInputChange = (event) => {
    setId(event.target.value);
  };
  const findMembers = () => {
    axiosInstance
        .post('/matchGetIt/match/getMember', null, { params: { id: session.userId} })
        .then((response) => {
          console.log(response.data);
        })
        .catch((error) => {
          console.log('파티 없음');
        });
  }

  const searchId = () => {
    if(session.userId!=id){
      axiosInstance
          .post('/matchGetIt/party/searchId', null, { params: { id: id, partyLeaderId:session.userId } })
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
        .post('/matchGetIt/party/renewPartyAcceptData', null, { params: {partyLeaderId:session.userId}})
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

      const partyMembers = party.map((member) => ({ id: member.userId }));


      const requestData = {
        partyLeader: session.userId,
        x: x,
        y: y,
        selectedDate: selectedDate,
        selectedTime: selectedTime,
        party: partyMembers,
      };

      axiosInstance
          .post('/matchGetIt/match/start', requestData)
          .then((response) => {
            console.log('성공');
          })
          .catch((error) => {
            console.log('실패');
          });
    }else{
      setSearchStatus('수락하지 않은 파티원이 있습니다');
      setTimeout(() => {
        setSearchStatus('');
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
          <div className="matchingTitle lTitle">League Match</div>
          <form>
            <div className="address">
              <div className="lSubTitle subTitle addressTitle">선택한 지역</div>
              <input
                  type="text"
                  className="lInputData inputData"
                  readOnly
                  required
                  value={address}
              />
              <button className="mBtn lButton addressBtn" type="button"  onClick={() => setAddressVisible(true)}>
                주소 검색
              </button>
              <AddressSearch visible={addressVisible} setVisible={setAddressVisible} onSelect={onSelectAddress} />
            </div>
            <div className="time">
              <div className="lSubTitle subTitle timeTitle">선택 시간</div>
              <input
                  type="date"
                  className="timeSelect mDate"
                  value={selectedDate}
                  onChange={(event) => setSelectedDate(event.target.value)}
              />
              <select
                  className="mTime"
                  value={selectedTime}
                  onChange={(event) => setSelectedTime(event.target.value)}
              >
                <option value="A">오전 10시~오후 12시</option>
                <option value="B">오후 12시~오후 2시</option>
                <option value="C">오후 2시~오후 4시</option>
                <option value="D">오후 4시~오후 6시</option>
                <option value="E">오후 6시~오후 8시</option>
                <option value="F">오후 8시~오후 10시</option>
              </select>
            </div>
            <div className="party">
              <div className="lSubTitle subTitle partyTitle">같이 할 파티원</div>

              <input
                  type="text"
                  className="lInputData inputMatchData"
                  value={id}
                  onChange={handleInputChange}
                  placeholder="같이 할 유저의 id 입력!"
              />
              <span className="systemMessage">{searchStatus}</span>
              <button className="mBtn lButton pBtn" type="button" onClick={searchId}>
                파티원 추가
              </button>
              <div className="partyList">
                {party.filter(member=> member.agreement=='AGREE').map((member, index) => (
                    <div key={index}>
                      <div className="Pname">{member.userName}</div>
                      <div className="deleteBtnArea">
                        <button
                            className="mBtn lButton deleteBtn"
                            type="button"
                            onClick={() => deleteId(index)}
                        >
                          삭제
                        </button>
                      </div>
                    </div>
                ))}
              </div>
              <button className="mBtn lButton pBtn" type="button" onClick={renewData}>
                수락여부 갱신
              </button>
            </div>
            <button className="mBtn lButton pointBtn" type="button">
              {session.name}님 잔여 포인트 : {session.ownedPoint}
            </button>
            <button className="mBtn lButton mSubBtn" type="button" onClick={submitMatch}>
              매칭
            </button>
          </form>
        </div>
      </>
  );
};

export default LeagueMatch;
