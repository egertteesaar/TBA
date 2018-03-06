package com.example.TBA.service;

import com.example.TBA.model.TestObj;
import com.example.TBA.repository.TestObjRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestObjService {
    @Autowired
    TestObjRepository repository;

    public void create(TestObj testObj) {
        repository.save(testObj);
    }

    public List<TestObj> getAllObjs() {
        return repository.findAll();
    }
}
