function loadWeatherData(latitude, longitude) {
    $.ajax({
        url: "/api/location/" + latitude + "/" + longitude,
        success: function(data) {
            data = $.parseJSON(data);
            console.log(data);
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


$(document).ready(function () {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(getLocationSuccessFunction, function (e) {console.log(e)});
    } else {
        alert('It seems like Geolocation, which is required for this page, is not enabled in your browser.');
    }
});

