var jsonData;
var startIndex = 0;
var scroll;
var allDataLoaded = false;

function setCookie(data) {
    var expires = "";
    var date = new Date();
    var days = 3;
    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
    expires = "; expires=" + date.toUTCString();
    document.cookie = "shoppingCart" + "=" + data + expires + "; path=/";
}

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
    if (i >= data.length) {
        allDataLoaded = true;
    }
}

function loadDetails(item) {
    var id = $(item).attr("id");
    scroll = $(window).scrollTop();
    $(".stock-content").slideUp();
    $("#load-more-button").slideUp();
    var data = jsonData[id-1].name;
    $('.content-column').append(
        "<div class='details'> " +
        "<p>details here</p>" +
        "<div class='row'>" +
        '<button id="'+ id +'" onclick="addToShoppingCart(this)" type="button" class="buy-button btn btn-danger">Ostukorvi</button>' +
        "</div>" +
        "<div class='row'>" +
        '<button onclick="loadMainView()" id="return-button" type="button" class="btn">Tagasi</button>' +
        "</div>" +
        "</div>"
    );
}

function addToShoppingCart(item) {
    var id = $(item).attr("id");
    setCookie(id);
    alert(document.cookie);
}

function loadMainView() {
    $(".details").hide();
    $(".stock-content").show();
    if (!allDataLoaded) {
        $("#load-more-button").show();
    }
    $("html").scrollTop(scroll);
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

