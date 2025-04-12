package com.example.core_service.repository;

import com.example.core_service.model.WorkLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class WorkLogRepositoryTest {

    @Autowired
    private WorkLogRepository repository;

    private WorkLog createTestWorkLog(Long userId) {
        WorkLog workLog = new WorkLog();
        workLog.setUserId(userId);
        workLog.setWorkDate(LocalDate.now());
        workLog.setTaskType("Development");
        workLog.setStartTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
        workLog.setEndTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0)));
        return workLog;
    }

    @Test
    void testFindByUserId() {
        repository.save(createTestWorkLog(1L));

        List<WorkLog> result = repository.findByUserId(1L);

        assertFalse(result.isEmpty());
        assertEquals(1L, result.get(0).getUserId());
    }

    @Test
    void testFindByWorkDate() {
        LocalDate today = LocalDate.now();
        repository.save(createTestWorkLog(1L));

        List<WorkLog> result = repository.findByWorkDate(today);

        assertFalse(result.isEmpty());
        assertEquals(today, result.get(0).getWorkDate());
    }

    @Test
    void testFindByUserIdAndWorkDate() {
        LocalDate today = LocalDate.now();
        repository.save(createTestWorkLog(1L));

        List<WorkLog> result = repository.findByUserIdAndWorkDate(1L, today);

        assertFalse(result.isEmpty());
        assertEquals(1L, result.get(0).getUserId());
        assertEquals(today, result.get(0).getWorkDate());
    }
}