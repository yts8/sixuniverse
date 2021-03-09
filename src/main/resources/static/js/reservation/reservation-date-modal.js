(() => {
  $(document).ready(function () {

    var cur = -1, prv = -1;
    let cur2 = 0;


    $('#update-date').bind('click', function () {
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

        var minDate = new Date();
        var d1, d2;
        var disabledDays = ["2021-3-19", "2021-3-20", "2021-3-24", "2021-3-25", "2021-3-26"];
        let maxDate = null;
        let maxDate2 = 0;


        $('#range-date').datepicker({
          numberOfMonths: [1, 2],
          showMonthAfterYear: true,
          dateFormat: 'yy-mm-dd',
          yearSuffix: "년",
          monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
          dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
          minDate: minDate,
          maxDate: "+3m", // 숙소 expiry_date 가져오기

          beforeShowDay: function disableAllTheseDays(date) {

            var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();

            for (i = 0; i < disabledDays.length; i++) {
              if ($.inArray(y + '-' + (m + 1) + '-' + d, disabledDays) != -1) {
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

              $('#check-in').val(d1);
              $('#check-out').val(d2);

              $('#range-date').datepicker('option', 'minDate', 0);
              $('#range-date').datepicker('option', 'maxDate', "+3m"); // 개월제한 있을 때 개원제한으로 막기

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
      $('#range-date').datepicker('option', 'maxDate', null);
      $('#range-date').datepicker('setDate', 0);
      $('.ui-datepicker td').removeClass('date-range-selected');

      cur = -1;




    });


    $('.reservation__save-date').click(function () {
      if($('#check-in').val() !='' && $('#check-out').val()!='') {

        $('.reservation__date').html($('#check-in').val() + ' ~ ' + $('#check-out').val());
        $('.reservation__check-in').val($('#check-in').val());
        $('.reservation__check-out').val($('#check-out').val());
        $('#date-modal').hide();
      }
    });


    $('.reservation__modal-cancel').bind('click', function () {
      $('#date-modal').hide();
    });

  });

})()