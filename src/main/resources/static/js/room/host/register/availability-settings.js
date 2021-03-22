(() => {
  // 숙박기간
  $(document).ready(function () {
    let num = 0;

    $('#date-plus').bind('click', function () {
      num = parseInt($('#max-date-number').val());
      ++num;
      $('#max-date-number').val(num);
    });

    $('#date-minus').bind('click', function () {
      num = parseInt($('#max-date-number').val());
      if (num > 0) {
        --num;
      }
      $('#max-date-number').val(num);
    });
  });


  // Elements
  const maxDateNumberEl = document.querySelector("#max-date-number");
  const submitBtnEl = document.querySelector("#submit");

  // Handler
  const handleSubmitClick = (e) => {
    if (!(maxDateNumberEl.value > 0)) {
      e.preventDefault();
      alert("최소 숙박기간은 1 이상입니다");
    }
  }

  // Initialize
  const init = () => {
    submitBtnEl.addEventListener("click", handleSubmitClick);
  }
  init();

})();