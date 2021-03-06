(() => {
  $(document).ready(function () {
    const header = "X-CSRF-TOKEN";
    const csrf = document.querySelector("#csrf").value;

    const update = function (status, reservationId) {

      $.ajax({
        url: `//${location.host}/api/reservation/host/update/` + status,
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
          if (result === 'ok') {
            alert('수락하였습니다.');
          } else if (result === 'already') {
            alert('게스트가 변경 요청한 날짜가 이미 예약되어 수락할 수 없습니다.');
          } else if (result === 'no') {
            alert('거절하였습니다.')
          }
          location.reload();
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
      update('update-no', reservationId);
    })


  });

})()