package com.example.TBA.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        return extractData(uri);
    }

    public String getWeatherByCity(String city) throws IOException {
        String apiKey = getApiKey();
        String uri = apiRoot + "apiKey=" + apiKey + "&q=" + city + "&units=metric";
        return extractData(uri);
    }

    /**
     * Parse JSON data from external API and extract specific values into new JSON object
     * @param uri
     * @return
     * @throws IOException
     */
    private String extractData(String uri) throws IOException{
        RestTemplate restTemplate = new RestTemplate();
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map<String, Object> parsedMap = parser.parseMap(restTemplate.getForObject(uri, String.class));
        log.info(parsedMap.toString());
        Map<String, Object> main = unpackMain(parsedMap);
        Map<String, Object> wind = unpackWind(parsedMap);
        Map<String, String> flatMap = new HashMap<>();
        flatMap.put("location", parsedMap.get("name").toString());
        flatMap.put("temp", main.get("temp").toString());
        if (main.containsKey("temp_min"))
            flatMap.put("temp_min", main.get("temp_min").toString());
        if (main.containsKey("temp_max"))
            flatMap.put("temp_max", main.get("temp_max").toString());
        if (main.containsKey("humidity"))
            flatMap.put("humidity", main.get("humidity").toString());
        if (main.containsKey("pressure"))
            flatMap.put("pressure", main.get("pressure").toString());
        if (wind.containsKey("speed"))
            flatMap.put("wind_speed", wind.get("speed").toString());
        if (wind.containsKey("deg"))
            flatMap.put("wind_deg", wind.get("deg").toString());
        // todo: get sunrise & sunset
        // todo: add custom description based on temp, humidity and wind speed
        return new ObjectMapper().writeValueAsString(flatMap);
    }

    @JsonProperty("main")
    private Map<String, Object> unpackMain(Map<String, Object> nested) {
        return (Map<String, Object>) nested.get("main");
    }

    @JsonProperty("wind")
    private Map<String, Object> unpackWind(Map<String, Object> nested) {
        return (Map<String, Object>) nested.get("wind");
    }
}
