// All code for sending requests to backend is stored in this file

// The base path where the API is running
const API_BASE_URL = "http://web-tek.ninja:8042/api";

/**
 * Send a REST-API request to the backend
 * @param method The method to use: GET, POST, PUT, DELETE
 * @param url relative URL of the API endpoint
 * @param callback Callback function to call on success, with response text as the parameter
 * @param requestBody When supplied, send this data in the request body. Does not work with HTTP GET!
 * @param errorCallback A function called when the response code is not 200
 */
async function sendApiRequest(
  method,
  url,
  callback,
  requestBody,
  errorCallback
) {
  let parameters = { method: method, headers: constructRequestHeaders(method) };

  if (requestBody) {
    parameters.body = JSON.stringify(requestBody);
  }

  try {
    const response = await fetch(API_BASE_URL + url, parameters);
    const responseText = await response.text();

    if (response.status === 200) {
      let responseJson = "";
      if (responseText) {
        responseJson = JSON.parse(responseText);
      }
      callback(responseJson);
    } else if (errorCallback) {
      errorCallback(responseText);
    }
  } catch (error) {
    if (errorCallback) {
      errorCallback(error);
    }
  }
}

/**
 * Construct HTTP Headers to be sent in a request
 * @param method
 * @return object An object containing the necessary HTTP headers
 */
function constructRequestHeaders(method) {
  let headers = {};

  if (method.toLowerCase() !== "get") {
    headers["Content-Type"] = "application/json";
  }

  const jwtToken = getCookie("jwt");
  if (jwtToken) {
    headers["Authorization"] = "Bearer " + jwtToken;
  }
  return headers;
}
