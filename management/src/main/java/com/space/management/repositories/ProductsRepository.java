package com.space.management.repositories;

import com.space.management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByMissionName(String missionName);
    List<Product> findAllByAcquisitionTimeIsBetween(LocalDateTime lowerTimeBound, LocalDateTime higherTimeBound);
}
