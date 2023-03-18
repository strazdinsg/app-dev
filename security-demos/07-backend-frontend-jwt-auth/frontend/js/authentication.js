// Authentication stuff

/**
 * Get the currently authenticated user
 * @returns User object or null if user is not authenticated
 */
function getAuthenticatedUser() {
  let user = null;
  const username = getCookie("current_username");
  const commaSeparatedRoles = getCookie("current_user_roles");
  if (username && commaSeparatedRoles) {
    const roles = commaSeparatedRoles.split(",");
    user = {
      "username": username,
      "roles": roles
    }
  }
  return user;
}

/**
 * Check if the given user has admin rights
 * @param user
 * @returns {boolean}
 */
function isAdmin(user) {
  return user && user.roles && user.roles.includes("ROLE_ADMIN");
}

/**
 * Send authentication request to the API
 * @param username Username
 * @param password Password, plain text
 * @param successCallback Function to call on success
 * @param errorCallback Function to call on error, with response text as the parameter
 */
function sendAuthenticationRequest(username, password, successCallback, errorCallback) {
  const postData = {
    "username": username,
    "password": password
  };
  sendApiRequest(
    "POST", "/authenticate",
    function (jwtResponse) {
      setCookie("jwt", jwtResponse.jwt);
      const userData = parseJwtUser(jwtResponse.jwt);
      if (userData) {
        setCookie("current_username", userData.username);
        setCookie("current_user_roles", userData.roles.join(","));
      }
      successCallback();
    },
    postData,
    function (responseText) {
      errorCallback(responseText);
    }
  );
}

/**
 * Parse JWT string, extract information from it
 * Code copied from https://stackoverflow.com/questions/38552003/how-to-decode-jwt-token-in-javascript-without-using-a-library
 * @param token JWT token string
 * @returns {any} Decoded JWT object
 */
function parseJwt(token) {
  const base64Url = token.split('.')[1];
  const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
  const jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
    return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
  }).join(''));

  return JSON.parse(jsonPayload);
}

/**
 * Parse JWT string, extract a User object
 * @param jwtString
 * @return User object
 */
function parseJwtUser(jwtString) {
  let user = null;
  const jwtObject = parseJwt(jwtString);
  if (jwtObject) {
    user = {
      "username": jwtObject.sub,
      "roles": jwtObject.roles.map(r => r.authority)
    }
  }
  return user;
}


/**
 * Log-out the user
 */
function doLogout() {
  console.log("Logging out...");
  deleteAuthorizationCookies();
  redirectTo("/index.html");
}

/**
 * Delete all cookies related to authorization (user session)
 */
function deleteAuthorizationCookies() {
  deleteCookie("jwt");
  deleteCookie("current_username");
  deleteCookie("current_user_roles");
}
