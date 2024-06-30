package com.upao.renteasegrupo1.backingservice.service;

import com.upao.renteasegrupo1.backingservice.model.entity.Contract;
import com.upao.renteasegrupo1.backingservice.model.entity.Notification;
import com.upao.renteasegrupo1.backingservice.repository.ContractRepository;
import com.upao.renteasegrupo1.backingservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentReminderService {

    private final ContractRepository contractRepository;
    private final NotificationRepository notificationRepository;

    @Autowired
    public PaymentReminderService(ContractRepository contractRepository, NotificationRepository notificationRepository) {
        this.contractRepository = contractRepository;
        this.notificationRepository = notificationRepository;
    }

    @Scheduled(cron = "0 0 9 * * ?") // Programar para que se ejecute todos los días a las 9 AM
    public void sendPaymentReminders() {
        LocalDate today = LocalDate.now();
        List<Contract> contracts = contractRepository.findByPaymentStatusAndEndDateAfter("PENDING", today);

        for (Contract contract : contracts) {
            if (contract.getEndDate().isAfter(today) && contract.getPaymentStatus().equals("PENDING")) {
                Notification notification = new Notification();
                notification.setUser(contract.getUser());
                notification.setMessage("You have a pending payment.");
                notification.setDate(today);
                notification.setRead(false);
                notificationRepository.save(notification);
            }
        }
    }
}
