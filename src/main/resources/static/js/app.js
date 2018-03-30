var jsonData;
var startIndex = 0;


function populateContent(data, index) {
    startIndex += 8;
    for (i = index; i < data.length && i < index + 8; i++) {
        var item = data[i];
        var image = item.image;
        var brand = item.brand;
        var name = item.name;
        var price = item.price;
        var whole = Math.floor(price / 100);
        var remainder = price % 100;
        var description = item.description;
        $('.stock-content').append("" +
            "<div class=\"card\">\n" +
                "<img class=\"card-img-top\" src=\"" + image + "\" alt=\"Card image cap\"/>\n" +
                "<div class=\"card-body\">\n" +
                    "<h5 class=\"card-title\">" + brand + " " + name + "</h5>\n" +
                    "<p class=\"card-text\">" + description + "</p>\n" +
                    "<p class=\"font-weight-bold card-text\">" + whole + "." + remainder + "€" + "</p>\n" +
                    "<a id='" + item.id +"' onclick='loadDetails(this)' href=\"#\" class=\"btn card-button btn-light\">Detailid</a>\n" +
                "</div>\n" +
            "</div>");
    }
}

function loadDetails(item) {
    var id = $(item).attr("id");
    $(".stock-content").slideUp();
    $("#load-more-button").slideUp();
    var data = jsonData[id-1].name;
    $('.content-column').append(
        "<div class='details'> " +
        "<p>details here</p>" +
        '<button id="'+ id +'" onclick="addToShoppingCart(this)" id="buy-button" type="button" class="btn btn-danger">Ostukorvi</button>' +
        '<button onclick="loadMainView()" id="return-button" type="button" class="btn">Tagasi</button>' +
        "</div>"
    );
}

function addToShoppingCart(item) {
    var id = $(item).attr("id");
    alert(id);
}

function loadMainView() {
    $(".details").hide();
    $(".stock-content").slideDown();
    $("#load-more-button").slideDown();
}

function loadStock(){
    $.ajax({
        url: "/api/stock/",
        success: function(data) {
            data = $.parseJSON(data);
            jsonData = data;
            populateContent(jsonData, startIndex);
        }
    });
}

$(document).ready(function () {
    loadStock();
    $("#load-more-button").click(function(){
        populateContent(jsonData, startIndex);
        if (startIndex > jsonData.length) {
            $("#load-more-button").hide();
        }
    });
});

