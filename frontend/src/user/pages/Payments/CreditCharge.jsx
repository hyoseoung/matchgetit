import React, {useEffect, useState} from 'react';
import './CreditCharge.css';

const ChargeComponent = () => {
  const [amount, setAmount] = useState('');
  const [isPayment, setIsPayment] = useState(false);
  const [name, setName] = useState('');
  const [agreed, setAgreed] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');
  const [isChargeOpen, setChargeOpen] = useState(true);

  useEffect(() => {
    const handleResize = () => {
      document.documentElement.style.width = window.innerWidth + 'px';
      document.documentElement.style.height = window.innerHeight + 'px';
    };

    handleResize();

    window.addEventListener('resize', handleResize);
    return () => {
      window.removeEventListener('resize', handleResize);
    };
  }, []);

  const handleAmountChange = (event) => {
    setAmount(event.target.value);
  };
  const pay = (amount) =>{
    window.location.href=`http://localhost:8081/matchGetIt/pay/payStart/`+{amount};
  }


  const handleButtonClick = (paymentType) => {
    if (!amount) {
      setErrorMessage('결제 금액을 선택하세요');
    } else {
      setErrorMessage('');

      // 선택된 페이 타입과 금액을 활용하여 페이지 이동 등의 동작 수행
      if (paymentType === 'kakaopay') {
        // 카카오페이 페이지로 이동
        window.location.href = `https://example.com/kakaopay?amount=${amount}`;
      } else if (paymentType === 'toss') {
        // 토스 페이지로 이동
        window.location.href = `http://localhost:8081/matchGetIt/pay/payStart/`+amount;
      } else if (paymentType === 'naverpay') {
        // 네이버페이 페이지로 이동
        window.location.href = `https://example.com/naverpay?amount=${amount}`;
      }

      // 추가로 수행해야 할 동작들을 구현하세요
    }
  };
  return (
    <div id="charge" className="page-container">
      <div className="page-body">
        <div className="charge">
          <div className="charge__title">
            <h2>미리 충전하고<br />더욱 편리하게!</h2>
          </div>
            <div className="charge__select">
              <p className="charge__select-title">충전할 금액</p>
              <div className="input">
                <ul className="radio-wrap hstack" style={{ justifyContent: 'space-between' }}>
                  <li className="radio-item radio-item__box">
                    <input
                        type="radio"
                        id="item_2"
                        name="item"
                        className="radio-gnb"
                        value="2000"
                        checked={amount === '2000'}
                        onChange={handleAmountChange}
                    />
                    <label htmlFor="item_2" className="checkLabel">
                      <p>2,000원</p>
                    </label>
                  </li>
                  <li className="radio-item radio-item__box">
                    <input
                        type="radio"
                        id="item_3"
                        name="item"
                        className="radio-gnb"
                        value="5000"
                        checked={amount === '5000'}
                        onChange={handleAmountChange}
                    />
                    <label htmlFor="item_3" className="checkLabel">
                      <p>5,000원</p>
                    </label>
                  </li>

                  <li className="radio-item radio-item__box">
                    <input
                        type="radio"
                        id="item_4"
                        name="item"
                        className="radio-gnb"
                        value="20000"
                        checked={amount === '20000'}
                        onChange={handleAmountChange}
                    />
                    <label htmlFor="item_4" className="checkLabel">
                      <p>20,000원</p>
                    </label>
                  </li>

                  <li className="radio-item radio-item__box">
                    <input
                        type="radio"
                        id="item_5"
                        name="item"
                        className="radio-gnb"
                        value="30000"
                        checked={amount === '30000'}
                        onChange={handleAmountChange}
                    />
                    <label htmlFor="item_5" className="checkLabel">
                      <p>30,000원</p>
                    </label>
                  </li>
                  <li className="radio-item radio-item__box">
                    <input
                        type="radio"
                        id="item_6"
                        name="item"
                        className="radio-gnb"
                        value="50000"
                        checked={amount === '50000'}
                        onChange={handleAmountChange}
                    />
                    <label htmlFor="item_6" className="checkLabel">
                      <p>50,000원</p>
                    </label>
                  </li>
                  <li className="radio-item radio-item__box">
                    <input
                        type="radio"
                        id="item_7"
                        name="item"
                        className="radio-gnb"
                        value="80000"
                        checked={amount === '80000'}
                        onChange={handleAmountChange}
                    />
                    <label htmlFor="item_7" className="checkLabel">
                      <p>80,000원</p>
                    </label>
                  </li>
                  <li className="radio-item radio-item__box">
                    <input
                        type="radio"
                        id="item_9"
                        name="item"
                        className="radio-gnb"
                        value="90000"
                        checked={amount === '90000'}
                        onChange={handleAmountChange}
                    />
                    <label htmlFor="item_9" className="checkLabel">
                      <p>90,000원</p>
                    </label>
                  </li>

                  <li className="radio-item radio-item__box">
                    <input
                        type="radio"
                        id="item_10"
                        name="item"
                        className="radio-gnb"
                        value="100000"
                        checked={amount === '100000'}
                        onChange={handleAmountChange}
                    />
                    <label htmlFor="item_10" className="checkLabel">
                      <p>100,000원</p>
                    </label>
                  </li>
                </ul>
                <p className="charge__select-desc">보유 캐시 0원</p>
              </div>

              {errorMessage && <p className="payment-errorMessage" >{errorMessage}</p>}
              <div className="check-money" onClick={()=>handleButtonClick('toss')}>결제 하기</div>
              {/*<div className="payment-icon" >*/}
                {/*<button onClick={() => handleButtonClick('kakaopay')} data-payment-type="kakaopay">*/}
                {/*  <img src="https://raw.githubusercontent.com/hyoseoung/matchgetit/8869a02909547f0f2c3d06aca29b111a41c3e830/Document/logo/NAVER_Pay_Logo/npay_78.png" />*/}
                {/*</button>*/}
                {/*<button onClick={()=>handleButtonClick('toss')}>*/}
                {/*  <img src="https://raw.githubusercontent.com/hyoseoung/matchgetit/8869a02909547f0f2c3d06aca29b111a41c3e830/Document/logo/Toss_Logo_Secondary_Blue/Toss_Logo_Secondary_Blue.png" />*/}
                {/*</button>*/}
                {/*<button onClick={() => handleButtonClick('kakaopay')} data-payment-type="kakaopay">*/}
                {/*  <img src="https://raw.githubusercontent.com/hyoseoung/matchgetit/8869a02909547f0f2c3d06aca29b111a41c3e830/Document/logo/kakaopay/payment_icon_yellow_large.png" />*/}
                {/*</button>*/}
              {/*</div>*/}
            </div>
        </div>
      </div>
    </div>
  );
};

export default ChargeComponent;
