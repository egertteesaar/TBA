package com.example.TBA.rest;

import com.example.TBA.model.StockObj;
import com.example.TBA.service.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StockResource {
    @Autowired
    StockService service;

    @RequestMapping(value = "/api/stock", method = RequestMethod.GET)
    public String getObj() throws JsonProcessingException{
        List<StockObj> allObjs = service.getAll();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(allObjs);
    }

}
