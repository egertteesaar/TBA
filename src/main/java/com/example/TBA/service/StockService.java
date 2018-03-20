package com.example.TBA.service;

import com.example.TBA.model.StockObj;
import com.example.TBA.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    @Autowired
    StockRepository repository;

    public void create(StockObj stockObj) {
        repository.save(stockObj);
    }

    public List<StockObj> getAll() {
        return repository.findAll();
    }
}
