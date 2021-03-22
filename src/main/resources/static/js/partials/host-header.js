(() => {
  // Elements
  const headerProfileMenuBtnEl = document.querySelector(".header-profile-menu-btn-js");
  const headerProfileMenuEl = document.querySelector(".header-profile-menu-js")

  // Handler
  const handleProfileMenuBtnClick = () => {
    headerProfileMenuEl.classList.toggle("header__profile-menu-hide");
  }

  // Initialize
  const init = () => {
    headerProfileMenuBtnEl.addEventListener("click", handleProfileMenuBtnClick);
  }
  init();

})();