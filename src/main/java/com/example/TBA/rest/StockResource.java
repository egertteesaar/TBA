package com.example.TBA.rest;

import com.example.TBA.model.StockObj;
import com.example.TBA.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StockResource {
    @Autowired
    StockService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "todo";
    }

    @RequestMapping(value = "/api/stock", method = RequestMethod.GET)
    public ResponseEntity<String> getObj() {
        List<StockObj> allObjs = service.getAll();
        return new ResponseEntity<>(allObjs.toString(), HttpStatus.OK);
    }

}
