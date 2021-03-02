(() => {

  // Element
  const updateToggleBtnEls = document.querySelectorAll(".update-toggle-btn-js");

  // Handler
  const updateToggleBtnClick = (e) => {
    const hide = "member-personal-info__update-hide";
    const {
      dataset: {id}, innerText
    } = e.target;
    const updateTextEl = document.querySelector(`.update-${id}-text-js`);
    const updateInputBoxEl = document.querySelector(`.update-${id}-input-box-js`);

    if (innerText === "수정") {
      e.target.innerText = "접기"
    } else {
      e.target.innerText = "수정"
    }

    updateTextEl.classList.toggle(hide);
    updateInputBoxEl.classList.toggle(hide);
  }

  // Initialize
  const init = () => {
    updateToggleBtnEls.forEach(el => el.addEventListener("click", updateToggleBtnClick));
  }

  init();

})();