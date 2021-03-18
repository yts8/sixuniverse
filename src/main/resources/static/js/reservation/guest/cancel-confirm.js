(() => {

  $(document).ready(function () {

    $('.guest-reservation-cancel__next-btn').click(function () {

      const header = "X-CSRF-TOKEN";
      const csrf = document.querySelector("#csrf").value;

      const reservationId = $('.guest-reservation-cancel__reservation-id').val();
      const json = JSON.stringify(reservationId);

      $.ajax({
        url: '/api/reservation/guest/cancel',
        data: json,
        type: 'post',
        contentType:'application/json; charset=utf-8',
        beforeSend: function(xhr){
          xhr.setRequestHeader(header, csrf);
        },
        success: function() {
          $('.guest-reservation-cancel__title').html('예약이 취소되었습니다.');
          $('.guest-reservation-cancel__back-btn').css('display', 'none');
          $('.guest-reservation-cancel__next-btn').css('display', 'none');

        }
        });

    })

  });

})()