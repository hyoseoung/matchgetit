import React, { useState } from 'react';

function AddressSearch() {
  const [address, setAddress] = useState('');
  const [latitude, setLatitude] = useState('');
  const [longitude, setLongitude] = useState('');

  const handleSearch = () => {
    new window.daum.Postcode({
      oncomplete: function (data) {
        var addr = data.address; // 최종 주소 변수

        // 주소로 상세 정보를 검색
        new window.daum.maps.services.Geocoder().addressSearch(addr, function (
          results,
          status
        ) {
          // 정상적으로 검색이 완료됐으면
          if (status === window.daum.maps.services.Status.OK) {
            var result = results[0];
            var coords = new window.daum.maps.LatLng(result.y, result.x);
            setAddress(result.address.address_name);
            setLatitude(coords.getLat().toString());
            setLongitude(coords.getLng().toString());
          }
        });
      },
    }).open();
  };

  return (
    <div>
      <button onClick={handleSearch}>주소 검색</button>
      <p>주소: {address}</p>
      <p>위도: {latitude}</p>
      <p>경도: {longitude}</p>
    </div>
  );
}

export default AddressSearch;