/**
 * Send request to the backend, get an HTML fragment for a specific page,
 * display it in the main content area.
 *
 * @param pageName The name of the page to request
 * @returns {Promise<void>} Returns a promise to execute the code at some point.
 */
async function showPage(pageName) {
  console.log("Requesting page " + pageName);
  const response = await fetch("/fragments/" + pageName);
  let httpResponseBody;
  if (response.ok) {
    httpResponseBody = await response.text();
  } else {
    httpResponseBody =
      "<p class='error'>Could not load data from the backend, contact the admin (code: " +
      response.status +
      ")</p>";
  }
  showMainContent(httpResponseBody);
}

/**
 * Show the given content (HTML) inside the main content area.
 * @param content The HTML content to show
 */
function showMainContent(content) {
  document.getElementById("main-content").innerHTML = content;
}
