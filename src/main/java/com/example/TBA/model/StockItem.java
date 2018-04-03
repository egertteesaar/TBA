package com.example.TBA.model;

import javax.persistence.*;

@Entity
@Table(name = "stockitem_table")
public class StockItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    @Column(name = "quantity")
    private Integer quantity;

    public StockItem(Item item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}
