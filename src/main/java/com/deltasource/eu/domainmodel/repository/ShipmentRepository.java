package com.deltasource.eu.domainmodel.repository;

import com.deltasource.eu.domainmodel.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
