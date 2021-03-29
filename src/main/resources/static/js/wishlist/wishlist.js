(() => {

  // 지도 API 가져오기
  const container = document.getElementById('map'); // 지도를 표시할 div
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
    level: 3, // 지도의 확대 레벨
    scrollwheel: false // 지도 스크롤 기능 막기
  };
  // 지도를 표시할 div 와  지도 옵션으로  지도 생성
  const map = new kakao.maps.Map(container, options);

  // 목록 모달창
  $(function () {
    $(".wishlist__make-wishlist-folder").click(function () {
      $(".wishlist__modal").fadeIn();
    });
    $(".wishlist__update-title button").click(function () {
      $(".wishlist__modal").fadeOut();
    });
  });
})();