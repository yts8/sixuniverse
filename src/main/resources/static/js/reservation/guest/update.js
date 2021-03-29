(() => {
  $(document).ready(function () {
    $('.guest-reservation-update__back-btn').click(function () {
      history.back();
    });

    const adult = parseInt($('#adult').html());
    const kid = parseInt($('#kid').html());
    const infant = parseInt($('#infant').html());

    const header = "X-CSRF-TOKEN";
    const csrf = document.querySelector("#csrf").value;

    const data = {
      reservationId: $('#reservation-id').val(), checkIn: $('#check-in').html(),
      checkOut: $('#check-out').html(),
      adult: adult, kid: kid, infant: infant
    };
    const json = JSON.stringify(data);

    $('.guest-reservation-update__result-next-btn').click(function () {
      $.ajax({
        url: `//${location.host}/api/reservation/guest/update/complete`,
        data: json,
        type: 'post',
        contentType: 'application/json; charset=utf-8',
        beforeSend: function (xhr) {
          xhr.setRequestHeader(header, csrf);
        },
        success: function () {
          $('.guest-reservation-update__title').html('회원님이 요청하신 변경 내용');
          $('.guest-reservation-update__btn').append('<div class="guest-reservation-update__reply">예약 변경 요청이 호스트님에게 전송되었습니다.</div>')
          $('.guest-reservation-update__back-btn').css('display', 'none');
          $('.guest-reservation-update__result-next-btn').css('display', 'none');
        }

      });
    });


  });

})()