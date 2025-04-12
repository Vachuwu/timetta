package com.example.core_service.service;

import com.example.core_service.model.WorkLog;
import com.example.core_service.repository.WorkLogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkLogServiceTest {

    @Mock
    private WorkLogRepository repository;

    @InjectMocks
    private WorkLogService service;

    private WorkLog createTestWorkLog() {
        WorkLog workLog = new WorkLog();
        workLog.setId(1L);
        workLog.setUserId(1L);
        workLog.setTaskType("Development");
        workLog.setWorkDate(LocalDate.now());
        workLog.setStartTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
        workLog.setEndTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0)));
        return workLog;
    }

    @Test
    void testCreateWorkLog() {
        WorkLog workLog = createTestWorkLog();
        when(repository.save(any(WorkLog.class))).thenReturn(workLog);

        WorkLog result = service.create(workLog);

        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        verify(repository, times(1)).save(workLog);
    }

    @Test
    void testGetById() {
        WorkLog workLog = createTestWorkLog();
        when(repository.findById(1L)).thenReturn(Optional.of(workLog));

        WorkLog result = service.getById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetByIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.getById(1L));
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(List.of(createTestWorkLog(), createTestWorkLog()));

        List<WorkLog> result = service.getAll();

        assertEquals(2, result.size());
    }

    @Test
    void testGetAllByUserId() {
        when(repository.findByUserId(1L)).thenReturn(List.of(createTestWorkLog(), createTestWorkLog()));

        List<WorkLog> result = service.getAllByUserId(1L);

        assertEquals(2, result.size());
    }

    @Test
    void testUpdateWorkLog() {
        WorkLog existingWorkLog = createTestWorkLog();
        WorkLog updatedWorkLog = createTestWorkLog();
        updatedWorkLog.setUserId(2L);

        when(repository.findById(1L)).thenReturn(Optional.of(existingWorkLog));
        when(repository.save(any(WorkLog.class))).thenReturn(existingWorkLog);

        WorkLog result = service.update(1L, updatedWorkLog);

        assertNotNull(result);
        assertEquals(2L, result.getUserId());
        verify(repository, times(1)).save(existingWorkLog);
    }

    @Test
    void testDeleteWorkLog() {
        service.delete(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void testValidateWorkLogInvalidTime() {
        WorkLog workLog = createTestWorkLog();
        workLog.setStartTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0)));
        workLog.setEndTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));

        assertThrows(IllegalArgumentException.class, () -> service.create(workLog));
    }
}