(() => {

  // Elements
  const priceEl = document.querySelector("#price");
  const submitBtnEl = document.querySelector("#submit");

  // Color
  const failBackgroundColor = "var(--fail-background-color)";
  const failBorderColor = "var(--fail-border-color)";

  // Handler
  const handleSubmitClick = (e) => {
    if (!(priceEl.value >= 10000)) {
      e.preventDefault();
      alert("기본 요금은 10000원 이상이여야 합니다.");
      priceEl.style.backgroundColor = failBackgroundColor;
      priceEl.style.borderColor = failBorderColor;
    }
  }

  // Initialize
  const init = () => {
    submitBtnEl.addEventListener("click", handleSubmitClick);
  }
  init();

})();