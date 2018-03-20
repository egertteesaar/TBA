package com.example.TBA.repository;

import com.example.TBA.model.StockObj;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockObj, Long> {

}
