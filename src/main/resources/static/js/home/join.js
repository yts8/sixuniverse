(() => {

    // Element
    const joinEmailInputEl = document.querySelector("#email");
    const joinEmailIconEl = document.querySelector(".join-email-icon");

    const joinPasswordInputEl = document.querySelector("#password");
    const joinPasswordIconEl = document.querySelector(".join-password-icon-js");

    const joinUsernameInputEl = document.querySelector("#username")
    const joinUsernameIconEl = document.querySelector(".join-username-icon-js")

    const joinBirthdateInputEl = document.querySelector("#birthdate")
    const joinBirthdateIconEl = document.querySelector(".join-birthdate-icon-js")

    const joinMobileInputEl = document.querySelector("#mobile")
    const joinMobileIconEl = document.querySelector(".join-mobile-icon-js")

    const joinAuthBtnContainerEl = document.querySelector(".join-auth-btn-container-js");
    const joinAuthBtnEl = document.querySelector(".join-auth-btn-js");

    const joinAuthInputContainerEl = document.querySelector(".join-auth-input-container-js");
    const joinAuthInputEl = document.querySelector(".join-auth-input-js");
    const joinAuthIconEl = document.querySelector(".join-auth-icon-js");

    const joinSubmitBtnEl = document.querySelector(".join-form-submit-btn")

    // Regex
    const emailRgx = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    const passwordRgx = /^.*(?=^.{8,20}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/
    const usernameRgx = /.+/;
    const birthdateRgx = /\d{8}/i;
    const mobileRgx = /(01[01])\d{8}/i;

    const activeColor = "var(--guest-color)";
    const disableColor = "var(--disable-color)";

    // Status
    let emailStatus = false;
    let authStatus = false;
    let passwordStatus = false;
    let usernameStatus = false;
    let birthdateStatus = true;
    let mobileStatus = true;
    let submitStatus = false;

    // Auth
    let authCode;

    // Function
    const setSubmit = () => {
      submitStatus = emailStatus && authStatus && passwordStatus && usernameStatus && birthdateStatus && mobileStatus;
      if (submitStatus) {
        joinSubmitBtnEl.style.backgroundColor = activeColor;
      } else {
        joinSubmitBtnEl.style.backgroundColor = "var(--light-guest-color)";
      }
    }

    const setStatus = (inputEl, status) => {
      switch (inputEl) {
        case joinPasswordInputEl:
          passwordStatus = status;
          break;
        case joinAuthInputEl:
          authStatus = status;
          break;
        case joinUsernameInputEl:
          usernameStatus = status;
          break;
        case joinBirthdateInputEl:
          birthdateStatus = status;
          break;
        case joinMobileInputEl:
          mobileStatus = status;
          break;
      }
    }

    // Handler
    const handleEmailKeyup = async (e) => {
      const {value: email} = e.target;

      // Initialize
      joinEmailIconEl.style.color = disableColor;
      joinAuthBtnContainerEl.style.display = "none";
      joinAuthInputContainerEl.style.display = "none";
      joinAuthInputEl.value = "";
      joinAuthIconEl.style.color = disableColor;
      authCode = null;
      emailStatus = false;
      authStatus = false;

      if (emailRgx.test(email)) {
        const res = await fetch(`http://localhost:8080/api/login/email/${email}`);
        const isNotEmail = await res.json();
        if (isNotEmail) {
          joinEmailIconEl.style.color = activeColor;
          joinAuthBtnContainerEl.style.display = "flex";
          emailStatus = true;
        }
      }
      setSubmit();
    }

    const handleInputKeyup = (e, iconEl) => {
      let rgx;
      switch (e.target) {
        case joinPasswordInputEl:
          rgx = passwordRgx;
          break;
        case joinUsernameInputEl:
          rgx = usernameRgx;
          break;
        case joinBirthdateInputEl:
          rgx = birthdateRgx;
          break;
        case joinMobileInputEl:
          rgx = mobileRgx;
          break;
      }
      if ((e.target === joinBirthdateInputEl && e.target.value === "") ||
        (e.target === joinMobileInputEl && e.target.value === "")) {
        iconEl.style.color = disableColor;
        setStatus(e.target, true);
      } else if (rgx.test(e.target.value)) {
        iconEl.style.color = activeColor;
        setStatus(e.target, true);
      } else {
        iconEl.style.color = disableColor;
        setStatus(e.target, false);
      }
      setSubmit();
    }

    const handleAuthBtnClick = async () => {
      if (emailStatus) {
        joinAuthBtnContainerEl.style.display = "none";
        joinAuthInputContainerEl.style.display = "flex"

        // const res = await fetch(`http://localhost:8080/api/login/email/${joinEmailInputEl.value}/auth-code`)
        // const json = await res.json();
        // authCode = json.authCode;
        authCode = "1234";
      }
      setSubmit();
    }

    const handleAuthInputKeyup = (e) => {
      const {value} = e.target;
      if (value === authCode) {
        joinAuthIconEl.style.color = activeColor;
        setStatus(e.target, true);
      } else {
        joinAuthIconEl.style.color = disableColor;
        setStatus(e.target, false);
      }
      setSubmit();
    }

    const handleSubmitClick = (e) => {
      if (!submitStatus) {
        e.preventDefault();
      }
    }

    // Initialize
    const init = () => {
      joinEmailInputEl.addEventListener("keyup", handleEmailKeyup);
      joinPasswordInputEl.addEventListener("keyup", (e) => handleInputKeyup(e, joinPasswordIconEl));
      joinUsernameInputEl.addEventListener("keyup", (e) => handleInputKeyup(e, joinUsernameIconEl));
      joinBirthdateInputEl.addEventListener("keyup", (e) => handleInputKeyup(e, joinBirthdateIconEl));
      joinMobileInputEl.addEventListener("keyup", (e) => handleInputKeyup(e, joinMobileIconEl));
      joinAuthBtnEl.addEventListener("click", handleAuthBtnClick)
      joinAuthInputEl.addEventListener("keyup", handleAuthInputKeyup)
      joinSubmitBtnEl.addEventListener("click", handleSubmitClick);
    }

    init();
  }
)()