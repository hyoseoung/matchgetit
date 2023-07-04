import React, { useState } from 'react';
import './ApplyManager.css';
import ManagerRecruitment from './ManagerRecruitment';

function ApplyManager(props) {
  const { session } = props;
  const [showManagerRecruitment, setShowManagerRecruitment] = useState(false);

  const handleApplyBtnClick = () => {
    setShowManagerRecruitment(true);
  };

  return (
    <div>
      <h3>매니저 지원하기</h3>
      <div className="my-page">
        <div className="my-page_body">
          <div className="my-info">
            <div className="forms">
              <fieldset>
                {!showManagerRecruitment ? (
                  <div className="ApplyManager">
                    <h3 className="apply-title">매니저 모집</h3>
                    <h3 className="apply-subtitle">매치기릿 매니저를 모집합니다!</h3>
                    <ul className="apply-list">
                      <li>※ 활동비 - 1경기(2시간) / 25,000원</li>
                      <li>※ 활동내용 - 경기진행, 회원출결관리, 경기장비관리, 경기기록작성</li>
                      <li>※ 인원 부족한 경우 직접 경기참여 의무가 있습니다.</li>
                    </ul>
                    <button className="applyBtn" onClick={handleApplyBtnClick}>
                      매니저 지원하기
                    </button>
                  </div>
                ) : (
                  <ManagerRecruitment session={session} />
                )}
              </fieldset>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default ApplyManager;
