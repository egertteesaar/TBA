function loadWeatherData(latitude, longitude) {
    $.ajax({
        url: "/api/location/" + latitude + "/" + longitude,
        success: function(data) {
            data = $.parseJSON(data);
            console.log(data);
            clearIntroView();
            loadResultView(data);
        }
    });
}

function getLocationSuccessFunction(position)
{
    var lat = position.coords.latitude;
    var long = position.coords.longitude;
    console.log(lat, long);
    loadWeatherData(lat, long);
}

function clearIntroView() {
    // todo: remove .intro-text and .begin-button from dom
}

function loadResultView(data) {
    // todo: show weather, load wardrobe from backend
    var location = data.name;
    var temp = data.temp;
    var hum = data.humidity;
    var pres = data.pressure;
    var wind_speed = data.wind_speed;
    var wind_deg = data.wind_deg;

}


$(document).ready(function () {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(getLocationSuccessFunction, function (e) {console.log(e)});
    } else {
        alert('It seems like Geolocation, which is required for this page, is not enabled in your browser.');
    }
    $(".begin-button").click(function () {
        alert("click");
    });
});

