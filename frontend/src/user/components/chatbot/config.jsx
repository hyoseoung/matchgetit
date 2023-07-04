import { createChatBotMessage } from 'react-chatbot-kit';
import Header from "./ChatbotHeader"
import ChatMessage from "./ChatMessage"
// import Botimg from "../../../../public/images/BallIcon.svg"
import Options from "./Options/Options"
import MatchGuide from "./matchGuide/matchGuide"
import ServiceGuide from "./serviceGuide/serviceGuide";
import PaymentsGuide from "./paymentsGuide/paymentsGuide";
import ContactMng from "./contactMng/ContactMng";
import Refund from "./paymentsGuide/Refund";
import Refundok from "./paymentsGuide/Refundok";

const botName="매치기"

const config = {
    initialMessages: [
        createChatBotMessage(
            `안녕하세요 ${botName}입니다!`,{
            }, ),
        createChatBotMessage(
            `챗봇을 종료하시려면 아이콘을 한 번 더 클릭해주세요!`,{
                widget: "general",
            }, ),
    ],
    customComponents: {
        // Replaces the default header
        header: () => <Header />,
        // Replaces the default bot avatar
        botAvatar: (props) => <div {...props} />,
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
            widgetName: "hasmatch",    //매칭현황 있음
            widgetFunc: (props) => <MatchGuide {...props} />,
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
        {
            widgetName: "matching",  //매칭 진행 중
            widgetFunc: (props) => <MatchGuide {...props} />,
        },
        {
            widgetName: "nomatch",  //매칭 현황 존재 X
            widgetFunc: (props) => <MatchGuide {...props} />,
        },
        {
            widgetName: "serviceGuide",
            widgetFunc: (props) => <ServiceGuide {...props} />,
        },
        {
            widgetName: "paymentGuide",
            widgetFunc: (props) => <PaymentsGuide {...props} />,
        },
        {
            widgetName: "refundGuide",
            widgetFunc: (props) => <PaymentsGuide {...props} />,
        },
        {
            widgetName: "refundyes",
            widgetFunc: (props) => <Refundok {...props} />,
        },
        {
            widgetName: "refundok",
            widgetFunc: (props) => <PaymentsGuide {...props} />,
        },
        {
            widgetName: "refundhasmatch",
            widgetFunc: (props) => <Refund {...props} />,
        },
        {
            widgetName: "refundmatching",
            widgetFunc: (props) => <Refund {...props} />,
        },
        {
            widgetName: "refundnomatch",
            widgetFunc: (props) => <PaymentsGuide {...props} />,
        },
        {
            widgetName: "contactMng",
            widgetFunc: (props) => <ContactMng {...props} />,
        },

        ],
};

export default config;