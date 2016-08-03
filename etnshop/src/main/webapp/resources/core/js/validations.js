function handleSubmitEditProduct() {
    $("#edit-product").submit(function (event) {
        var productName = $("#edit-product-name").val();
        if (!productName.trim()) {
            alert('Product name must be filled')
            event.preventDefault();
        }
    });
}

function handleSubmitNewProduct() {
    $("#new-product").submit(function (event) {
        var productName = $("#new-product-name").val();
        var productSerial = $("#new-product-serial").val();
        if (!productName.trim() || !productSerial.trim()) {
            alert('Product name and serial number must be filled')
            event.preventDefault();
        }
    });
}

$(handleSubmitEditProduct);
$(handleSubmitNewProduct);