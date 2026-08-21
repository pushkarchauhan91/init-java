package com.javatechie.config;

import com.javatechie.repository.ProductRepository;
import com.javatechie.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class RecordMonitoringScheduler {

    private final ProductRepository productRepository;
    private final TaskRepository taskRepository;

    @Scheduled(cron = "0 */5 * * * ?")
    public void checkForRecentChanges() {
        LocalDateTime cutoff = LocalDateTime.now().minusMinutes(5);

        int recentProducts = productRepository.findRecentlyChanged(cutoff).size();
        int recentTasks = taskRepository.findRecentlyChanged(cutoff).size();

        if (recentProducts > 0 || recentTasks > 0) {
            log.info("Recent record activity detected in last 5 minutes: products={}, tasks={}", recentProducts, recentTasks);
        } else {
            log.info("No new or modified records detected in the last 5 minutes.");
        }
    }
}
