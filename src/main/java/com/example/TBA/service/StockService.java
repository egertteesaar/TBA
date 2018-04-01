package com.example.TBA.service;

import com.example.TBA.model.Item;
import com.example.TBA.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    @Autowired
    StockRepository repository;

    public void create(Item item) {
        repository.save(item);
    }

    public List<Item> getAll() {
        return repository.findAll();
    }

    public Item getById(long id) {
        return repository.findOne(id);
    }
}
