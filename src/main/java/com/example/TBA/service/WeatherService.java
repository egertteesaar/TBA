package com.example.TBA.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Service
public class WeatherService {

    private final String apiRoot = "https://api.openweathermap.org/data/2.5/weather?";

    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);

    private String getApiKey() throws IOException {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/main/resources/local.properties"));
            return props.getProperty("openWeatherApiKey");
        } catch (FileNotFoundException e) {
            log.error("File not found");
        }
        return null;
    }

    public String getWeatherByLatitudeLongitude(String latitude, String longitude) throws IOException {
        String apiKey = getApiKey();
        String uri = apiRoot + "apiKey=" + apiKey + "&lat=" + latitude + "&lon=" + longitude
                + "&units=metric";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
    }

    public String getWeatherByCity(String city) throws IOException {
        String apiKey = getApiKey();
        String uri = apiRoot + "apiKey=" + apiKey + "&city=" + city + "&units=metric";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
    }

}
