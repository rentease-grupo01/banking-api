package com.upao.renteasegrupo1.backingservice.controller;

import com.upao.renteasegrupo1.backingservice.model.dto.NotificationResponseDTO;
import com.upao.renteasegrupo1.backingservice.mapper.NotificationMapper;
import com.upao.renteasegrupo1.backingservice.model.entity.Notification;
import com.upao.renteasegrupo1.backingservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Autowired
    public NotificationController(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationResponseDTO>> getNotificationsByUserId(@PathVariable Long userId) {
        List<Notification> notifications = notificationRepository.findByUserIdAndReadFalse(userId);
        List<NotificationResponseDTO> notificationDTOs = notificationMapper.convertToListDTO(notifications);
        return new ResponseEntity<>(notificationDTOs, HttpStatus.OK);
    }

    @PostMapping("/markAsRead/{notificationId}")
    public ResponseEntity<Void> markNotificationAsRead(@PathVariable Long notificationId) {
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        if (notification.isPresent()) {
            Notification updatedNotification = notification.get();
            updatedNotification.setRead(true);
            notificationRepository.save(updatedNotification);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
