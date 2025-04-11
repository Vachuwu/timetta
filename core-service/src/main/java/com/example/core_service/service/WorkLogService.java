package com.example.core_service.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import lombok.RequiredArgsConstructor;
import com.example.core_service.model.WorkLog;
import com.example.core_service.repository.WorkLogRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkLogService {

    private final WorkLogRepository repository;

    // Создание записи
    public WorkLog create(WorkLog workLog) {
        validateWorkLog(workLog);
        return repository.save(workLog);
    }

    // Получение записи по ID
    public WorkLog getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("WorkLog not found with id: " + id));
    }

    // Все записи
    public List<WorkLog> getAll() {
        return repository.findAll();
    }

    // Все записи пользователя
    public List<WorkLog> getAllByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    // Обновление записи
    public WorkLog update(Long id, WorkLog updatedWorkLog) {
        WorkLog existingWorkLog = getById(id);
        validateWorkLog(updatedWorkLog);

        existingWorkLog.setUserId(updatedWorkLog.getUserId());
        existingWorkLog.setWorkDate(updatedWorkLog.getWorkDate());
        existingWorkLog.setStartTime(updatedWorkLog.getStartTime());
        existingWorkLog.setEndTime(updatedWorkLog.getEndTime());
        existingWorkLog.setTaskType(updatedWorkLog.getTaskType());
        existingWorkLog.setDescription(updatedWorkLog.getDescription());

        return repository.save(existingWorkLog);
    }

    // Удаление записи
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Валидация
    private void validateWorkLog(WorkLog workLog) {
        if (workLog.getEndTime().isBefore(workLog.getStartTime())) {
            throw new IllegalArgumentException("End time must be after start time!");
        }
        if (!workLog.getStartTime().toLocalDate().equals(workLog.getWorkDate())) {
            throw new IllegalArgumentException("Work date must match start time date!");
        }
    }
}