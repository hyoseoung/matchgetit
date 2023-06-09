import { createChatBotMessage } from 'react-chatbot-kit';
import Header from "./Header"
import ChatMessage from "./ChatMessage"
// import botimg from "../../../../public/images/BallIcon.svg"
import Options from "./Options"
import matchGuide from "./matchGuide"


const botName="매치기"

const config = {
    initialMessages: [
        createChatBotMessage(
            `안녕하세요 ${botName}입니다!`,{
                widget: "general",
            } ),
    ],
    customComponents: {
        // Replaces the default header
        header: () => <Header />,
        // Replaces the default bot avatar
        botAvatar: (props) => <botimg {...props} />,
        // Replaces the default bot chat message container
        botChatMessage: (props) => <ChatMessage {...props} bot />,
        // Replaces the default user icon
        userAvatar: (props) => <div {...props} />,
        // Replaces the default user chat message
        userChatMessage: (props) => <ChatMessage {...props} />,
    },
    widgets: [
        {
            widgetName: "general",
            widgetFunc: (props) => <Options {...props} />,
        },
        {
            widgetName: "matchGuide",
            widgetFunc: (props) => <matchGuide {...props} />,
            props: {
                questions: [
                    {
                        question: "What is closure?",
                        answer:
                            "Closure is a way for a function to retain access to it's enclosing function scope after the execution of that function is finished.",
                        id: 1,
                    },
                    {
                        question: "Explain prototypal inheritance",
                        answer:
                            "Prototypal inheritance is a link between an object and an object store that holds shared properties. If a property is not found on the host object, javascript will check the prototype object.",
                        id: 2,
                    },
                ],
            },
        },
        ],
};

export default config;