package com.example.TBA.controller;

import com.example.TBA.model.TestObj;
import com.example.TBA.service.TestObjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WebController {
    @Autowired
    TestObjService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "hello, world!!";
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public ResponseEntity<String> getObj() {
        List<TestObj> allObjs = service.getAllObjs();
        String r = "Kõik objektid \n";
        for (TestObj o : allObjs) {
            r += o.toString() + "\n";
        }
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @RequestMapping(value="/accounts/a", method = RequestMethod.GET)
    public ResponseEntity<String> createObj() {
        TestObj o = new TestObj(new Long(1), "Marko");
        service.create(o);
        return new ResponseEntity<>("TestObj loodi takistusteta! \n", HttpStatus.CREATED);
    }
    /*
    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteObj(@PathVariable Long id) {

    }
    */

}
