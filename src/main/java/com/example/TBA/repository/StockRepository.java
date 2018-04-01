package com.example.TBA.repository;

import com.example.TBA.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepository extends JpaRepository<Item, Long> {

    @Query(value = "select * from stock_table inner join stockitem_table on stockitem_table.item_id=stock_table.id "
            + "where stockitem_table.quantity<5", nativeQuery = true)
    public List<Item> findItemsWithQuantityLessThanFive();

}
