package com.example.todoappapi.tasks;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Date;

public record TaskDto(
       @NotBlank String title,
        String description,
        LocalDate dueDate,
        Boolean isCompleted,
        Integer projectId
) {
}
