function populateContent(data) {
    console.log(data);
    for (i = 0; i < data.length; i++) {
        var item = data[i];
        var image = item.image;
        var brand = item.brand;
        var name = item.name;
        var description = item.description;
        $('.stock-content').append("" +
            "<div class=\"card\">\n" +
            "<img class=\"card-img-top\" src=\"" + image + "\" alt=\"Card image cap\"/>\n" +
            "<div class=\"card-body\">\n" +
            "<h5 class=\"card-title\">" + brand + " " + name + "</h5>\n" +
            "<p class=\"card-text\">" + description + "</p>\n" +
            "<a href=\"#\" class=\"btn btn-primary\">Detailid</a>\n" +
            "</div>\n" +
            "</div>");
    }
}

function loadStock(){
    $.ajax({
        url: "/api/stock/",
        success: function(data) {
            data = $.parseJSON(data);
            populateContent(data);
        }
    });
}


$(document).ready(function () {
    loadStock();
});

