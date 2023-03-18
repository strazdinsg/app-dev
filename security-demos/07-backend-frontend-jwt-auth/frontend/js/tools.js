// Some tools, utilities

/**
 * Get value of a specific cookie.
 * Code copied from https://www.w3schools.com/js/js_cookies.asp
 * @param cname Cookie name (key)
 * @returns {string} Value of the cookie or "" if cookie not found
 */
function getCookie(cname) {
  let name = cname + "=";
  let decodedCookie = decodeURIComponent(document.cookie);
  let ca = decodedCookie.split(';');
  for (let i = 0; i < ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) === ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) === 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

/**
 * Store a local cookie
 * Code copied from https://www.w3schools.com/js/js_cookies.asp
 * @param cname Name of the cookie (key)
 * @param cvalue Value of the cookie
 * @param exdays expiry time in days
 */
function setCookie(cname, cvalue, exdays) {
  const d = new Date();
  d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
  let expires = "expires=" + d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

/**
 * Delete a cookie
 * @param cookieName Name of the cookie to delete
 */
function deleteCookie(cookieName) {
  setCookie(cookieName, "", -1);
}

/**
 * Execute the function func after the page is loaded
 * @param func Function to run when the page is loaded
 */
function runOnLoad(func) {
  document.addEventListener("DOMContentLoaded", func);
}


/**
 * Show an error message for a form
 * @param errorMessage The error message to show
 */
function showFormError(errorMessage) {
  showFormResult(errorMessage, "error");
}

/**
 * Show a success message for a form
 * @param successMessage The success message to show
 */
function showFormSuccess(successMessage) {
  showFormResult(successMessage, "success");
}

/**
 * Show a result message in a form
 * @param message The message to show
 * @param resultType Type of the result: error or success
 */
function showFormResult(message, resultType) {
  const resultElement = document.getElementById("result-message");
  resultElement.classList.add(resultType);
  resultElement.classList.remove("hidden");
  resultElement.innerText = message;
}
