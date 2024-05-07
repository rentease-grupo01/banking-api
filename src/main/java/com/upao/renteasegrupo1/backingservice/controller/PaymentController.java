package com.upao.renteasegrupo1.backingservice.controller;

import com.upao.renteasegrupo1.backingservice.model.dto.PaymentRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.dto.PaymentResponseDTO;
import com.upao.renteasegrupo1.backingservice.model.entity.Payment;
import com.upao.renteasegrupo1.backingservice.service.PaymentService;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.upao.renteasegrupo1.backingservice.model.dto.UserRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.dto.UserResponseDTO;
import com.upao.renteasegrupo1.backingservice.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payments")
@AllArgsConstructor
@Data
public class PaymentController {

    private final PaymentService paymentService;
    private final UserService userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllPaymentsByUserId(@PathVariable Long userId) {
        try {
            List<PaymentResponseDTO> payments = paymentService.getAllPaymentsByUserId(userId);
            List<Map<String, Object>> simplePayments = payments.stream()
                    .map(payment -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("paymentId", payment.getId());  // Asumiendo que quieres el ID del pago también
                        map.put("metodoDePago", payment.getMetodoDePago());  // Ejemplo: añadiendo método de pago
                        map.put("fecha", payment.getFecha());  // Añadiendo la fecha

                        // Extrayendo solo el ID y username del usuario asociado al pago
                        // Asegurándote de que el objeto User no es nulo
                        map.put("userId", payment.getUser().getId());
                        map.put("username", payment.getUser().getUsername());


                        return map;
                    })
                    .collect(Collectors.toList());

            return new ResponseEntity<>(simplePayments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
