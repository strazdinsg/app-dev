// Handlers of events - form button clicks etc

const loginFormButton = document.getElementById("login-form-button");
if (loginFormButton) {
    loginFormButton.addEventListener("click", submitLoginForm);
}

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
    redirectTo("/");
}

function showFormError(errorMessage) {
    showFormResult(errorMessage, "error");
}

function showFormResult(message, resultType) {
    const resultElement = document.getElementById("result-message");
    resultElement.classList.add(resultType);
    resultElement.classList.remove("hidden");
    resultElement.innerText = message;
}
