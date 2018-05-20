var cartItems = [];
var price = 0;

function removeItem(id) {
    // todo: remove from shopping cart
}

function getItem(id){
    $.ajax({
        url: "/api/stock/" + id,
        success: function(data) {
            data = $.parseJSON(data);
            jsonData = data;
            price += data.price / 100;
            addRow(jsonData);
        }
    });
}

function addRow(data) {
    $('#cartTable tbody').append(
        "<tr>" +
        '<td>' + data.brand + '</td>' +
        '<td>'+ data.name + '</td>' +
        '<td>'+ data.price / 100 + '</td>' +
        '<td><button type="button" class="btn btn-danger">Remove</button></td>' +
        '</tr>'
    );
}

function populateCart() {
    for (var i = 0; i < cartItems.length; i++) {
        getItem(cartItems[i]);
    }
}


$(document).ready(function () {
    if (localStorage.getItem('cartItems')) {
        cartItems = JSON.parse(localStorage.getItem('cartItems'));
    }
    console.log(cartItems);
    populateCart();
});