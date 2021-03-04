(() => {
  $(document).ready(function () {
    $('#update-date').bind('click', function () {
      $('#date-modal').show();

      $.datepicker._defaults.onAfterUpdate = null;

      var datepicker__updateDatepicker = $.datepicker._updateDatepicker;
      $.datepicker._updateDatepicker = function( inst ) {
        datepicker__updateDatepicker.call( this, inst );

        var onAfterUpdate = this._get(inst, 'onAfterUpdate');
        if (onAfterUpdate)
          onAfterUpdate.apply((inst.input ? inst.input[0] : null),
            [(inst.input ? inst.input.val() : ''), inst]);
      }


      $(function() {

        var cur = -1, prv = -1;
        var minDate = new Date();
        var d1, d2;

        $('#range-date div')
          .datepicker({
            showAnim: "show",
            numberOfMonths: 2,
            showMonthAfterYear: true,
            dateFormat: 'yy-mm-dd',
            yearSuffix: "년",
            monthNames: [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ],
            dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
            minDate: minDate,

            beforeShowDay: function ( date ) {

              return [true, ( (date.getTime() >= Math.min(prv, cur) && date.getTime() <= Math.max(prv, cur)) ? 'date-range-selected' : '')];
            },



            onSelect: function ( dateText, inst ) {

              prv = cur;
              cur = (new Date(inst.selectedYear, inst.selectedMonth, inst.selectedDay)).getTime();
              if ( prv == -1 || prv == cur ) {
                prv = cur;
                $('#check-in').val( dateText );
                $('#check-out').val('');

              } else {
                d1 = $.datepicker.formatDate( 'yy-mm-dd', new Date(Math.min(prv,cur)), {} );
                d2 = $.datepicker.formatDate( 'yy-mm-dd', new Date(Math.max(prv,cur)), {} );
                $('#check-in').val( d1 );
                $('#check-out').val( d2 );
              }
            },


            onChangeMonthYear: function ( year, month, inst ) {
              //prv = cur = -1;
            },

            // onAfterUpdate: function ( inst ) {
            //   $('<button type="button" class="ui-datepicker-close ui-state-default ui-priority-primary ui-corner-all" data-handler="hide" data-event="click">Done</button>')
            //     .appendTo($('#range-date div .ui-datepicker-buttonpane'))
            //     .on('click', function () { $('#date-modal').hide(); });
            // }
          }).showAnim();
          // .position({
          //   my: 'left top',
          //   at: 'left bottom',
          //   of: $('#range-date input')
          // });

        // $('#range-date input').on('focus', function (e) {
        //   var v = this.value,
        //     d;
        //
        //   try {
        //     if ( v.indexOf(' - ') > -1 ) {
        //       d = v.split(' - ');
        //
        //       prv = $.datepicker.parseDate( 'yy-mm-dd', d[0] ).getTime();
        //       cur = $.datepicker.parseDate( 'yy-mm-dd', d[1] ).getTime();
        //
        //     } else if ( v.length > 0 ) {
        //       prv = cur = $.datepicker.parseDate( 'yy-mm-dd', v ).getTime();
        //     }
        //   } catch ( e ) {
        //     cur = prv = -1;
        //   }
        //
        //   if ( cur > -1 )
        //     $('#range-date div').datepicker('setDate', new Date(cur));
        //
        //   $('#range-date div').datepicker('refresh').show();
        // });

      });




    });


    $('.reservation__delete-date').click(function () {
      $('#check-in').val('');
      $('#check-out').val('');
      $(".ui-state-default").removeClass("ui-state-active");
      $(".ui-datepicker-calendar td").removeClass("date-range-selected");



    });

    $('.reservation__save-date').click(function () {
      $('.reservation__date').html($('#check-in').val() + ' ~ ' + $('#check-out').val());

      $('#date-modal').hide();
    });


  $('.reservation__modal-cancel').bind('click', function () {
      $('#date-modal').hide();
    });

  });

})()