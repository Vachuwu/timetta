package com.example.core_service.controller;


import org.springframework.web.bind.annotation.*;
import java.util.*;
import lombok.RequiredArgsConstructor;

import com.example.core_service.model.WorkLog;
import com.example.core_service.service.WorkLogService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/worklogs")
@RequiredArgsConstructor
public class WorkLogController {

    private final WorkLogService service;

    @PostMapping
    public WorkLog create(@RequestBody WorkLog workLog) {
        return service.create(workLog);
    }

    @GetMapping("/{id}")
    public WorkLog getById(@PathVariable Long id) {
        return service.getById(id);
    }


    @GetMapping
    public List<WorkLog> getAll() {
        return service.getAll();
    }

    @GetMapping("/user/{userId}")
    public List<WorkLog> getAllByUserId(@PathVariable Long userId) {
        return service.getAllByUserId(userId);
    }

    @PutMapping("/{id}")
    public WorkLog update(@PathVariable Long id, @RequestBody WorkLog workLog) {
        return service.update(id, workLog);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}