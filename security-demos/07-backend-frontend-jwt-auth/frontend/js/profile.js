// Scripts related to the profile page

runOnLoad(loadProfileData);

const profileSaveButton = document.getElementById("profile-save-button");
const bioElement = document.getElementById("bio");

profileSaveButton.onclick = function (event) {
  event.preventDefault(); // Don't submit the form
  const profileData = {
    "bio": bioElement.value
  };
  const username = getCookie("current_username");
  bioElement.disabled = true;
  profileSaveButton.disabled = true;
  sendApiRequest("PUT", "/users/" + username, profileSaveSuccess, profileData, profileSaveError);
}

/**
 * Send request to backend, load user profile data
 */
function loadProfileData() {
  console.log("Loading profile data from API...");
  bioElement.disabled = true;
  profileSaveButton.disabled = true;
  const user = getAuthenticatedUser();
  if (user) {
    sendApiRequest("GET", "/users/" + user.username, showProfileData);
  } else {
    redirectTo("/no-access.html");
  }
}

/**
 * Show user profile data on the page
 * @param profileData User profile data received from the backend
 */
function showProfileData(profileData) {
  if (profileData) {
    bioElement.innerText = profileData.bio ? profileData.bio : "";
    bioElement.disabled = false;
    profileSaveButton.disabled = false;
  }
}

/**
 * This function is called when profile was successfully saved (response from API received)
 */
function profileSaveSuccess() {
  showFormSuccess("Profile saved");
  bioElement.disabled = false;
  profileSaveButton.disabled = false;
}

/**
 * This function is called when profile-saving failed
 * @param error Error message received from the API
 */
function profileSaveError(error) {
  showFormError(error);
}
