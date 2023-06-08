import React from 'react';

const ActionProvider = ({ createChatBotMessage, setState, children }) => {
    const handleHello = () => {
        const botMessage = createChatBotMessage('반갑습니다. 매치기 봇 입니다. 무엇을 도와드릴까요?');

        setState((prev) => ({
            ...prev,
            messages: [...prev.messages, botMessage],
        }));
    };

    // Put the handleHello function in the actions object to pass to the MessageParser
    return (
        <div>
            {React.Children.map(children, (child) => {
                return React.cloneElement(child, {
                    actions: {
                        handleHello,
                    },
                });
            })}
        </div>
    );
};

export default ActionProvider;