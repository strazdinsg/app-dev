// Scripts related to admin page

runOnLoad(checkAdminPermissions);

function checkAdminPermissions() {
    if (!isAdmin(getAuthenticatedUser())) {
        redirectTo("/index.html");
    }
}
