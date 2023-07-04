import React, { useState } from "react";

import FlashCard from "./FlashCard";
const PaymentsGuide = (props) => {
    const options = [
        {
            text: "환불 규정 안내",
            handler: props.actionProvider.handleRefundGuide,
            id: 1,
        },
        {
            text: "내 경기 환불",
            handler: props.actionProvider.handleRefund,
            id: 2,
        },
        {
            text: "고객 센터 문의",
            handler: props.actionProvider.handleServiceCenter,
            id: 3,
        },
        {
            text: "처음으로 돌아가기",
            handler: props.actionProvider.handleHello,
            id: 4,
        },
    ];

    const buttonsMarkup = options.map((option) => (
        <button key={option.id} onClick={option.handler} className="option-button">
            {option.text}
        </button>
    ));

    return <div className="options-container">{buttonsMarkup}</div>;
};
export default PaymentsGuide;
