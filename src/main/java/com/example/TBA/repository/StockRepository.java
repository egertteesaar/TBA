package com.example.TBA.repository;

import com.example.TBA.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Item, Long> {

}
