package com.upao.renteasegrupo1.backingservice.repository;

import com.upao.renteasegrupo1.backingservice.model.entity.Lodging;

import java.util.List;
import java.util.Optional;

public interface LodgingRepository {
    List<Lodging> findAll();

    default Optional<Object> findById() {
        return findById(null);
    }

    Optional<Object> findById(Long id);


}

