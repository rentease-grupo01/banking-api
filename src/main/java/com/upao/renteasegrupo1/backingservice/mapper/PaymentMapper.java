package com.upao.renteasegrupo1.backingservice.mapper;

import com.upao.renteasegrupo1.backingservice.model.dto.PaymentRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.dto.PaymentResponseDTO;
import com.upao.renteasegrupo1.backingservice.model.entity.Payment;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@AllArgsConstructor

public class PaymentMapper {

    private final ModelMapper modelMapper;

    public Payment convertToEntity(PaymentRequestDTO paymentRequestDTO) {
        return modelMapper.map(paymentRequestDTO, Payment.class);
    }

    public PaymentResponseDTO convertToDTO(Payment payment) {
        return modelMapper.map(payment, PaymentResponseDTO.class);
    }

    public List<PaymentResponseDTO> convertToListDTO(List<Payment> payments) {
        return payments.stream()
                .map(this::convertToDTO)
                .toList();

    }
}
