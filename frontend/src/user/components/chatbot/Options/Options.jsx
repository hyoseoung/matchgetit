import React from "react";

import "./Options.css";

const Options = (props) => {
    const options = [
        {
            text: "이용 규칙",
            handler: props.actionProvider.handleserviceGuide,
            id: 1,
        },
        {
            text: "내 매칭 현황",
            handler: props.actionProvider.handlematchGuide,
            id: 2,
        },
        {
            text: "결제 관련",
            handler: props.actionProvider.handlepaymentGuide,
            id: 3,
        },
        {
            text: "고객센터 문의",
            handler: props.actionProvider.handleserviceCenter,
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

export default Options;
