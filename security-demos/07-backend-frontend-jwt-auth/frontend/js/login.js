// Login-form related stuff

const loginFormButton = document.getElementById("login-form-button");
if (loginFormButton) {
  loginFormButton.addEventListener("click", submitLoginForm);
}

/**
 * Submit the login form
 * @param event
 */
function submitLoginForm(event) {
  event.preventDefault(); // Don't submit the form using the regular HTTP POST
  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;
  sendAuthenticationRequest(username, password, onLoginSuccess, showFormError);
}

/**
 * This function will be called when login was successful
 */
function onLoginSuccess() {
  redirectTo("/index.html");
}
