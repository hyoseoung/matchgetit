import React, {useEffect, useState} from 'react';
import '../Payments/CreditHistory.css';
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import moment from "moment";


function CreditHistory (){
    const [value, onChange] = useState(new Date());
    const [mark, setMark] = useState([]);

    const marks = [
        "14-06-2023",
        "15-06-2023",
        "01-07-2023",
        "03-07-2023"
    ];

    const [selectedDateContent, setSelectedDateContent] = useState([]);

    const handleDateClick = (date) => {
        const selectedDate = moment(date).format('DD-MM-YYYY');
        const content = findContentByDate(selectedDate);
        setSelectedDateContent(content);
    };

    const findContentByDate = (date) => {
        const data = [
            { date: '15-06-2023', content: '6/21 경기 매칭', price: '-20,000원' },
            { date: '15-06-2023', content: '출석포인트', price: '+100원' },
            { date: '14-06-2023', content: '6/17 경기 매칭', price: '-20,000원'},
            { date: '01-07-2023', content: '7/05 경기 매칭', price: '-20,000원'},
            { date: '03-07-2023', content: '7/15 경기 매칭', price: '-20,000원'},
            { date: '03-07-2023', content: '크레딧 충전', price: '100,000원'},
        ];
        return data.filter((item) => item.date === date);
    };
    const tileClassName= ({ date, view }) => {
        let html = [];
        if (marks.find((x) => x === moment(date).format("DD-MM-YYYY"))) {
            return 'highlight';
        }
        return '';
    };

    return (
        <div>
            <div className="CreditHistory_M">
                <h2>나의 결제 내역</h2>
            </div>
            <Calendar
                onChange={onChange}
                value={value}
                locale="en-EN"
                className="calendar"
                tileClassName={tileClassName}
                onClickDay={handleDateClick} // 날짜를 클릭했을 때 호출될 함수
            />
            <br/><hr />
            {selectedDateContent.length > 0 && (
                <>
                    {selectedDateContent.map((content, index) => (
                        <div className="CreditHistory" key={index}>
                            <p>{moment(content).format('YYYY.MM.DD HH:mm')}</p>
                            <p>{content.content}</p>
                            <h3>{content.price}</h3>
                            <hr />
                        </div>
                    ))}
                </>
            )}
        </div>
    );
}

export default CreditHistory;