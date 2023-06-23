import React, { useState } from 'react';
import DaumPostcode from 'react-daum-postcode';
import axios from 'axios';


const AddressSearch = ({ onSelect, visible, setVisible}) => {

    const handleComplete = (data) => {
        let fullAddress = data.address;
        let extraAddress = '';

        if (data.addressType === 'R') {
            if (data.bname !== '') {
                extraAddress += data.bname;
            }
            if (data.buildingName !== '') {
                extraAddress += extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName;
            }
            fullAddress += extraAddress !== '' ? ` (${extraAddress})` : '';
        }

        setVisible(false);

        // Here is the part where we call the Kakao Map API
        axios.get(`https://dapi.kakao.com/v2/local/search/address.json`, {
            params: {
                query: fullAddress
            },
            headers: {
                'Authorization': `KakaoAK 04076bec2077f5bf9e3ea19dbea286d2`
            }
        }).then(response => {
            const { data } = response;
            if (data.documents[0]) {
                const { x, y } = data.documents[0];
                onSelect(fullAddress, x, y);
            } else {
                console.log('No matching address found');
            }
        }).catch(error => {
            console.log(error);
        });
    };

    return (
        <div>
            {visible && (
                <div>
                    <button title="닫기" onClick={() => setVisible(false)}>닫기</button>
                    <div>
                        <DaumPostcode onComplete={handleComplete} height={700} />
                    </div>
                </div>
            )}
        </div>
    );
};

export default AddressSearch;