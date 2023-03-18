/**
 * Send an Ajax request to backend, fetch product data
 */
async function sendProductDataRequest() {
    console.log("Loading product data...");
    const response = await fetch("/api/products");
    const productJson = await response.json();
    showProducts(productJson);
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