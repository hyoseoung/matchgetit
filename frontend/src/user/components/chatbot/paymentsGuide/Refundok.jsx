import React, { useState } from "react";

import FlashCard from "./FlashCard";
const PaymentsGuide = (props) => {
    const options = [
        {
            text: "환불하기",
            handler: props.actionProvider.handleRefundok,
            id: 1,
        },
        {
            text: "처음으로 돌아가기",
            handler: props.actionProvider.handleHello,
            id: 2,
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
