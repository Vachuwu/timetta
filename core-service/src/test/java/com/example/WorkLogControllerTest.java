package com.example.core_service.controller;

import com.example.core_service.model.WorkLog;
import com.example.core_service.service.WorkLogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkLogControllerTest {

    @Mock
    private WorkLogService service;

    @InjectMocks
    private WorkLogController controller;

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
        when(service.create(any(WorkLog.class))).thenReturn(workLog);

        WorkLog result = controller.create(workLog);

        assertNotNull(result);
        assertEquals(1L, result.getUserId());
    }

    @Test
    void testGetById() {
        WorkLog workLog = createTestWorkLog();
        when(service.getById(1L)).thenReturn(workLog);

        WorkLog result = controller.getById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetAll() {
        when(service.getAll()).thenReturn(List.of(createTestWorkLog(), createTestWorkLog()));

        List<WorkLog> result = controller.getAll();

        assertEquals(2, result.size());
    }

    @Test
    void testGetAllByUserId() {
        when(service.getAllByUserId(1L)).thenReturn(List.of(createTestWorkLog(), createTestWorkLog()));

        List<WorkLog> result = controller.getAllByUserId(1L);

        assertEquals(2, result.size());
    }

    @Test
    void testUpdateWorkLog() {
        WorkLog workLog = createTestWorkLog();
        when(service.update(eq(1L), any(WorkLog.class))).thenReturn(workLog);

        WorkLog result = controller.update(1L, workLog);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testDeleteWorkLog() {
        controller.delete(1L);
        verify(service, times(1)).delete(1L);
    }
}