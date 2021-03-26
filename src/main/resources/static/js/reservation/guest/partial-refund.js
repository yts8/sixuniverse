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

      alert(paymentId + ", " + price + ", " + commission)
      refund();

      // 부분환불
      // cancelPay();
      //
      // function cancelPay() {
      //   $.ajax({
      //     url: '/api/reservation/pay/partial/refund',
      //     type: 'post',
      //     data: JSON.stringify({
      //       paymentId: paymentId
      //       price: price + commission
      //     }),
      //     contentType: 'application/json; charset=utf-8',
      //     beforeSend: function (xhr) {
      //       xhr.setRequestHeader(header, csrf);
      //     }
      //   }).done(function (result) { // 환불 성공시 로직
      //     alert("환불 성공");
      //     refund();
      //   }).fail(function (error) { // 환불 실패시 로직
      //     alert("환불 실패");
      //   });
      // }

      // 이 부분만 주석 처리 후 다른 주석 해제하면 결제 가능
      // -------------------------------------------------------------
      function refund() {
        $.ajax({
          url: '/api/reservation/partial/refund',
          type: 'post',
          data: JSON.stringify({
            paymentId: paymentId,
            reservationId: reservationId,
            price: price,
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