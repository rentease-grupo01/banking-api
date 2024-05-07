package com.upao.renteasegrupo1.backingservice.service;

import com.upao.renteasegrupo1.backingservice.mapper.PaymentMapper;
import com.upao.renteasegrupo1.backingservice.model.dto.PaymentRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.dto.PaymentResponseDTO;
import com.upao.renteasegrupo1.backingservice.model.entity.Payment;
import com.upao.renteasegrupo1.backingservice.model.entity.User;
import com.upao.renteasegrupo1.backingservice.repository.PaymentRepository;
import com.upao.renteasegrupo1.backingservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Transactional(readOnly = true)
    public List<PaymentResponseDTO> getAllPaymentsByUserId(Long userId) {
        List<Payment> payments = paymentRepository.findByUserId(userId);
        return paymentMapper.convertToListDTO(payments);
    }

    public PaymentResponseDTO createPayment(PaymentRequestDTO paymentRequestDTO) {
        // Convertir PaymentRequestDTO a Payment entity
        Payment payment = paymentMapper.convertToEntity(paymentRequestDTO);

        // Guardar el pago en la base de datos
        Payment savedPayment = paymentRepository.save(payment);

        // Convertir Payment entity a PaymentResponseDTO
        return paymentMapper.convertToDTO(savedPayment);
    }


}
