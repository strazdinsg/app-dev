// Dynamically load product data

runOnLoad(sendProductDataRequest);

/**
 * Send an Ajax request to backend, fetch product data
 */
function sendProductDataRequest() {
  console.log("Loading product data...");
  sendApiRequest("GET", "/products", showProducts, null, productLoadingFailed);
}

/**
 * Show products on the page
 * @param products
 */
function showProducts(products) {
  const productContainer = document.getElementById("product-container");
  productContainer.innerHTML = "";
  for (let i = 0; i < products.length; ++i) {
    const product = products[i];
    const productElement = document.createElement("li");
    productElement.innerText = product.name + " ($" + product.price + ")";
    productContainer.appendChild(productElement);
  }
}

function productLoadingFailed() {
  const main = document.querySelector("main");
  main.innerHTML =
    "<p class='error'>Could not load products from the API. Perhaps the backend is not accessible?</p>";
}
