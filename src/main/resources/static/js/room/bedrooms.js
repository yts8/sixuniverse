(() => {

  // Elements
  const maxPeopleEl = document.querySelector("#max-people");
  const submitBtnEl = document.querySelector("#submit");

  // Handle
  const handleSubmitClick = (e) => {
    if (!(maxPeopleEl.value > 0)) {
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