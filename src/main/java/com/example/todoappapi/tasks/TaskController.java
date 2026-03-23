package com.example.todoappapi.tasks;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public TaskResponseDto saveTask(
            @Valid @RequestBody TaskDto dto
    ){
        return taskService.createTask(dto);
    }

    @GetMapping("/tasks")
    public List<TaskResponseDto> findAllTasks(){
        return  taskService.findAllTasks();
    }


    @GetMapping("/tasks/{id}")
    public TaskResponseDto findTaskById(
            @PathVariable Integer id
    ){
        return taskService.findTaskByID(id);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTaskById(
            @PathVariable Integer id
    ){
        taskService.deleteTask(id);
    }

    @PostMapping("/tasks/{id}")
    public TaskResponseDto updateTask(
            @PathVariable Integer id,
            @RequestBody TaskDto dto
    ){
        return taskService.updateTask(id, dto);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exp
    ){
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError)error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName,errorMessage);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


}
