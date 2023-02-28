const API_BASE_URL = "http://localhost:8080/api";

/**
 * Send a REST API request (asynchronous HTTP GET), parse the response as JSON data,
 * call the response handler
 * @param apiUrl The relative URL to send the REST API request to
 * @param responseHandler The function which will handle the response data
 * @returns {Promise<void>} A promise that this function will resolve at some later time
 */
async function requestData(apiUrl, responseHandler) {
  const url = API_BASE_URL + apiUrl;
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

/**
 * Create the content for navigation
 */
function initializeNavigation() {
  console.log("Ddd");
  const navItems = [
    { link: "index.html", title: "Home" },
    { link: "books.html", title: "Books" },
    { link: "about.html", title: "About Us" },
  ];
  navItems.forEach((navItem) => createMenuItem(navItem));
  return true;
}

/**
 * Create one menu item for the navigation
 * @param {object} navItem Navigation item containing the link and title
 */
function createMenuItem(navItem) {
  console.log(navItem);
  const li = document.createElement("li");
  li.innerHTML = `<a href="${navItem.link}">${navItem.title}</a>`;
  const navList = document.querySelector("nav ul");
  navList.appendChild(li);
}
