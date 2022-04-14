// Dynamically load product data

document.addEventListener("DOMContentLoaded", function() {
   sendProductDataRequest();
});

/**
 * Send an Ajax request to backend, fetch product data
 */
function sendProductDataRequest() {
    console.log("Loading product data...");
    sendApiRequest("GET", "/products", showProducts)
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
