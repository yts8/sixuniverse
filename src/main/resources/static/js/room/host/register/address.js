(() => {

  // Elements
  const zipcodeEl = document.querySelector("#zipcode");
  const addressEl = document.querySelector("#address");
  const submitBtnEl = document.querySelector("#submit");

  // Color
  const failBackgroundColor = "var(--fail-background-color)";
  const failBorderColor = "var(--fail-border-color)";

  // Handler
  const handleSubmitClick = (e) => {
    const zipcodeRgx = /^\d{5}$/
    if (!zipcodeRgx.test(zipcodeEl.value)) {
      e.preventDefault();
      zipcodeEl.style.backgroundColor = failBackgroundColor;
      zipcodeEl.style.borderColor = failBorderColor;
      addressEl.style.backgroundColor = failBackgroundColor;
      addressEl.style.borderColor = failBorderColor;
    }
  }


  //Initialize
  const init = () => {
    submitBtnEl.addEventListener("click", handleSubmitClick);
  }
  init();
})();
