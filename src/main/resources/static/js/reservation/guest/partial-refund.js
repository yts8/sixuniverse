(() => {
  $(document).ready(function () {
    const header = "X-CSRF-TOKEN";
    const csrf = document.querySelector("#csrf").value;

    // const IMP = window.IMP; // 생략해도 괜찮습니다.
    // IMP.init("imp69268966"); // "imp00000000" 대신 발급받은 "가맹점 식별코드"를 사용합니다.

    // 부분환불
    $('.guest-reservation__modal-room-info').on('click', '.guest-reservation__partial-refund', function () {

      const paymentId = $('.guest-reservation__again-payment-id').val();
      const price = parseInt($('.guest-reservation__price').val());
      const roomName = $('#room-name').html();
      const reservationId = $('.guest-reservation__cancel-update-id').val();
      const commission = parseInt($('.guest-reservation__commission').val());
      const refundPrice = parseInt($('.guest-reservation__refund-price').val())

      // refund();

      // 부분환불
      // 부분취소는 api 에서 카드사에 취소처리가 되지 않음(테스트 결제 모드에서는 부분 취소가 실질적으로 이루어지지 않음)
      // cancelPay();
      //
      // function cancelPay() {
      //   $.ajax({
      //     url: '/api/reservation/pay/partial/refund',
      //     type: 'post',
      //     data: JSON.stringify({
      //       imp_uid: paymentId,
      //       amount: refundPrice
      //     }),
      //     contentType: 'application/json; charset=utf-8',
      //     beforeSend: function (xhr) {
      //       xhr.setRequestHeader(header, csrf);
      //     }
      //   }).done(function (result) { // 환불 성공시 로직
      //     alert("환불 성공");
      refund();
      //   }).fail(function (error) { // 환불 실패시 로직
      //     alert("환불 실패");
      //   });
      // }

      // -------------------------------------------------------------
      function refund() {
        $.ajax({
          url: `${location.protocol}//${location.host}/api/reservation/partial/refund`,
          type: 'post',
          data: JSON.stringify({
            paymentId: paymentId,
            reservationId: reservationId,
            price: price + commission,
            commission: commission,
            paymentMethod: 'card'
          }),
          contentType: 'application/json; charset=utf-8',
          beforeSend: function (xhr) {
            xhr.setRequestHeader(header, csrf);
          },
          success: function () {
            alert('부분환불 성공');
            location.reload();
          }
        }).fail(function () {
          alert('부분환불 오류 발생')
        });
      }

      // -------------------------------------------------------------
    });


  });

})()