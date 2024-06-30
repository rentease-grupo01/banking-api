package com.upao.renteasegrupo1.backingservice.mapper;

import com.upao.renteasegrupo1.backingservice.model.dto.NotificationResponseDTO;
import com.upao.renteasegrupo1.backingservice.model.entity.Notification;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NotificationMapper {

    private final ModelMapper modelMapper;

    public NotificationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public NotificationResponseDTO convertToDTO(Notification notification) {
        return modelMapper.map(notification, NotificationResponseDTO.class);
    }

    public List<NotificationResponseDTO> convertToListDTO(List<Notification> notifications) {
        return notifications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
