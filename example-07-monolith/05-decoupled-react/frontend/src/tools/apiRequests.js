const API_BASE_URL = "http://localhost:8080/api";

/**
 * Send HTTP GET request to the backend REST API
 * @param {string} apiUrl Relative API URL
 * @param {function} callback Callback function to call un success, with the response JSON as an argument.
 * @return {Promise<void>}
 */
export async function requestApiData(apiUrl, callback) {
  console.log(`Requesting data from ${apiUrl}...`);
  const url = API_BASE_URL + apiUrl;
  const response = await fetch(url);
  if (response.ok) {
    const jsonData = await response.json();
    callback(jsonData);
  }
}
