package com.example.TBA.rest;

import com.example.TBA.model.Item;
import com.example.TBA.service.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StockResource {
    @Autowired
    StockService service;

    @RequestMapping(value = "/api/stock", method = RequestMethod.GET)
    public String getAllObjs() throws JsonProcessingException{
        List<Item> allObjs = service.getAll();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(allObjs);
    }

    @RequestMapping(value = "/api/stock/{id}", method = RequestMethod.GET)
    public String getItemById(@PathVariable Long id) throws JsonProcessingException{
        Item item = service.getById(id);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(item);
    }

    @RequestMapping(value = "/api/stock/lowInStock", method = RequestMethod.GET)
    public String getItemsLowInStock() throws JsonProcessingException{
        List<Item> items = service.getItemsWithQuantityLessThanFive();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(items);
    }

}
