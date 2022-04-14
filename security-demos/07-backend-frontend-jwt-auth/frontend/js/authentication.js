// Authentication stuff

/**
 * Get the currently authenticated user
 * @returns User object or null if user is not authenticated
 */
function getAuthenticatedUser() {
    let user = null;
    const jwtString = getCookie("jwt");
    if (jwtString) {
        const jwt = parseJwt(jwtString);
        if (jwt) {
            user = {
                "username": jwt.sub,
                "roles": jwt.roles.map(r => r.authority)
            }
            console.log(user);
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
    return user.roles.includes("ROLE_ADMIN");
}

/**
 * Send authentication request to the API
 * @param username Username
 * @param password Password, plain text
 * @param successCallback Function to call on success
 * @param errorCallback Function to call on error, with resposne text as the parameter
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
function parseJwt (token) {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
}
