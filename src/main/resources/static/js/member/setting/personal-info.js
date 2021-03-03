(() => {

  // Element
  const updateToggleBtnEls = document.querySelectorAll(".update-toggle-btn-js");
  const updateBtnEls = document.querySelectorAll(".update-btn-js");
  const updateAddressBtnEl = document.querySelector(".update-address-btn-js");

  const usernameInputEl = document.querySelector("#username");
  const birthdateInputEl = document.querySelector("#birthdate");
  const mobileInputEl = document.querySelector("#mobile");


  // Color
  const failBackgroundColor = "var(--fail-background-color)";
  const failBorderColor = "var(--fail-border-color)";

  // Regex
  const rgxs = {
    username: /.+/,
    birthdate: /\d{8}/i,
    mobile: /(01[01])\d{8}/i,
    zipcode: /\d{5}/,
    bio: /.+/
  }

  // Toggle Flag
  const toggleBtnFlag = (id) => {
    const hide = "member-personal-info__update-hide";

    const updateTextEl = document.querySelector(`.update-${id}-text-box-js`);
    const updateInputBoxEl = document.querySelector(`.update-${id}-input-box-js`);

    const toggleBtn = document.querySelector(`.update-toggle-${id}-btn-js`);

    if (toggleBtn.innerHTML.trim() === "수정") {
      toggleBtn.innerHTML = "접기"
    } else {
      toggleBtn.innerHTML = "수정"
    }

    updateTextEl.classList.toggle(hide);
    updateInputBoxEl.classList.toggle(hide);
  }

  // Set Fail Input Color
  const setFailInputColor = (el) => {
    el.style.background = failBackgroundColor;
    el.style.borderColor = failBorderColor;
  }

  // Set Reset Input Color
  const setResetInputColor = (el) => {
    el.style.background = "white";
    el.style.borderColor = "#d9d9d9";
  }

  // Regex Check
  const checkRgx = (el, rgx) => {
    if (!rgx.test(el.value)) {
      setFailInputColor(el);
      setFailInputColor(el);
      return false;
    }
    return true;
  }

  // AJAX
  const sendAjax = async (id, data) => {

    const csrf = document.querySelector("#csrf").value;

    console.log(csrf);
    console.log(id);
    console.log(data);

    await fetch(`http://localhost:8080/api/member/update/${id}`, {
      method: "post",
      headers: {
        "Content-Type": "application/json",
        "X-CSRF-TOKEN": csrf
      },
      body: JSON.stringify(data)
    })
  }

  // Handler
  const handleUpdateBtnClick = async (e) => {
    const {
      dataset: {id}
    } = e.target;

    const updateInputEl = document.querySelector(`#${id}`);

    if (!checkRgx(updateInputEl, rgxs[`${id}`])) {
      return;
    }

    let data;
    switch (id) {
      case "username":
        data = {username: updateInputEl.value}
        break;
      case "birthdate":
        data = {birthdate: updateInputEl.value}
        break;
      case "mobile":
        data = {mobile: updateInputEl.value}
        break;
      case "bio":
        data = {bio: updateInputEl.value}
        break;
    }

    await sendAjax(id, data);

    const updateTextEl = document.querySelector(`.update-${id}-text-box-js`);

    updateTextEl.innerHTML = updateInputEl.value;
    setResetInputColor(updateInputEl);
    toggleBtnFlag(id);
  }

  const handleUpdateAddressBtnClick = async (e) => {
    const {
      dataset: {id}
    } = e.target;

    const zipcodeInput = document.querySelector("#zipcode");
    const addressInput = document.querySelector("#address");
    const subAddressInput = document.querySelector("#sub-address");

    if (!checkRgx(zipcodeInput, rgxs.zipcode)) {
      return;
    }

    const data = {
      "zipcode": zipcodeInput.value,
      "address": addressInput.value,
      "subAddress": subAddressInput.value,
    }
    await sendAjax(id, data);

    const addressTextEl = document.querySelector(".update-address-text-js");
    const subAddressTextEl = document.querySelector(".update-sub-address-text-js");

    addressTextEl.innerHTML = addressInput.value;
    subAddressTextEl.innerHTML = subAddressInput.value;

    setResetInputColor(zipcodeInput);
    setResetInputColor(addressInput);

    toggleBtnFlag(id);
  }

  const handleInputKeyup = (e, rgx) => {
    if (!rgx.test(e.target.value)) {
      setFailInputColor(e.target)
    } else {
      setResetInputColor(e.target);
    }
  }

  // Initialize
  const init = () => {
    updateToggleBtnEls.forEach(el => el.addEventListener("click", e => toggleBtnFlag(e.target.dataset.id)));
    updateAddressBtnEl.addEventListener("click", handleUpdateAddressBtnClick);
    updateBtnEls.forEach(el => el.addEventListener("click", handleUpdateBtnClick));

    usernameInputEl.addEventListener("keyup", e => handleInputKeyup(e, rgxs.username));
    birthdateInputEl.addEventListener("keyup", e => handleInputKeyup(e, rgxs.birthdate));
    mobileInputEl.addEventListener("keyup", e => handleInputKeyup(e, rgxs.mobile));
  }

  init();

})();