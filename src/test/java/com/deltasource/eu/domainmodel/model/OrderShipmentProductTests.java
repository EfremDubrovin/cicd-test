package com.deltasource.eu.domainmodel.model;

import com.deltasource.eu.domainmodel.repository.OrderRepository;
import com.deltasource.eu.domainmodel.repository.ProductRepository;
import com.deltasource.eu.domainmodel.repository.ShipmentRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class OrderShipmentProductTests {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testSaveAndRetrieveOrder() {
        Order order = new Order();
        order.setOrderNumber("ORD123");
        order.setTotalAmount(new BigDecimal("100.0"));

        orderRepository.save(order);
        Order retrievedOrder = orderRepository.findById(order.getId()).orElse(null);

        assertNotNull(retrievedOrder);
        assertEquals(order.getOrderNumber(), retrievedOrder.getOrderNumber());
        assertEquals(order.getTotalAmount(), retrievedOrder.getTotalAmount());
    }

    @Test
    void testSaveAndRetrieveShipment() {
        Order order = new Order();
        order.setOrderNumber("ORD456");
        order.setTotalAmount(new BigDecimal("200.0"));
        orderRepository.save(order);

        Shipment shipment = new Shipment();
        shipment.setOrder(order);
        shipment.setTrackingNumber("TRACK123");
        shipment.setShippingAddress("123 Main St");

        shipmentRepository.save(shipment);
        Shipment retrievedShipment = shipmentRepository.findById(shipment.getId()).orElse(null);

        assertNotNull(retrievedShipment);
        assertEquals(shipment.getTrackingNumber(), retrievedShipment.getTrackingNumber());
        assertEquals(shipment.getShippingAddress(), retrievedShipment.getShippingAddress());
    }

    @Test
    void testSaveAndRetrieveProduct() {
        Product product = new Product();
        product.setProductName("Laptop");
        product.setPrice(new BigDecimal("999.99"));

        productRepository.save(product);
        Product retrievedProduct = productRepository.findById(product.getId()).orElse(null);

        assertNotNull(retrievedProduct);
        assertEquals(product.getProductName(), retrievedProduct.getProductName());
        assertEquals(product.getPrice(), retrievedProduct.getPrice());
    }

    @Test
    void testOrderShipmentRelationship() {
        Product product1 = new Product();
        product1.setProductName("Tablet");
        product1.setPrice(new BigDecimal("299.99"));

        Product product2 = new Product();
        product2.setProductName("Smartphone");
        product2.setPrice(new BigDecimal("599.99"));
        productRepository.save(product2);

        Order order = new Order();
        order.setOrderNumber("ORD789");
        order.setTotalAmount(new BigDecimal("300.0"));

        Shipment shipment1 = new Shipment();
        shipment1.setTrackingNumber("TRACK789");
        shipment1.setShippingAddress("456 Oak St");

        shipment1.setProducts(List.of(productRepository.save(product1)));

        Shipment shipment2 = new Shipment();
        shipment2.setTrackingNumber("TRACK987");
        shipment2.setShippingAddress("789 Pine St");

        shipment2.setProducts(List.of(productRepository.save(product2)));

        order.setShipments(Arrays.asList(shipment1, shipment2));

        orderRepository.save(order);

        Order retrievedOrder = orderRepository.findById(order.getId()).orElse(null);

        assertNotNull(retrievedOrder);
        assertEquals(2, retrievedOrder.getShipments().size());
    }

    @Test
    void testShipmentProductRelationship() {
        Product product1 = new Product();
        product1.setProductName("Tablet");
        product1.setPrice(new BigDecimal("299.99"));

        Product product2 = new Product();
        product2.setProductName("Smartphone");
        product2.setPrice(new BigDecimal("599.99"));

        Shipment shipment = new Shipment();
        shipment.setTrackingNumber("TRACK555");
        shipment.setShippingAddress("999 Elm St");
        shipment.setProducts(Arrays.asList(productRepository.save(product2), productRepository.save(product1)));

        Order order = new Order();
        order.setOrderNumber("ORD789");
        order.setTotalAmount(new BigDecimal("300.0"));

        shipment.setOrder(orderRepository.save(order));  // Set the order

        shipmentRepository.save(shipment);

        Shipment retrievedShipment = shipmentRepository.findById(shipment.getId()).orElse(null);

        assertNotNull(retrievedShipment);
        assertEquals(2, retrievedShipment.getProducts().size());
    }
}
