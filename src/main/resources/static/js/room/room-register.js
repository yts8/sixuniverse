(() => {
  $(document).ready(function () {
    let num = 0;


    $('#people-plus').bind('click', function () {
      num = parseInt($('#max-people').val());
      ++num;
      $('#max-people').val(num);
    });

    $('#people-minus').bind('click', function () {
      num = parseInt($('#max-people').val());
      if (num > 0) {
        --num;
      }
      $('#max-people').val(num);
    });


    $('#bath-plus').bind('click', function () {
      num = parseInt($('#bath-count').val());
      ++num;
      $('#bath-count').val(num);
    });

    $('#bath-minus').bind('click', function () {
      num = parseInt($('#bath-count').val());
      if (num > 0) {
        --num;
      }
      $('#bath-count').val(num);
    });

    $('#bedRoom-plus').bind('click', function () {
      num = parseInt($('#bedroom-count').val());
      ++num;
      $('#bedroom-count').val(num);
    });

    $('#bedRoom-minus').bind('click', function () {
      num = parseInt($('#bedroom-count').val());
      if (num > 0) {
        --num;
      }
      $('#bedroom-count').val(num);
    });

    $('#bed-plus').bind('click', function () {
      num = parseInt($('#bed-count').val());
      ++num;
      $('#bed-count').val(num);
    });

    $('#bed-minus').bind('click', function () {
      num = parseInt($('#bed-count').val());
      if (num > 0) {
        --num;
      }
      $('#bed-count').val(num);
    });


  });

})()
