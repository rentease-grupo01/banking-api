package com.upao.renteasegrupo1.backingservice.repository;

import com.upao.renteasegrupo1.backingservice.model.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    // Método para encontrar un contrato por su ID
    Optional<Contract> findById(Long id);

    // Método para encontrar contratos por estado
    List<Contract> findByStatus(String status);

    // Métodos para encontrar contratos por fecha exacta de inicio y término
    List<Contract> findByStartDate(LocalDate startDate);
    List<Contract> findByEndDate(LocalDate endDate);

    List<Contract> findByUserId(long id);

    List<Contract> findByPaymentStatusAndEndDateAfter(String paymentStatus, LocalDate endDate);
}
