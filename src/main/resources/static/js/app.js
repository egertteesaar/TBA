var lat;
var long;

function loadWeatherData(latitude, longitude) {
    $.ajax({
        url: "/api/location/" + latitude + "/" + longitude,
        success: function(data) {
            data = $.parseJSON(data);
            clearIntroView();
            loadResultView(data);
        }
    });
}

function loadWeatherDataByLocation(location) {
    $.ajax({
        url: "/api/location/" + location,
        success: function(data) {
            data = $.parseJSON(data);
            clearIntroView();
            loadResultView(data);
        }
    });
}

function getLocationSuccessFunction(position)
{
    lat = position.coords.latitude;
    long = position.coords.longitude;
    console.log(lat, long);
}

function clearIntroView() {
    // todo: remove .intro-text and .begin-button from dom
    $(".intro-text").hide('slow');
    $(".begin-button").hide('slow');
    $(".location-form").hide('slow');
}

function loadResultView(data) {
    // todo: unpack other values; show weather, load wardrobe from backend
    console.log(data);
    var location = data.location;
    var temp = data.temp;
    var hum = data.humidity;
    var pres = data.pressure;
    var wind_speed = data.wind_speed;
    var wind_deg = data.wind_deg;
    $(".weather-text").text(location + ", " + temp + "°C, tuule kiirus " + wind_speed + " m/s");

}


$(document).ready(function () {
    $(".intro-form").hide();
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(getLocationSuccessFunction, function (pos) {
            $(".begin-button").hide();
            $(".intro-form").show();
        });
    } else {
        alert('It seems like Geolocation, which is required for this page, is not enabled in your browser.');
    }
    $(".begin-button").click(function () {
        loadWeatherData(lat, long);
    });
    $(".location-form").submit(function (event){
        event.preventDefault();
        loadWeatherDataByLocation($(".form-control").val());
    });
});

