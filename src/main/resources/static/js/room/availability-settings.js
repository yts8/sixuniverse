(() => {

  // Elements
  const maxDateNumberEl = document.querySelector("#max-date-number");
  const submitBtnEl = document.querySelector("#submit");

  // Handler
  const handleSubmitClick = (e) => {
    if (!(maxDateNumberEl.value > 0)) {
      e.preventDefault();
      alert("최대 숙박 인원은 1 이상이여야 합니다.");
    }
  }

  // Initialize
  const init = () => {
    submitBtnEl.addEventListener("click", handleSubmitClick);
  }
  init();

})();