package com.example.TBA.rest;

import com.example.TBA.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WeatherResource {
    @Autowired
    WeatherService service;

    @RequestMapping(value = "/api/location/{latitude}/{longitude}", method = RequestMethod.GET)
    public ResponseEntity<String> getWeather(@PathVariable String latitude, @PathVariable String longitude)
            throws IOException{
        String result = service.getWeatherByLatitudeLongitude(latitude, longitude);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
}
