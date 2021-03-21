(() => {
  const header = "X-CSRF-TOKEN";
  const csrf = document.querySelector("#csrf").value;

  $(document).ready(function () {

    $('.reservation__btn').click(function () {

      const data = {
        // imp_uid: rsp.imp_uid,
        // paid_amount: rsp.paid_amount,
        // pay_method: rsp.pay_method,
        // name: rsp.name
        paymentId: 11111111,
        price: 40000,
        paymentMethod: 'card'
      }

      $('.reservation__payment').val(data);

      $('#reservation-fr').submit();


    });

  });


  var IMP = window.IMP; // 생략해도 괜찮습니다.
  IMP.init("imp69268966"); // "imp00000000" 대신 발급받은 "가맹점 식별코드"를 사용합니다.


  // const json1 = JSON.stringify(data1);

  // function requestPay() {
  var name = document.getElementById('roomName');
  var price = document.getElementById('reservation__oneday-price');
  // var reservationId = document.getElementById()

//     IMP.request_pay({ // param
//       pg: "html5_inicis.INIpayTest",
//       pay_method: "card",
//       name: name,
//       amount: price,
//       // buyer_email: "yun07003@naver.com",
// //           buyer_name: "홍길동",
// //           buyer_tel: "010-4242-4242",
// //           buyer_addr: "서울특별시 강남구 신사동",
// //           buyer_postcode: "01181"
//     }, function (rsp) { // callback
//       if (rsp.success) {
  // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
  // jQuery로 HTTP 요청
  // const data = JSON.stringify({
  //   // imp_uid: rsp.imp_uid,
  //   // paid_amount: rsp.paid_amount,
  //   // pay_method: rsp.pay_method,
  //   // name: rsp.name
  //   paymentId: 11111111,
  //   price: 40000,
  //   paymentMethod: 'card'
  // })
  //
  // $('.reservation__payment').val(data);

  //     .done(function (data) {
  //   // 가맹점 서버 결제 API 성공시 로직
  // })
  // } else {
  //   alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
  // }
  // });
  // }

})()
