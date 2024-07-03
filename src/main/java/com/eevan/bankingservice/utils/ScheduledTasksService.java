package com.eevan.bankingservice.utils;

import com.eevan.bankingservice.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledTasksService {
    @Autowired
    private ClientService clientService;

    @Scheduled(fixedRate = 60000)
    public void updateClientBalances() {
        clientService.updateBalance();
    }
}
