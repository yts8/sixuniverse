(() => {

  // 목록 모달창
  $(".reservation-review__review-all").click(function () {
    $(".reservation-review__modal").fadeIn();
  });
  $(".reservation-review__update-title button").click(function () {
    $(".reservation-review__modal").fadeOut();
  });

  // 삭제 모달창
  $(".g-review-by__modal-delete").click(function (e) {
    $(".g-review-by__modal").fadeIn(100);
    document.querySelector(".review-id").value = e.target.dataset.id;
  });
  $(".g-review-by__delete-title button").click(function () {
    $(".g-review-by__modal").fadeOut(100);
  });

  // 청결도, 위치, 서비스 별점
  const starScoreCleanEl = document.querySelector(".star-clean-js");
  const starScoreLocationEl = document.querySelector(".star-location-js");
  const starScoreServiceEl = document.querySelector(".star-service-js");

  $('.review-form__star-part-clean span').click(function () {
    const starScoreClean = $(this).data('clean');
    starScoreCleanEl.value = starScoreClean;

    $(this).parent().children('span').removeClass('on');
    $(this).addClass('on').prevAll('span').addClass('on');
    return false;
  });

  $('.review-form__star-part-location span').click(function () {
    const starScoreLocation = $(this).data('location');
    starScoreLocationEl.value = starScoreLocation;

    $(this).parent().children('span').removeClass('on');
    $(this).addClass('on').prevAll('span').addClass('on');
    return false;
  });

  $('.review-form__star-part-service span').click(function () {
    const starScoreService = $(this).data('service');
    starScoreServiceEl.value = starScoreService;

    $(this).parent().children('span').removeClass('on');
    $(this).addClass('on').prevAll('span').addClass('on');
    return false;
  });

  // 프로그레스바 게이지
  $('.reservation-review__gauge').each(function () {
    var $this = $(this);
    var per = $this.attr('per');
    $this.css('width', per + "%");
  });

  // 호스트 답글 쓰기/보기 click 이벤트
  $('.on-reply').click(function (e) {
    const id = e.target.dataset.id;
    if ($(`.reply-form-js${id}`).css("display") === "none") {
      $(`.reply-form-js${id}`).show();
      $(`.reply-content-js${id}`).show();
      $(`.reply-update-form-js${id}`).hide();
    } else {
      $(`.reply-form-js${id}`).hide();
    }
  });

  $('.review-update-form-btn-js').click(function (e) {
    const id = e.target.dataset.id;
    $(`.reply-update-form-js${id}`).show();
    $(`.reply-content-js${id}`).hide();
    $(`.review-update-form-btn-js`).hide();
    $(`.g-review-by__modal-delete`).hide();
  })

})();