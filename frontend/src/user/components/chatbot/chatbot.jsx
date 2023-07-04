import Chatbot from 'react-chatbot-kit'
import 'react-chatbot-kit/build/main.css'
import config from './config';
import MessageParser from './MessageParser';
import ActionProvider from './ActionProvider';
import React, {useState} from "react";
import Header from "./ChatbotHeader";
import './chatbot.css'


const ChatbotComponent = () => {
    const [chatbotVisible, setChatbotVisible] = useState(false);
    // console.log(chatbotVisible);
    const toggleChatbot = (z) => {
        setChatbotVisible(!chatbotVisible);
        // console.log('누름');
    };

    return (
        <div>
            {chatbotVisible ? (
                <div className="chatbot-container">
                    <Header toggleChatbot={chatbotVisible} />
                    <Chatbot
                        config={config}
                        messageParser={MessageParser}
                        actionProvider={ActionProvider}
                    />
                    <div className="chatbot-icon" onClick={toggleChatbot} alt="temp"></div>
                </div>
            ) : (
                <div className="chatbot-icon" onClick={toggleChatbot} alt="temp"></div>
            )}
        </div>
    );
};

export default ChatbotComponent;