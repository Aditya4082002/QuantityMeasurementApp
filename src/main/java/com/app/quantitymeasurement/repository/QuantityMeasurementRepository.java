package com.app.quantitymeasurement.repository;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.quantitymeasurement.model.QuantityMeasurementEntity;

@Repository
public interface QuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Long> {

    //find by operation (ADD, SUBTRACT, etc.)
    List<QuantityMeasurementEntity> findByOperation(String operation);

    //get records after a certain time
    List<QuantityMeasurementEntity> findByCreatedAtAfter(LocalDateTime time);

    //count operations
    long countByOperation(String operation);
}
