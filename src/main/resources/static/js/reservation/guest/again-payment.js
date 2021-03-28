(() => {
  $(document).ready(function () {
    const header = "X-CSRF-TOKEN";
    const csrf = document.querySelector("#csrf").value;
    const randomNum = Math.floor(Math.random() * 10001);

    // const IMP = window.IMP; // 생략해도 괜찮습니다.
    // IMP.init("imp69268966"); // "imp00000000" 대신 발급받은 "가맹점 식별코드"를 사용합니다.

    // 재결제
    $('.guest-reservation__modal-room-info').on('click', '.guest-reservation__again-payment', function () {

      const paymentId = $('.guest-reservation__again-payment-id').val();
      const price = parseInt($('.guest-reservation__price').val());
      const roomName = $('#room-name').html();
      const reservationId = $('.guest-reservation__cancel-update-id').val();
      const commission = parseInt($('.guest-reservation__commission').val());

      // 취소 후 재결제
      // api 취소와 DB 삭제
      // cancelPay();
      //
      // function cancelPay() {
      //   $.ajax({
      //     url: '/api/reservation/guest/pay/cancel',
      //     type: 'post',
      //     data: JSON.stringify({
      //       imp_uid: paymentId
      //     }),
      //     contentType: 'application/json; charset=utf-8',
      //     beforeSend: function (xhr) {
      //       xhr.setRequestHeader(header, csrf);
      //     }
      //   }).done(function (result) { // 환불 성공시 로직
      //     alert("취소 성공");
      //     cancelAgain();
      //   }).fail(function (error) { // 환불 실패시 로직
      //     alert("취소 실패");
      //   });
      // }

      // ---------------------------------------------------------------------
      cancelAgain();
      // ---------------------------------------------------------------------
      // 결제 취소되면 DB 에서 변경 전 결제 정보 삭제
      function cancelAgain() {
        $.ajax({
          url: `${location.protocol}//${location.host}/api/reservation/pay/cancel/again`,
          type: 'post',
          data: JSON.stringify({
            paymentId: paymentId
          }),
          contentType: 'application/json; charset=utf-8',
          beforeSend: function (xhr) {
            xhr.setRequestHeader(header, csrf);
          },
          success: function () {
            alert('변경 전 결제 정보 삭제 성공');
          }
        }).done(function () {
          // requestPay();
          again();
        }).fail(function () {
          alert('변경 전 결제 정보 삭제 실패');
        });
      }

      // ---------------------------------------------------------------------

      // 재결제
      // function requestPay() {
      //
      //   IMP.request_pay({ // param
      //     pg: "html5_inicis.INIpayTest",
      //     name: roomName,
      //     amount: price + commission,
      //   }, function (rsp) { // callback
      //     if (rsp.success) {
      //      again();
      // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
      // jQuery로 HTTP 요청
      // 이 부분만 주석 처리 후 다른 주석 해제하면 결제 가능
      // -------------------------------------------------------------
      function again() {
        $.ajax({
          url: `${location.protocol}//${location.host}/api/reservation/pay/again`,
          type: 'post',
          data: JSON.stringify({
            paymentId: 'imp_' + randomNum, // 결제 api 사용 시 주석
            // paymentId: rsp.imp_uid, // requestPay() 사용 시 주석 해제
            reservationId: reservationId,
            price: price + commission,  // 게스트 최종결제금액
            commission: commission,
            paymentMethod: 'card'
          }),
          contentType: 'application/json; charset=utf-8',
          beforeSend: function (xhr) {
            xhr.setRequestHeader(header, csrf);
          },
          success: function () {
            alert('재결제 성공');
            location.reload();
          }
        }).fail(function () {
          alert('재결제 오류 발생')
        });
      }

      // -------------------------------------------------------------

      //     } else {
      //       alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
      //     }
      //   });
      // }

    });


  });

})()