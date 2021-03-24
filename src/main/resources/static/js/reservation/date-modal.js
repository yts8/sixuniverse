(() => {
  $(document).ready(function () {

    let cur = -1, prv = -1;
    let cur2 = 0;
    let arrayDays = [];

    $('.header__finder__inner').click(function () {
      $('.header__search-expand-container').show()
      $('.header__search-container').hide()
    })

    $('.header__search-select-date').click(function () {

        $('#header-date-modal').show();

        $(function () {

          const minDate = new Date();
          let d1, d2;

          $('#header-range-date').datepicker({
            numberOfMonths: [1, 2],
            showMonthAfterYear: true,
            dateFormat: 'yy-mm-dd',
            yearSuffix: "년",
            monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
            minDate: minDate,
            beforeShowDay: function disableAllTheseDays(date) {
              // const m = date.getMonth() < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
              // const d = date.getDate() < 10 ? "0" + date.getDate() : date.getDate(), y = date.getFullYear();
              //
              // for (i = 0; i < disabledDays.length; i++) {
              //   if ($.inArray(y + '-' + m + '-' + d, disabledDays) != -1) {
              //     return [false];
              //   }
              // }
              return [true, ((date.getTime() >= Math.min(prv, cur2) && date.getTime() <= Math.max(prv, cur2)) ? 'date-range-selected' : '')];
            },
            onSelect: function (dateText, inst) {

              prv = cur;
              cur = (new Date(inst.selectedYear, inst.selectedMonth, inst.selectedDay)).getTime();
              cur2 = cur;

              if (prv == -1 || prv == cur) {

                const minDate2 = new Date(inst.selectedYear, inst.selectedMonth, inst.selectedDay);

                $('#header-range-date').datepicker('option', 'minDate', minDate2);

                prv = cur;
                $('#header-check-in').html(dateText);
                $('#header-check-out').html('날짜추가');

              } else {
                d1 = $.datepicker.formatDate('yy-mm-dd', new Date(Math.min(prv, cur)), {});
                d2 = $.datepicker.formatDate('yy-mm-dd', new Date(Math.max(prv, cur)), {});

                const minus = ((new Date(d2).getTime()) - (new Date(d1).getTime())) / 1000 / 60 / 60 / 24;

                for (let i = 0; i <= minus; i++) {
                  const testD1 = new Date(d1);
                  const testDate = new Date(testD1.setDate(testD1.getDate() + i));

                  arrayDays[i] = moment(testDate).format('YYYY-MM-DD');
                }

                $('#header-check-in').html(d1);
                $('#header-check-out').html(d2);

                $('#header-range-date').datepicker('option', 'minDate', 0);

                cur = -1;

              }

            }

          });

        });


    });

    $('.header__modal').click(function (e) {
      const container = $('.header__modal');
      if(e.target == e.currentTarget) {
        container.hide();
        return;
      }

    });

    $('.header__modal-cancel').click(function () {
      $('#header-date-modal').hide();
    });

  });


})()