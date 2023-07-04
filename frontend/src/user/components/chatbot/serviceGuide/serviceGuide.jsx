import React, { useState } from "react";

const ServiceGuide = (props) => {
    const options = [
        {
            text: "Match get it 이란?",
            handler: props.actionProvider.handleMatchGetItGuide,
            id: 1,
        },
        {
            text: "풋살 이용 규칙",
            handler: props.actionProvider.handleFootballGuide,
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
export default ServiceGuide;
