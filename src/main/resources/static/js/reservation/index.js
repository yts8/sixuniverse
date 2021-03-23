(() => {

  $(document).ready(function () {

    $('.reservation__btn').click(function () {

      const name = $('#roomName').html();
      const price = parseInt($('#total-price').html());
      const commission = price * 0.1;

      const IMP = window.IMP; // 생략해도 괜찮습니다.
      IMP.init("imp69268966"); // "imp00000000" 대신 발급받은 "가맹점 식별코드"를 사용합니다.

      // requestPay();
      //
      // function requestPay() {
      //
      //   IMP.request_pay({ // param
      //     pg: "html5_inicis.INIpayTest",
      //     name: name,
      //     // amount: price + commission,
      //     amount: 100
      //   }, function (rsp) { // callback
      //     if (rsp.success) {
      //       // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
      //       // jQuery로 HTTP 요청
      //       // 실제 결제한 후 정보 저장 지우지마시오
      //       const data = JSON.stringify({
      //         imp_uid: rsp.imp_uid,
      //         paid_amount: rsp.paid_amount,
      //         commission: commission,
      //         pay_method: rsp.pay_method
      //       })

            // 이 부분만 주석 처리 후 다른 주석 해제하면 결제 가능
            // -------------------------------------------------------------
            const randomNum = Math.floor(Math.random() * 10001);

            const data = JSON.stringify({
              imp_uid: 'imp_' + randomNum,
              paid_amount: price + commission,  // 게스트 최종결제금액
              commission: commission,   // 수수료
              pay_method: 'card'
            })
            // -------------------------------------------------------------

            $('.reservation__payment').val(data);
            $('#reservation-fr').submit();


  //         } else {
  //           alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
  //         }
  //       });
  //     }

    });

  });


})()
