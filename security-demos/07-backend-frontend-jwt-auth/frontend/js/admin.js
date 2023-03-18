// Scripts related to admin page

runOnLoad(protectAdminArea);

/**
 * Redirect the user away from this page when admin permissions not present
 */
function protectAdminArea() {
  if (!isAdmin(getAuthenticatedUser())) {
    redirectTo("/index.html");
  }
}
