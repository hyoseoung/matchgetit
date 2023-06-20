import React, { useState, useEffect } from 'react';
import './NaverPay.css';

const NaverPay = () => {
  const [selectedAmount, setSelectedAmount] = useState(0);



  const handleNaverPayBtnClick = () => {


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
      totalPayAmount: '200',
      taxScopeAmount: '200',
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

        <input type="button" id="naverPayBtn" value="네이버페이 결제 버튼" onClick={handleNaverPayBtnClick} />
        <div id="paymentInfo">
            <p>결제 ID:</p>
            <p>총 결제 금액: {selectedAmount}원</p>
        </div>
    </div>
  );
};

export default NaverPay;
