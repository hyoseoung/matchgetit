import React, { useState } from 'react';

const ChargeComponent = () => {
  const [amount, setAmount] = useState('');
  const [name, setName] = useState('');
  const [agreed, setAgreed] = useState(false);

  const handleAmountChange = (event) => {
    setAmount(event.target.value);
  };

  const handleNameChange = (event) => {
    setName(event.target.value);
  };

  const handleAgreementChange = () => {
    setAgreed(!agreed);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    // Handle form submission
  };

  return (
    <div id="charge" className="page-container">
      <div className="page-body">
        <div className="charge">
          <div className="charge__title">
            <h2>미리 충전하고<br />더욱 편리하게!</h2>
          </div>
          <form onSubmit={handleSubmit}>
            <div className="charge__select">
              <p className="charge__select-title">충전할 금액</p>
              <div className="input">
                <ul className="radio-wrap hstack" style={{ justifyContent: 'space-between' }}>
                  <li className="radio-item radio-item__box">
                    <input
                      type="radio"
                      id="item_2"
                      name="item_2"
                      className="radio-gnb"
                      value="2"
                      checked={amount === '2'}
                      onChange={handleAmountChange}
                    />
                    <label htmlFor="item_2" className="checkLabel">
                      <p>2,000원</p>
                    </label>
                  </li>
                  {/* Add other radio options */}
                </ul>
                <p className="charge__select-desc">보유 캐시 0원</p>
              </div>
            </div>
            <div className="charge__select">
              <p className="charge__select-title">입금자명</p>
              <div className="input_wrap">
                <input
                  type="text"
                  placeholder="한글 6글자, 영어 18글자 이내로 입력해주세요"
                  value={name}
                  onChange={handleNameChange}
                />
              </div>
            </div>
            <div className="charge__select">
              <p className="charge__select-title">결제 수단</p>
              <div className="input">
                <ul className="radio-wrap">
                  <li className="radio-item">
                    <input
                      type="radio"
                      id="cash"
                      value=""
                      name="#"
                      checked={true} // Assuming '무통장 입금' is the default option
                      readOnly
                    />
                    <label htmlFor="cash">
                      <span className="radio"></span>
                      <div>
                        <p style={{ textAlign: 'left' }}>무통장 입금</p>
                        <p className="radio-item__span">현재는 무통장 입금만 가능합니다</p>
                      </div>
                    </label>
                  </li>
                </ul>
              </div>
            </div>
            <div className="charge__select">
              <p className="charge__select-title">결제 동의</p>
              <div className="input">
                <ul className="checkbox-wrap">
                  <li className="checkbox-item">
                    <input
                      type="checkbox"
                      id="confirm"
                      checked={agreed}
                      onChange={handleAgreementChange}
                    />
                    <label htmlFor="confirm">
                      <span className="checkbox"></span>
                      <p>입금자명과 입금 금액을 확인하였습니다</p>
                    </label>
                  </li>
                </ul>
              </div>
            </div>
            <div className="charge btn-wrap">
              <button className="btn__disabled" disabled={!agreed}>
                충전 신청
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default ChargeComponent;
