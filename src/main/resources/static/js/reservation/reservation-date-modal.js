(() => {
  $(document).ready(function () {

    let cur = -1, prv = -1;
    let cur2 = 0;
    let arrayDays = [];
    let expiryDate = 0;

    // const header = "X-CSRF-TOKEN";
    // const csrf = document.querySelector("#csrf").value;

    // const data = $('.reservation__check-in').val() == '' ? '1999-09-09' : $('.reservation__check-in').val();
    // const json = JSON.stringify(data);

    function getToday() {
      const date = new Date();
      return date.getFullYear() + "-" + ("0" + (date.getMonth() + 1)).slice(-2) + "-" + ("0" + date.getDate()).slice(-2);
    }

    $('#update-date').click(function () {
      if ($('#update-check-in').html() == getToday()) {
        $('.guest-reservation-update__msg').html('예약 당일에는 날짜를 변경할 수 없습니다.');
        $('.guest-reservation-update__reservation-date').css('box-shadow', '0 0 5px red');
      } else {
        $('#date-modal').show();

        $(function () {

          const minDate = new Date();
          let d1, d2;
          let maxDate = null;
          let maxDate2 = 0;
          let maxDateNum = $('.reservation__room-max-date').val();
          expiryDate = $('.reservation__room-expiry-date').val();

          let disabledDayList = $('.reservation__reservation-date-list').val();
          const disabledDays = disabledDayList.substring(1, disabledDayList.length - 1).split(", ");

          $('#range-date').datepicker({
            numberOfMonths: [1, 2],
            showMonthAfterYear: true,
            dateFormat: 'yy-mm-dd',
            yearSuffix: "년",
            monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
            minDate: minDate,
            maxDate: expiryDate == 0 ? null : "+" + expiryDate + "m", // 숙소 expiry_date 가져오기

            beforeShowDay: function disableAllTheseDays(date) {

              const m = date.getMonth() < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
              const d = date.getDate() < 10 ? "0" + date.getDate() : date.getDate(), y = date.getFullYear();

              for (i = 0; i < disabledDays.length; i++) {
                if ($.inArray(y + '-' + m + '-' + d, disabledDays) != -1) {
                  return [false];
                }
              }

              return [true, ((date.getTime() >= Math.min(prv, cur2) && date.getTime() <= Math.max(prv, cur2)) ? 'date-range-selected' : '')];
            },

            onSelect: function (dateText, inst) {

              prv = cur;
              cur = (new Date(inst.selectedYear, inst.selectedMonth, inst.selectedDay)).getTime();
              cur2 = cur;

              if (prv == -1 || prv == cur) {

                const minDate2 = new Date(inst.selectedYear, inst.selectedMonth, inst.selectedDay);
                // const maxTest = minDate2.setDate(minDate2.getDate() + maxDateNum);
                $('#range-date').datepicker('option', 'minDate', minDate2);
                // $('#range-date').datepicker('option', 'maxDate', maxTest);

                // console.log("maxTest : " + maxTest);

                prv = cur;
                $('#check-in').val(dateText);
                $('#check-out').val('');

                const maxCur = (new Date(inst.selectedYear, inst.selectedMonth + 1, inst.selectedDay)).getTime();

                $.each(disabledDays, function (index, item) {
                  maxDate = new Date(item);

                  maxDate2 = (new Date(maxDate.getFullYear(), maxDate.getMonth() + 1, maxDate.getDate())).getTime();

                  if (maxCur < maxDate2) {

                    $('#range-date').datepicker('option', 'maxDate', maxDate);

                    return false;
                  }

                });

              } else {
                d1 = $.datepicker.formatDate('yy-mm-dd', new Date(Math.min(prv, cur)), {});
                d2 = $.datepicker.formatDate('yy-mm-dd', new Date(Math.max(prv, cur)), {});

                const minus = ((new Date(d2).getTime()) - (new Date(d1).getTime())) / 1000 / 60 / 60 / 24;

                for (let i = 0; i <= minus; i++) {
                  const testD1 = new Date(d1);
                  const testDate = new Date(testD1.setDate(testD1.getDate() + i));

                  arrayDays[i] = moment(testDate).format('YYYY-MM-DD');
                  $('.reservation__date').val(arrayDays);
                }

                $('#check-in').val(d1);
                $('#check-out').val(d2);

                $('#range-date').datepicker('option', 'minDate', 0);
                $('#range-date').datepicker('option', 'maxDate', expiryDate == 0 ? null : "+" + expiryDate + "m"); // 개월제한 있을 때 개원제한으로 막기

                cur = -1;

              }

            }

          });

        });
      }

      // console.log('테스트');

      // $.ajax({
      //   url: "/api/reservation/guest/update/today",
      //   data: json,
      //   type: 'post',
      //   contentType: 'application/json; charset=utf-8',
      //   beforeSend: function (xhr) {
      //     xhr.setRequestHeader(header, csrf);
      //   },
      //   success: function (result) {
      //     if (result) {
      //       $('.guest-reservation-update__msg').html('예약 당일에는 날짜를 변경할 수 없습니다.');
      //       $('.guest-reservation-update__reservation-date').css('box-shadow', '0 0 5px red');
      //     } else {


      //   }
      // },
      // error: function () {
      //   console.log('ajax 실패');
      // }

      // })

    });


    $('.reservation__delete-date').click(function () {

      $('#check-in').val('');
      $('#check-out').val('');
      $('#range-date').datepicker('option', 'minDate', 0);
      $('#range-date').datepicker('option', 'maxDate', expiryDate == 0 ? null : "+" + expiryDate + "m");
      $('#range-date').datepicker('setDate', 0);
      $('.ui-datepicker td').removeClass('date-range-selected');

      cur = -1;

    });


    $('.reservation__modal-cancel').click(function () {
      $('#date-modal').hide();
    });

  });


})()