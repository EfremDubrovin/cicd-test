package com.deltasource.eu.domainmodel.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "products")
    private List<Shipment> shipments = new ArrayList<>();

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "barcode", unique = true)
    private String barcode;

    public Product(String productName, BigDecimal price, String barcode, List<Shipment> shipments) {
        this.shipments = shipments;
        this.productName = productName;
        this.price = price;
        this.barcode = barcode;
    }

    public Product(String productName, BigDecimal price, String barcode) {
        this.productName = productName;
        this.price = price;
        this.barcode = barcode;
    }
}