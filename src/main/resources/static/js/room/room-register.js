(() => {
  $(document).ready(function () {
    let num = 0;


      $('#people-plus').bind('click', function () {
        num = parseInt($('#maxPeople').val());
          ++num;
        $('#maxPeople').val(num);
      });

    $('#people-minus').bind('click', function () {
      num = parseInt($('#maxPeople').val());
      if (num > 0) {
        --num;
      }
      $('#maxPeople').val(num);
    });


    $('#bath-plus').bind('click', function () {
      num = parseInt($('#bathCount').val());
      ++num;
      $('#bathCount').val(num);
    });

    $('#bath-minus').bind('click', function () {
      num = parseInt($('#bathCount').val());
      if (num > 0) {
        --num;
      }
      $('#bathCount').val(num);
    });

    $('#bedRoom-plus').bind('click', function () {
      num = parseInt($('#bedRoomCount').val());
      ++num;
      $('#bedRoomCount').val(num);
    });

    $('#bedRoom-minus').bind('click', function () {
      num = parseInt($('#bedRoomCount').val());
      if (num > 0) {
        --num;
      }
      $('#bedRoomCount').val(num);
    });

    $('#bed-plus').bind('click', function () {
      num = parseInt($('#bedCount').val());
      ++num;
      $('#bedCount').val(num);
    });

    $('#bed-minus').bind('click', function () {
      num = parseInt($('#bedCount').val());
      if (num > 0) {
        --num;
      }
      $('#bedCount').val(num);
    });


  });

})()
