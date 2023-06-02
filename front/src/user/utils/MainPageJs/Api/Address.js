var friendlyGeocoder = new daum.maps.services.Geocoder();
    function fAddrfunc() {
      new daum.Postcode({
        oncomplete: function(data) {
          var addr = data.address; // 최종 주소 변수

          // 주소로 상세 정보를 검색
          friendlyGeocoder.addressSearch(data.address, function(results, status) {
            // 정상적으로 검색이 완료됐으면
            if (status === daum.maps.services.Status.OK) {
              var result = results[0];
              var coords = new daum.maps.LatLng(result.y, result.x);
              document.getElementById('fInputAddress').value = result.address.address_name;
              document.getElementById('fInputLat').value = coords.getLng().toString();
              document.getElementById('fInputLon').value = coords.getLat().toString();
            }
          });
        }
      }).open();
    }
    var leagueGeocode = new daum.maps.services.Geocoder();
    function lAddrfunc() {
      new daum.Postcode({
        oncomplete: function(data) {
          var addr = data.address; // 최종 주소 변수

          // 주소로 상세 정보를 검색
          leagueGeocode.addressSearch(data.address, function(results, status) {
            // 정상적으로 검색이 완료됐으면
            if (status === daum.maps.services.Status.OK) {
              var result = results[0];
              var coords = new daum.maps.LatLng(result.y, result.x);
              document.getElementById('lInputAddress').value = result.address.address_name;
              document.getElementById('lInputLat').value = coords.getLng().toString();
              document.getElementById('lInputLon').value = coords.getLat().toString();
            }
          });
        }
      }).open();
    }