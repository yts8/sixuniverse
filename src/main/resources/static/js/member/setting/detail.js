(() => {

  // Element
  const updateToggleBtnEls = document.querySelectorAll(".update-toggle-btn-js");
  const updateBtnEls = document.querySelectorAll(".update-btn-js");
  const updateAddressBtnEl = document.querySelector(".update-address-btn-js");
  const updatePasswordBtnEl = document.querySelector(".update-password-btn-js");
  const updatePasswordTextBoxEl = document.querySelector(".update-password-text-box-js");

  const usernameInputEl = document.querySelector("#username");
  const birthdateInputEl = document.querySelector("#birthdate");
  const mobileInputEl = document.querySelector("#mobile");
  const oldPasswordInputEl = document.querySelector("#old-password");
  const newPasswordInputEl = document.querySelector("#new-password");
  const confirmPasswordInputEl = document.querySelector("#confirm-password");

  // CSRF
  const csrf = document.querySelector("#csrf").value;

  // Color
  const failBackgroundColor = "var(--fail-background-color)";
  const failBorderColor = "var(--fail-border-color)";

  // Regex
  const rgxs = {
    username: /.+/,
    birthdate: /^\d{8}$/i,
    mobile: /^(01[01])\d{8}$/i,
    zipcode: /^\d{5}$/,
    bio: /.+/,
    password: /^.*(?=^.{8,20}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/
  }

  // Toggle Flag
  const toggleBtnFlag = (id) => {
    const hide = "member-setting__update-hide";

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

    return await fetch(`//${location.host}/api/member/setting/update/${id}`, {
      method: "post",
      headers: {
        "Content-Type": "application/json",
        "X-CSRF-TOKEN": csrf
      },
      body: JSON.stringify(data)
    });
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

  const handleUpdatePasswordBtnClick = async (e) => {
    const {
      dataset: {id}
    } = e.target;

    if (!checkRgx(oldPasswordInputEl, rgxs.password) ||
      !checkRgx(newPasswordInputEl, rgxs.password) ||
      newPasswordInputEl.value !== confirmPasswordInputEl.value) {
      return;
    }

    const data = {
      "oldPassword": oldPasswordInputEl.value,
      "newPassword": newPasswordInputEl.value
    }
    const res = await sendAjax(id, data)
    try {
      const member = await res.json();
      updatePasswordTextBoxEl.innerHTML = member.updateDate.split("T")[0];
    } catch (e) {
      alert("기존 비밀번호가 다릅니다.");
      return;
    }


    oldPasswordInputEl.value = "";
    newPasswordInputEl.value = "";
    confirmPasswordInputEl.value = "";

    setResetInputColor(oldPasswordInputEl);
    setResetInputColor(newPasswordInputEl);
    setResetInputColor(confirmPasswordInputEl);

    toggleBtnFlag(id);

  }

  const handleInputKeyup = (e, rgx) => {
    if (!rgx.test(e.target.value)) {
      setFailInputColor(e.target)
    } else {
      setResetInputColor(e.target);
    }
  }

  const handleConfirmPasswordKeyup = () => {
    if (newPasswordInputEl.value !== confirmPasswordInputEl.value) {
      setFailInputColor(confirmPasswordInputEl);
    } else {
      setResetInputColor(confirmPasswordInputEl);
    }
  }

  // Initialize
  const init = () => {
    updateToggleBtnEls && updateToggleBtnEls.forEach(el => el.addEventListener("click", e => toggleBtnFlag(e.target.dataset.id)));
    updateBtnEls && updateBtnEls.forEach(el => el.addEventListener("click", handleUpdateBtnClick));
    updateAddressBtnEl && updateAddressBtnEl.addEventListener("click", handleUpdateAddressBtnClick);
    updatePasswordBtnEl && updatePasswordBtnEl.addEventListener("click", handleUpdatePasswordBtnClick);

    usernameInputEl && usernameInputEl.addEventListener("keyup", e => handleInputKeyup(e, rgxs.username));
    birthdateInputEl && birthdateInputEl.addEventListener("keyup", e => handleInputKeyup(e, rgxs.birthdate));
    mobileInputEl && mobileInputEl.addEventListener("keyup", e => handleInputKeyup(e, rgxs.mobile));

    oldPasswordInputEl && oldPasswordInputEl.addEventListener("keyup", e => handleInputKeyup(e, rgxs.password));
    newPasswordInputEl && newPasswordInputEl.addEventListener("keyup", e => handleInputKeyup(e, rgxs.password));
    confirmPasswordInputEl && confirmPasswordInputEl.addEventListener("keyup", handleConfirmPasswordKeyup);
  }

  init();

})();