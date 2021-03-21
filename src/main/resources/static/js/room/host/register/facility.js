(() => {
  // Elements
  const facilityEls = document.querySelectorAll('input[name="name"]');
  const submitBtnEl = document.querySelector("#submit");

  // Handler
  const handleSubmitClick = (e) => {

    let isSubmit = false;

    for (const facilityEl of facilityEls) {
      if (facilityEl.checked) {
        isSubmit = true;
        break;
      }
    }
    if (!isSubmit) {
      alert("1개 이상 선택해야 합니다.");
      e.preventDefault();
    }
  }

  // Initialize
  const init = () => {
    submitBtnEl.addEventListener("click", handleSubmitClick);
  }
  init();
})()