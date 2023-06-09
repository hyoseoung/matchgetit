import {createChatBotMessage} from "react-chatbot-kit";

class ActionProvider {

    constructor(createChatBotMessage, setStateFunc) {
        this.createChatBotMessage = createChatBotMessage;
        this.setState = setStateFunc;
    }

    addMessageToState = (message) => {
        this.setState((prevState) => ({
            ...prevState,
            messages: [...prevState.messages, message],
        }));
    };

    handlehello = () => {
        const botName="매치기";
        const message = this.createChatBotMessage(
                `안녕하세요 ${botName}입니다!`,{
                    widget: "general",
                } )
        this.addMessageToState(message);
    };

    handleunknow = () => {
        const message = this.createChatBotMessage(
            `무슨 말씀이신지 잘 모르겠어요. 아래 내용을 선택해주세요!`, {
            widget:"general"})
        this.addMessageToState(message);
    };

    handleserviceGuide = () => {
        const message = this.createChatBotMessage(
            `이용 규칙입니다.`, {
                widget: "serviceGuide",
            }
        );  this.addMessageToState(message);
    };
    handlematchGuide = () => {
        const userName = " "
        const message = this.createChatBotMessage(
            `${userName}님의 매칭 현황입니다`, {
                widget: "matchGuide",
            }
        );  this.addMessageToState(message);
    };
    handlepaymentGuide = () => {
        const message = this.createChatBotMessage(
            "결제 관련 안내입니다", {
                widget: "paymentGuide",
            }
        );  this.addMessageToState(message);
    };
    handleserviceCenter = () => {
        const message = this.createChatBotMessage(
            "고객 센터입니다.", {
                widget: "serviceCenter",
            }
        );  this.addMessageToState(message);
    };


}

export default ActionProvider;
