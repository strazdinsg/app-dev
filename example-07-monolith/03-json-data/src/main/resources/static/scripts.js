/**
 * Show a specific page (which has been hidden previously) inside the main content.
 *
 * @param pageName The name of the page to show
 */
function showPage(pageName) {
  const pageContent = document.getElementById(pageName);
  if (pageContent) {
    hideAllPages();
    showElement(pageContent);
  }
}

/**
 * Hide all pages (their main content).
 */
function hideAllPages() {
  const allPageElements = document.querySelectorAll("article");
  allPageElements.forEach((element) => hideElement(element));
}

/**
 * Show a given DOM element.
 * @param element The DOM element to show.
 */
function showElement(element) {
  setElementVisibility(element, true);
}

/**
 * Hide a given DOM element.
 * @param element The DOM element to hide.
 */
function hideElement(element) {
  setElementVisibility(element, false);
}

/**
 * Set visibility for a specific element: show or hide it.
 * @param element The element to show or hide.
 * @param visible When true, the element will be visible. When false, it will be hidden.
 */
function setElementVisibility(element, visible) {
  element.style.display = visible ? "" : "none";
}

/**
 * Send requests, load data from the API. These are asynchronous calls which
 * might take some time to complete.
 */
function loadDataFromApi() {
  // P.S. In real applications you would probably load the data on demand, not all data at once
  // Here we simplify it for demo purposes.
  requestData("/api/books/favorite", showFavoriteBooks);
  requestData("/api/books", showAllBooks);
  requestData("/api/statistics", showBookAndAuthorCount);
}

/**
 * Send a REST API request (asynchronous HTTP GET), parse the response as JSON data,
 * call the response handler
 * @param url The URL to send the REST API request to
 * @param responseHandler The function which will handle the response data
 * @returns {Promise<void>} A promise that this function will resolve at some later time
 */
async function requestData(url, responseHandler) {
  const response = await fetch(url);
  if (response.ok) {
    const responseData = await response.json();
    if (responseData) {
      responseHandler(responseData);
    }
  }
}

function showAllBooks(books) {
  showBookListing(books, "all-book-container");
}

function showFavoriteBooks(books) {
  showBookListing(books, "favorite-book-container");
}

/**
 * Generate book listing within a given book container
 * @param {array} books  The book data - an array
 * @param {string} containerElementId ID of the container - the DOM element where the book cards
 * will be displayed.
 */
function showBookListing(books, containerElementId) {
  const bookContainer = document.getElementById(containerElementId);
  if (bookContainer) {
    removeAllChildren(bookContainer);
    books.forEach((book) => bookContainer.appendChild(createCardElement(book)));
  }
}

/**
 * Remove all children from a given DOM element.
 * @param {HTMLElement} element The parent DOM element whose children will be removed.
 */
function removeAllChildren(element) {
  element.innerText = "";
}

/**
 * Create a DOM element representing a book card.
 * @param {object} book The book data to use for creating the card
 * @returns {HTMLDivElement} DOM element for the card
 */
function createCardElement(book) {
  const cardElement = document.createElement("div");
  cardElement.classList.add("card");
  cardElement.innerHTML =
    '<div class="book-image-container">' +
    '<img src="img/books/' +
    book.id +
    '.jpg" alt="A book cover"/>' +
    "</div>" +
    "<p>" +
    book.title +
    "</p>";
  return cardElement;
}

/**
 * Show the total number of books and authors.
 * @param {object} statistics
 */
function showBookAndAuthorCount(statistics) {
  if (statistics.bookCount) {
    showStatistic(statistics.bookCount, "book-count");
  }
  if (statistics.authorCount) {
    showStatistic(statistics.authorCount, "author-count");
  }
}

/**
 * Show a given statistic within DOM element with given ID.
 * @param {number} statistic The statistic to show
 * @param {string} targetElementId ID of the DOM element where to show the statistic
 */
function showStatistic(statistic, targetElementId) {
  const targetElement = document.getElementById(targetElementId);
  if (targetElement) {
    targetElement.innerText = statistic;
  }
}
