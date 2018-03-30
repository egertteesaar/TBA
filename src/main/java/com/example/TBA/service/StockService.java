package com.example.TBA.service;

import com.example.TBA.model.Stock;
import com.example.TBA.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    @Autowired
    StockRepository repository;

    public void create(Stock stock) {
        repository.save(stock);
    }

    public List<Stock> getAll() {
        return repository.findAll();
    }

    public Stock getById(long id) {
        return repository.findOne(id);
    }
}
