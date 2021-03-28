(() => {
  $(document).ready(function () {
    const header = "X-CSRF-TOKEN";
    const csrf = document.querySelector("#csrf").value;

    const update = function (status, reservationId) {
      console.log(status)
      console.log(reservationId)

      $.ajax({
        url: '/api/reservation/host/update/' + status,
        data: JSON.stringify({
          reservationId: reservationId,
          status: status
        }),
        type: 'post',
        contentType: 'application/json; charset=utf-8',
        beforeSend: function (xhr) {
          xhr.setRequestHeader(header, csrf);
        },
        success: function (result) {
          if(result==='ok') {
            alert('수락하였습니다.');
          } else {
            alert('거절하였습니다.')
          }
        }
      }).fail(function () {
        alert('오류 발생');
      })
    }

    $('.reservation__host-update-ok').click(function () {
      const reservationId = $(this).next('#reservation-id').val();
      update('update-ok', reservationId);
    })

    $('.reservation__host-update-no').click(function () {
      const reservationId = $(this).prev('#reservation-id').val();
      console.log("click : " + reservationId)
      update('update-no', reservationId);
    })



  });

})()