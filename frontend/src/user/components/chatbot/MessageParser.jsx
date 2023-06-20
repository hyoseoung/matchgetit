class MessageParser {
    constructor(actionProvider) {
        this.actionProvider = actionProvider;
    }

    parse(message) {
        console.log(message);
        const lowercase = message.toLowerCase();
        // 인사
        if (lowercase.includes("안녕") || lowercase.includes("안녕하세요") || lowercase.includes("안녕하십니까") || lowercase.includes("안녕하실까요") ||
            lowercase.includes("ㅎㅇ") || lowercase.includes("하이") || lowercase.includes("HI") || lowercase.includes("hello")
        ) {
            this.actionProvider.handleHello();
        }
        // 이용 규칙
        else if (lowercase.includes("이용 안내") || lowercase.includes("이용안내") ||
            lowercase.includes("이용 규칙") || lowercase.includes("이용규칙") ||
            lowercase.includes("서비스 안내") || lowercase.includes("서비스안내") ||
            lowercase.includes("서비스 규칙") || lowercase.includes("서비스규칙") ||
            lowercase.includes("사용법") || lowercase.includes("사용방법") ||
            lowercase.includes("사용하는법") || lowercase.includes("사용 하는 법") || lowercase.includes("사용하는 법") ||
            lowercase.includes("사용하는방법") || lowercase.includes("사용 하는 방법") || lowercase.includes("사용하는 방법")
        ) {
            this.actionProvider.handleServiceGuide();
        }
        //내 매칭 현황
        else if (lowercase.includes("매칭현황") || lowercase.includes("매칭 현황") ||
            lowercase.includes("예약내역") || lowercase.includes("예약 내역") || lowercase.includes("내 현황") ||
            lowercase.includes("내 매칭") || lowercase.includes("내 예약") ||
            lowercase.includes("내매칭") || lowercase.includes("내예약") ||
            lowercase.includes("내 매칭 현황") || lowercase.includes("내매칭 현황") || lowercase.includes("내 매칭현황") || lowercase.includes("내매칭현황") ||
            lowercase.includes("내 예약 현황") || lowercase.includes("내예약 현황") || lowercase.includes("내 매예약현황") || lowercase.includes("내예약현황")
        ) {
            this.actionProvider.handleMatchGuide();
        }
        //결제관련
        else if (lowercase.includes("결제") || lowercase.includes("결재") ||
            lowercase.includes("결제내역") || lowercase.includes("결제 내역") || lowercase.includes("결재내역") || lowercase.includes("결재 내역") ||
            lowercase.includes("결제안내") || lowercase.includes("결제 안내") || lowercase.includes("결재안내") || lowercase.includes("결재 안내") ||
            lowercase.includes("취소") || lowercase.includes("취소안내") || lowercase.includes("취소 안내") ||
            lowercase.includes("환불") || lowercase.includes("환불안내") || lowercase.includes("환불 안내")
        ) {
            this.actionProvider.handlePaymentGuide();
        }
        //고객센터 문의
        else if (lowercase.includes("고객센터") || lowercase.includes("상담원") || lowercase.includes("사람") ||
            lowercase.includes("몰라") || lowercase.includes("모르겠어") || lowercase.includes("뭔데") || lowercase.includes("뭐지") ||
            lowercase.includes("몰라?") || lowercase.includes("모르겠는데") || lowercase.includes("뭐") || lowercase.includes("뭘까") ||
            lowercase.includes("어떻게해") || lowercase.includes("어떻게 해") || lowercase.includes("어떻게해?") || lowercase.includes("어떻게 해?") ||
            lowercase.includes("어떡해?") || lowercase.includes("어떡해") || lowercase.includes("어떡하지") || lowercase.includes("어떡하지?") ||
            lowercase.includes("사람") || lowercase.includes("사람불러줘") || lowercase.includes("고객센터") || lowercase.includes("어떻해") || lowercase.includes("어떻해?")
        ) {
            this.actionProvider.handleServiceCenter();
        }
        else{
            this.actionProvider.handleHello();
        }
    }
}

export default MessageParser;
