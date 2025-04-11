package com.example.core_service.model;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Entity
@Table(name = "work_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @Column(nullable = false)
    @NotNull(message = "Work date cannot be null")
    private LocalDate workDate;

    @Column(nullable = false)
    @NotNull(message = "Start time cannot be null")
    private LocalDateTime startTime;

    @Column(nullable = false)
    @NotNull(message = "End time cannot be null")
    private LocalDateTime endTime;

    @Column(nullable = false)
    @NotBlank(message = "Task type cannot be blank")
    private String taskType;

    @Column(nullable = true)
    private String description;

    @Transient
    public double getHoursSpent() {
        return ChronoUnit.MINUTES.between(startTime, endTime) / 60.0;
    }

    @Transient
    public String getFormattedSummary() {
        return String.format("%s - %.1f ч - %d %s",
                taskType,
                getHoursSpent(),
                workDate.getDayOfMonth(),
                workDate.getMonth().toString().toLowerCase());
    }
}