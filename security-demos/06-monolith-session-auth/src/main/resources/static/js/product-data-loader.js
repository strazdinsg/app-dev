// Dynamically load product data
const request = new XMLHttpRequest();

/**
 * Send an Ajax request to backend, fetch product data
 */
function sendProductDataRequest() {
    console.log("Loading product data...");
    request.onload = productDataReceived;
    request.open("GET", "/api/products");
    request.send();
}

/**
 * This method is called when product data is received
 */
function productDataReceived() {
    if (request.readyState === XMLHttpRequest.DONE) {
        if (request.status === 200) {
            console.log("Product data received")
            const products = JSON.parse(request.responseText);
            showProducts(products);
        } else {
            console.error("Error while fetching product data");
        }
    }
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