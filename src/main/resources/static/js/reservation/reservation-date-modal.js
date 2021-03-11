(() => {
  $(document).ready(function () {

    let cur = -1, prv = -1;
    let cur2 = 0;
    let arrayDays = [];
    let expiryDate = 0;

    $('#update-date').click(function () {
      $('#date-modal').show();

      // $.datepicker._defaults.onAfterUpdate = null;

      // var datepicker__updateDatepicker = $.datepicker._updateDatepicker;
      // $.datepicker._updateDatepicker = function (inst) {
      //   datepicker__updateDatepicker.call(this, inst);
      //
      //   var onAfterUpdate = this._get(inst, 'onAfterUpdate');
      //   if (onAfterUpdate)
      //     onAfterUpdate.apply((inst.input ? inst.input[0] : null),
      //       [(inst.input ? inst.input.val() : ''), inst]);
      // }

      $(function () {

        const minDate = new Date();
        let d1, d2;
        let maxDate = null;
        let maxDate2 = 0;
        expiryDate = $('.reservation__room-expiry-date').val();

        let disabledDayList = $('.reservation__reservation-date-list').val();
        const disabledDays = disabledDayList.substring(1,disabledDayList.length-1).split(", ");


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
            const d = date.getDate() < 10 ? "0" + date.getDate(): date.getDate(), y = date.getFullYear();

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

              $('#range-date').datepicker('option', 'minDate', minDate2);

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
              }

              $('#check-in').val(d1);
              $('#check-out').val(d2);

              $('#range-date').datepicker('option', 'minDate', 0);
              $('#range-date').datepicker('option', 'maxDate', expiryDate == 0 ? null : "+" + expiryDate + "m"); // 개월제한 있을 때 개원제한으로 막기

              cur = -1;

            }

          },

        });

      });

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


    $('.reservation__save-date').click(function () {
      if ($('#check-in').val() != '' && $('#check-out').val() != '') {

        $('.reservation__date').html($('#check-in').val() + ' ~ ' + $('#check-out').val());
        $('.reservation__check-in').val($('#check-in').val());
        $('.reservation__check-out').val($('#check-out').val());
        $('.reservation__date').val(arrayDays);

        $('#date-modal').hide();
      }
    });


    $('.reservation__modal-cancel').click(function () {
      $('#date-modal').hide();
    });

  });

})()