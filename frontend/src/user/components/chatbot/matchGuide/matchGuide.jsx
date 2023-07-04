import React, { useState } from "react";

import FlashCard from "./FlashCard";

const MatchGuide = (props) => {
    const options = [
        {
            text: "매니저 문의",
            handler: props.actionProvider.handleContactMng,
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

    const matchstate=true

    const matchcontant=" ";

    if(!matchstate){
        return <p>"매칭 내역이 존재 하지 않습니다"{buttonsMarkup}</p>
    }

    return <div className="options-container">
        <FlashCard
                    contant={matchcontant}
                />

        {buttonsMarkup}

    </div>;
};
    // let [questionIndex, setQuestionIndex] = useState(0);
    //
    // const incrementIndex = () => setQuestionIndex((prev) => (prev += 1));
    //
    // const currentQuestion = props.questions[questionIndex];
    //
    // if (!currentQuestion) {
    //     return <p>Quiz over.</p>;
    // }
    //
    // return (
    //
    // );
export default MatchGuide;
