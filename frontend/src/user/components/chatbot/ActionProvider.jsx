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

    handleHello = () => {
        const botName="매치기";
        const message = this.createChatBotMessage(
                `안녕하세요 ${botName}입니다!`,{
                    widget: "general",
                } )
        this.addMessageToState(message);
    };

    // handleUnknow = () => {
    //     const message = this.createChatBotMessage(
    //         `무슨 말씀이신지 잘 모르겠어요. 아래 내용을 선택해주세요!`, {
    //         widget:"general"})
    //     this.addMessageToState(message);
    // };

    handleServiceGuide = () => {
        const message = this.createChatBotMessage(
            `이용 규칙입니다.`, {
                widget: "serviceGuide",
            }
        );  this.addMessageToState(message);
    };
    handleMatchGetItGuide = () => {
        const message = this.createChatBotMessage(
            `Match get it 이란, 어쩌구 저쩌구 이렇고 저렇고 이런 저런 내용 으쌰 으쌰 빠샤 빠샤 호잇 호오오오잇`, {
                widget: "serviceGuide",
            }
        );  this.addMessageToState(message);
    };
    handleFootballGuide = () => {
        const message = this.createChatBotMessage(
            `풋살 어쩌구 저쩌구 이렇고 저렇고 이런 저런 내용 으쌰 으쌰 빠샤 빠샤 호잇 호오오오잇`, {
                widget: "serviceGuide",
            }
        );  this.addMessageToState(message);
    };
    handleContactMng = () => {
        const message = this.createChatBotMessage(
            `담당 매니저와 연결됩니다.`, {
                widget: "contactMng",
            }
        );  this.addMessageToState(message);
    };

    handlePaymentGuide = () => {
        const message = this.createChatBotMessage(
            "결제 관련 안내입니다", {
                widget: "paymentGuide",
            }
        );  this.addMessageToState(message);
    };
    handleServiceCenter = () => {
        const message = this.createChatBotMessage(
            "고객 센터입니다.", {
                widget: "serviceCenter",
            }
        );  this.addMessageToState(message);
    };
    handleRefundGuide = () => {
        const message = this.createChatBotMessage(
            <>
                환불 규정 안내입니다. <br /> <br />
                경기 시작 3일 이전 취소: 100% 환불 <br />
                경기 시작 3일 미만 취소: 80% 환불 <br />
                경기 시작 2일 미만 취소: 60% 환불 <br />
                경기 시작 1일 미만 취소: 환불 불가
            </>,
            {
                widget: "refundGuide",
            }
        );
        this.addMessageToState(message);
    };
    handleRefundyes = () => {
        const userwallet = "3333-12-6657443"
        const userbank = "카카오뱅크"
        const usertemppoint = "10,000"
        const message = this.createChatBotMessage(
            <>
                환불을 진행합니다.<br />
                계좌번호  : {userwallet} <br />
                  은 행  : {userbank} <br />
                환불예상액 : {usertemppoint} : 원 <br />

                상기 내역이 맞으면 환불을 눌러주세요 <br />
                **버튼을 누르면 환불이 진행됩니다.** <br />
            </>,
            {
                widget: "refundyes",
            }
        );
        this.addMessageToState(message);
    };
    handleRefundok = () => {
        const userwallet = "3333-12-6657443"
        const userbank = "카카오뱅크"
        const usertemppoint = "10,000"
        const message = this.createChatBotMessage(
            <>
                해당 계좌로 영업일기준 2~3일 이내 환불이 진행됩니다.<br />
                계좌번호  : {userwallet} <br />
                  은 행  : {userbank} <br />
                환불예상액 : {usertemppoint} : 원 <br />
            </>,
            {
                widget: "refundok",
            }
        );
        this.addMessageToState(message);
    };
    handleRefund = () => {
        const userName = "John"; // 예시로 사용자 이름을 "John"으로 설정합니다.
        let hasMatch='yes'; // 매칭 현황이 존재하는지 여부를 나타내는 변수입니다.
        let widget;
        let messageText;

        if (hasMatch==='yes') {
            widget = "refundhasmatch"; // 매칭 현황이 존재하는 경우
            messageText = (
                <>
                    {userName}님의 매칭 현황입니다.;<br />
                    해당 매칭을 취소&환불 하시겠습니까?
                </>
            )
        } else if(hasMatch==='ing'){
            widget = "refundmatching"; // 매칭이 진행중인 경우
            messageText = (
                <>
                    {userName}님은 현재 매칭이 진행 중입니다.<br />
                    아래는 매칭 신청 현황입니다!<br />
                    <br />
                    매칭 취소는 매칭 화면에서 가능합니다.

                </>
            )
        }else{
            widget = "refundnomatch"; // 매칭 현황이 존재하지 않는 경우
            messageText = (
                <>
                    {userName}님은 매칭 현황이 존재하지 않습니다.<br />
                    매칭을 진행 후 확인해주세요!
                </>
            )
        }

        const message = this.createChatBotMessage(messageText, { widget: widget });

        this.addMessageToState(message);
    };
    handleMatchGuide = () => {
        const userName = "John"; // 예시로 사용자 이름을 "John"으로 설정합니다.
        let hasMatch='yes'; // 매칭 현황이 존재하는지 여부를 나타내는 변수입니다.
        let widget;
        let messageText;

        if (hasMatch==='yes') {
            widget = "hasmatch"; // 매칭 현황이 존재하는 경우
            messageText = `${userName}님의 매칭 현황입니다.`;
        } else if(hasMatch==='ing'){
            widget = "matching"; // 매칭이 진행중인 경우
            messageText = (
                <>
                    {userName}님은 현재 매칭이 진행 중입니다.<br />
                    아래는 매칭 신청 현황입니다!
                </>
            )
        }else{
            widget = "nomatch"; // 매칭 현황이 존재하지 않는 경우
            messageText = (
                <>
                    {userName}님은 매칭 현황이 존재하지 않습니다.<br />
                    매칭을 진행 후 확인해주세요!
                </>
            )
        }

        const message = this.createChatBotMessage(messageText, { widget: widget });

        this.addMessageToState(message);
    };





}

export default ActionProvider;
