const BASE_URL = "/frontend"; // Set this to whatever your BASE URL is

/**
 * Create the navigation, adding links based on current user permission level
 */
function createNavigation() {
    addNavigationItem("Home", "/");
    addNavigationItem("Products", "/products.html");
    const authenticatedUser = getAuthenticatedUser();
    if (authenticatedUser) {
        if (isAdmin(authenticatedUser)) {
            addNavigationItem("Administration", "/admin.html");
        }
        addNavigationItem(`Welcome, ${authenticatedUser.username}!`, "/profile.html");
        addNavigationItem("Logout", `/logout`);
    } else {
        addNavigationItem("Login", "/login.html");
        addNavigationItem("Sign up", "/signup.html");
    }
}

function addNavigationItem(title, url) {
    const navList = document.querySelector(".navigation");
    const navItem = document.createElement("li");
    navItem.innerHTML = `<a href='${BASE_URL + url}'>${title}</a>`;
    navList.appendChild(navItem);
}

/**
 * Redirect browser to given relative URL
 * @param frontendUrl URL, relative to frontend base URL
 */
function redirectTo(frontendUrl) {
    window.location = BASE_URL + frontendUrl;
}
