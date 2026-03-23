package com.example.todoappapi.tasks;

import com.example.todoappapi.projects.Project;
import org.springframework.stereotype.Service;

@Service
public class TaskMapper {

    public Task toTask(TaskDto dto){
        if (dto == null){
            return null;
        }
        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setDueDate(dto.dueDate());
        task.setCompleted(false);
        return task;
    }

    public TaskResponseDto toTaskResponseDto(Task task){
        if (task == null){
            return null;
        }
        return new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getCompleted(),
                task.getProject().getId() != null ? task.getProject().getId() :null
        );
    }

}
