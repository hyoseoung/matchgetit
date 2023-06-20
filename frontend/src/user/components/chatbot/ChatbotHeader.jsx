import styled from "styled-components";
import React, {useState} from 'react';
import "./chatbot"


const HeaderContainer = styled.div`
  background: rgb(92, 130, 255);
  background: linear-gradient(
          90deg,
          rgba(157, 92, 255, 1) 0%,
          rgba(92, 130, 255, 1) 100%
  );
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;

  .ri-close-line,
  .ri-arrow-left-s-line {
    font-size: 1rem;
    color: #ffffff;
    cursor: pointer;
  }
`;

const ChatbotHeader = ({chatbotVisible}) => {
    console.log(chatbotVisible);

    return (
        <HeaderContainer>
            <i className="ri-arrow-left-s-line">Match get it</i>
            {/*<i className="ri-close-line" onClick={chatbotVisible}>*/}
            {/*    닫기*/}
            {/*</i>*/}
        </HeaderContainer>
    );
};

export default ChatbotHeader;
