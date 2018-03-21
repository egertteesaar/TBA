package com.example.TBA.rest;

import com.example.TBA.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(WeatherResource.class);

    @RequestMapping(value = "/api/location/{latitude}/{longitude}", method = RequestMethod.GET)
    public String getWeather(@PathVariable String latitude, @PathVariable String longitude)
            throws IOException{
        return service.getWeatherByLatitudeLongitude(latitude, longitude);
    }


    @RequestMapping(value = "/api/location/{location}", method = RequestMethod.GET)
    public String getWeatherByLocation(@PathVariable String location)
            throws IOException{
        return service.getWeatherByCity(location);
    }
}
