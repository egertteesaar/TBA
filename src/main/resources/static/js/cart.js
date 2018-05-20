var cartItems = [];
var price = 0;

function removeItem(id) {
    var index = cartItems.indexOf("" + id);
    if (index !== - 1) {
        if (cartItems.length === 1) {
            cartItems = [];
        } else {
            cartItems.splice(index, 1);
        }
    }
    localStorage.setItem('cartItems', JSON.stringify(cartItems));
    removeRow(id);
}

function getItem(id){
    $.ajax({
        url: "/api/stock/" + id,
        success: function(data) {
            data = $.parseJSON(data);
            jsonData = data;
            price += data.price / 100;
            addRow(jsonData, id);
        }
    });
}

function addRow(data, id) {
    $('#cartTable tbody').append(
        "<tr id='" + id + "'>" +
        '<td>' + data.brand + '</td>' +
        '<td>'+ data.name + '</td>' +
        '<td>'+ data.price / 100 + '</td>' +
        '<td><button onclick="removeItem(' + data.id +')" type="button" class="btn btn-danger">Remove</button></td>' +
        '</tr>'
    );
}

function removeRow(id) {
    $("#cartTable #" + id).remove();
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