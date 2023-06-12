import React, { useState, useEffect } from 'react';
import './NaverPay.css';

const NaverPay = () => {
  const [selectedAmount, setSelectedAmount] = useState(0);

  const toggleButton = (button, amount) => {
    setSelectedAmount(amount);
  };

  const handleNaverPayBtnClick = () => {
    if (selectedAmount < 100) {
      alert('결제금액은 100 이상이어야 합니다.');
      return;
    }

    const oPay = window.Naver.Pay.create({
      mode: 'development', // development or production
      clientId: 'dQPaTGkl7UD9gyUVttF3', // clientId
      openType: 'popup',
      onAuthorize: (oData) => {
        if (oData.resultCode === 'Success') {
          const paymentId = oData.paymentId;
          const productName = oData.productName;
          const totalPayAmount = selectedAmount;
          const admissionState = oData.admissionState;

          console.log('결제 ID:', paymentId);
          console.log('상품명:', productName);
          console.log('총 결제 금액:', totalPayAmount);
          console.log('결제 상태:', admissionState);
        }
      },
    });

    oPay.open({
      merchantUserKey: '4648484646464',
      merchantPayKey: '7864546465465465',
      productName: '매치기릿',
      totalPayAmount: selectedAmount,
      taxScopeAmount: selectedAmount,
      taxExScopeAmount: '0',
      returnUrl: '사용자 결제 완료 후 결제 결과를 받을 URL',
    });
  };

  useEffect(() => {
    const script = document.createElement('script');
    script.src = 'https://nsp.pay.naver.com/sdk/js/naverpay.min.js';
    script.async = true;
    document.body.appendChild(script);

    return () => {
      document.body.removeChild(script);
    };
  }, []);

  return (
    <div>
        <button className={`button ${selectedAmount === 2000 ? 'clicked' : ''}`} onClick={() => toggleButton(2000, 2000)}>
        2,000원
        </button>
        <button className={`button ${selectedAmount === 5000 ? 'clicked' : ''}`} onClick={() => toggleButton(5000, 5000)}>
        5,000원
        </button>
        <button className={`button ${selectedAmount === 10000 ? 'clicked' : ''}`} onClick={() => toggleButton(10000, 10000)}>
        10,000원
        </button>
        <br />
        <button className={`button ${selectedAmount === 20000 ? 'clicked' : ''}`} onClick={() => toggleButton(20000, 20000)}>
        20,000원
        </button>
        <button className={`button ${selectedAmount === 30000 ? 'clicked' : ''}`} onClick={() => toggleButton(30000, 30000)}>
        30,000원
        </button>
        <button className={`button ${selectedAmount === 50000 ? 'clicked' : ''}`} onClick={() => toggleButton(50000, 50000)}>
        50,000원
        </button>
        <br />
        <button className={`button ${selectedAmount === 80000 ? 'clicked' : ''}`} onClick={() => toggleButton(80000, 80000)}>
        80,000원
        </button>
        <button className={`button ${selectedAmount === 90000 ? 'clicked' : ''}`} onClick={() => toggleButton(90000, 90000)}>
        90,000원
        </button>
        <button className={`button ${selectedAmount === 100000 ? 'clicked' : ''}`} onClick={() => toggleButton(100000, 100000)}>
        100,000원
        </button>
        <br />
        <input type="button" id="naverPayBtn" value="네이버페이 결제 버튼" onClick={handleNaverPayBtnClick} />
        <div id="paymentInfo">
            <p>결제 ID:</p>
            <p>총 결제 금액: {selectedAmount}원</p>
        </div>
    </div>
  );
};

export default NaverPay;
