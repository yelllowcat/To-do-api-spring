package com.example.todoappapi.projects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public List<ProjectDto> findAll(){
        return projectService.findAll();

    }

    @GetMapping("/projects/{id}")
    public ProjectDto findAProjectById(@PathVariable Integer id){
        return projectService.findById(id);
    }

    @PostMapping("/projects")
    public ProjectDto saveProject(
            @RequestBody ProjectDto dto
    ){
        return projectService.saveProject(dto);
    }

    @DeleteMapping("/projects/{id}")
    public void deleteProject(
            @PathVariable Integer id
    ){
        projectService.deleteProject(id);
    }

    @PostMapping("/projects/{id}")
    public ProjectDto updateProject(
            @PathVariable Integer id,
            @RequestBody ProjectDto dto
    ){
    return  projectService.updateProject(id,dto);
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
