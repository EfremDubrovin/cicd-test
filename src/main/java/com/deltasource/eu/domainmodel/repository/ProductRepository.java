package com.deltasource.eu.domainmodel.repository;

import com.deltasource.eu.domainmodel.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
