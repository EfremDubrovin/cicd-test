package com.deltasource.eu.domainmodel.repository;

import com.deltasource.eu.domainmodel.model.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByBarcode(String barcode);
}
