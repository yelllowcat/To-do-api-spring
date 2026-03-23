package com.example.todoappapi.tasks;

import com.example.todoappapi.projects.Project;
import com.example.todoappapi.projects.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper mapper;

    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, TaskMapper mapper, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
        this.projectRepository = projectRepository;
    }

    public TaskResponseDto createTask(TaskDto dto){
        Task task = mapper.toTask(dto);

        Project project = projectRepository.getReferenceById(dto.projectId());
        task.setProject(project);

        Task savedTask = taskRepository.save(task);
        return mapper.toTaskResponseDto(savedTask);
    }

    public List<TaskResponseDto> findAllTasks(){
        return taskRepository.findAll()
                .stream()
                .map(mapper::toTaskResponseDto)
                .collect(Collectors.toList());
    }

    public TaskResponseDto findTaskByID(Integer id){
        return taskRepository.findById(id)
                .map(mapper::toTaskResponseDto)
                .orElse(null);
    }



    public void deleteTask(Integer id){
        taskRepository.deleteById(id);
    }

    public TaskResponseDto updateTask(Integer id,TaskDto dto){
        Task task = mapper.toTask(dto);
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setDueDate(dto.dueDate());
        task.setCompleted(dto.isCompleted());

        return  mapper.toTaskResponseDto(task);
    }

}
