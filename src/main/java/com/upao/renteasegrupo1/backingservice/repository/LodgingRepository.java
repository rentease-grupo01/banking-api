package com.upao.renteasegrupo1.backingservice.repository;

import com.upao.renteasegrupo1.backingservice.model.entity.Lodging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LodgingRepository extends JpaRepository<Lodging, Long> {
    List<Lodging> findByTitleContainingIgnoreCase(String title);
    List<Lodging> findByLocationAndPriceLessThanEqual(String location, BigDecimal maxPrice);
    List<Lodging> findByPriceLessThanEqual(BigDecimal maxPrice);
    List<Lodging> findByLocationIgnoreCase(String location);
}

