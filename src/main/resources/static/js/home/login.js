(() => {

  // Element
  const loginEmailInputEl = document.querySelector(".login-email-input-js");
  const loginEmailIconEl = document.querySelector(".login-email-icon-js");

  const loginPasswordInputEl = document.querySelector(".login-password-input-js");
  const loginPasswordIconEl = document.querySelector(".login-password-icon-js");

  const loginSubmitBtnEl = document.querySelector(".login-submit-btn-js");

  // Regex
  const emailRgx = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
  const passwordRgx = /^.*(?=^.{8,20}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;

  // Color
  const activeColor = "#EB4C60";
  const disableColor = "#b3b3b3";

  // Status
  let emailStatus = false;
  let passwordStatus = false;

  // Function
  const setSubmit = () => {
    if (emailStatus && passwordStatus) {
      loginSubmitBtnEl.style.backgroundColor = activeColor;
    } else {
      loginSubmitBtnEl.style.backgroundColor = "#ffb3b3";
    }
  }

  // Handler
  const handleEmailKeyup = () => {
    emailStatus = false;
    if (emailRgx.test(loginEmailInputEl.value)) {
      loginEmailIconEl.style.color = activeColor;
      emailStatus = true;
    } else {
      loginEmailIconEl.style.color = disableColor;
    }
    setSubmit();
  }

  const handlePasswordKeyup = () => {
    passwordStatus = false;
    if (passwordRgx.test(loginPasswordInputEl.value)) {
      loginPasswordIconEl.style.color = activeColor;
      passwordStatus = true;
    } else {
      loginPasswordIconEl.style.color = disableColor;
    }
    setSubmit();
  }

  const handleSubmitClick = (e) => {
    if (!(emailStatus && passwordStatus)) {
      e.preventDefault()
    }
  }

  // Initialize
  const init = () => {
    loginEmailInputEl.addEventListener("keyup", handleEmailKeyup);
    loginPasswordInputEl.addEventListener("keyup", handlePasswordKeyup);
    loginSubmitBtnEl.addEventListener("click", handleSubmitClick);
  }

  init();
})()