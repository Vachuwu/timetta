package com.example.core_service.repository;

import com.example.core_service.model.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;

public interface WorkLogRepository extends JpaRepository<WorkLog, Long> {
    List<WorkLog> findByUserId(Long userId); // Все записи пользователя
    List<WorkLog> findByWorkDate(LocalDate date); // Все записи за дату
    List<WorkLog> findByUserIdAndWorkDate(Long userId, LocalDate date); // Записи пользователя за дату
}