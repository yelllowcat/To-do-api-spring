package com.example.todoappapi.tasks;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Date;

public record TaskResponseDto(
        Integer id,
        String title,
        String Description,
        LocalDate dueDate,
        Boolean isCompleted,
        Integer projectId
) {

}
