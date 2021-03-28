(() => {

  $(document).ready(function () {

    $('.guest-reservation-cancel__next-btn').click(function () {

      const header = "X-CSRF-TOKEN";
      const csrf = document.querySelector("#csrf").value;

      const paymentId = $('#payment-id').val();

      cancelPay();

      function cancelPay() {
          $.ajax({
            url: '/api/reservation/guest/pay/cancel',
            type: 'post',
            data: JSON.stringify({
              imp_uid: paymentId
            }),
            contentType: 'application/json; charset=utf-8',
            beforeSend: function (xhr) {
              xhr.setRequestHeader(header, csrf);
            }
          }).done(function (result) { // 환불 성공시 로직
            alert("환불 성공");
            cancelDelete();
          }).fail(function (error) { // 환불 실패시 로직
            alert("환불 실패");
          });
      }


      // -------------------
      // cancelDelete()     // 주석 처리 후 다른 주석 해제하면 결제 취소 가능
      // -------------------

      function cancelDelete() {
          $.ajax({
            url: '/api/reservation/guest/cancel',
            data: JSON.stringify({
              reservationId: $('.guest-reservation-cancel__reservation-id').val(),
              paymentId: paymentId
            }),
            type: 'post',
            contentType: 'application/json; charset=utf-8',
            beforeSend: function (xhr) {
              xhr.setRequestHeader(header, csrf);
            },
            success: function () {
              $('.guest-reservation-cancel__title').html('예약이 취소되었습니다.');
              $('.guest-reservation-cancel__back-btn').css('display', 'none');
              $('.guest-reservation-cancel__next-btn').css('display', 'none');
            }
          }).fail(function (error) { // 환불 실패시 로직
            alert("DB 삭제 실패" + error);
          });
      }


    })

  });

})()