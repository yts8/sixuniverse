(() => {
  $(document).ready(function () {
    $('.guest-reservation-update__next-btn').click(function () {
      const reservationId = $('#reservationId').val();

      const header = "X-CSRF-TOKEN";
      const csrf = document.querySelector("#csrf").value;

      $.ajax({
        url: `${location.protocol}//${location.host}/api/reservation/update/check`,
        data: JSON.stringify({
          reservationId: $('#reservationId').val(),
          checkIn: $('.reservation__check-in').val(),
          checkOut: $('.reservation__check-out').val(),
          adult: $('.reservation__adult').val(),
          kid: $('.reservation__kid').val(),
          infant: $('.reservation__infant').val(),
        }),
        type: 'post',
        contentType: 'application/json; charset=utf-8',
        beforeSend: function (xhr) {
          xhr.setRequestHeader(header, csrf);
        },
        success: function (data) {
          if (data) {
            $('.guest-reservation-update__fr').submit();
          } else {
            alert('변경할 내용을 입력해주세요.');
            return;
          }
        }
      })
    });

  });

})()