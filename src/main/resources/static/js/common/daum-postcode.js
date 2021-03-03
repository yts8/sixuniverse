// <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

(() => {

  // Element
  const postcodeSearchBtnEl = document.querySelector(".postcode-search-btn");
  const zipcodeEl = document.querySelector("#zipcode");
  const addressEl = document.querySelector("#address");
  const subAddressEl = document.querySelector("#sub-address");

  // Handle
  const handlePostcodeSearchBtnClick = () => {
    new daum.Postcode({
      oncomplete(data) {
        let addr = ''; // 주소 변수
        if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
          addr = data.roadAddress;
        } else { // 사용자가 지번 주소를 선택했을 경우(J)
          addr = data.jibunAddress;
        }

        zipcodeEl.value = data.zonecode;
        addressEl.value = addr;
        subAddressEl.focus();
      }
    }).open();
  }

  // Initialize
  const init = () => {
    postcodeSearchBtnEl.addEventListener("click", handlePostcodeSearchBtnClick);
  }
  init();

})();