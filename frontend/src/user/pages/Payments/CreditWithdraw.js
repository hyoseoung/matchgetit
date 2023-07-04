import React, {useEffect, useState} from 'react';
import '../Payments/CreditWithdraw.css';

function CreditWithdraw (){
    const [searchTerm, setSearchTerm] = useState('');
    const [searchCondition, setSearchCondition] = useState('all');
    const handleSearchChange = (e) => {
        setSearchTerm(e.target.value);
    };
    const handleSearchConditionChange = (e) => {
        setSearchCondition(e.target.value);
    };

    return (
        <div>
            <div className="creditWithdraw">
                <h2>크레딧 출금</h2>
            </div>
            <div className="withdrawable_Amount">
                <h4>출금 가능 크레딧</h4>
                <h2>900,000</h2>
                <input type={"button"} className="WithdrawBtn" value="출금"/>
            </div>
            <div className="account_Info">
                <h4>은행명</h4>
                <select className="bankName" value={searchCondition} onChange={handleSearchConditionChange}>
                    <option value="국민은행">국민은행</option>
                    <option value="신한은행">신한은행</option>
                    <option value="우리은행">우리은행</option>
                    <option value="KEB하나은행">KEB하나은행</option>
                    <option value="농협">농협</option>
                    <option value="SC제일은행">SC제일은행</option>
                </select>
                <br></br>
                <h4>계좌번호</h4>
                <input type="text" className="accountNumber" /><br></br>
                <h4>예금주</h4>
                <input type="text" className="accountHolder"/>
            </div>
            <div className="withdrawComp">
                <input type="submit" className="withdrawCompBtn" value="크레딧 출금" />
            </div>
        </div>
    );
};

export default CreditWithdraw;