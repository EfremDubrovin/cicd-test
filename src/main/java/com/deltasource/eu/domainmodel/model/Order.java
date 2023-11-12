package com.deltasource.eu.domainmodel.model;

import java.math.BigDecimal;
import javax.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "order")
    private List<Shipment> shipments;

    public Order(String orderNumber, BigDecimal totalAmount, List<Shipment> shipments) {
        this.shipments = shipments;
        this.orderNumber = orderNumber;
        this.totalAmount = totalAmount;
    }

    public Order(String orderNumber, BigDecimal totalAmount) {
        this.orderNumber = orderNumber;
        this.totalAmount = totalAmount;
    }
}