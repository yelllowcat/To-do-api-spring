package com.example.todoappapi.tasks;

import com.example.todoappapi.projects.Project;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    private String description;

    private LocalDate dueDate;

    private Boolean isCompleted;

    @ManyToOne
    @JoinColumn(
            name = "project_id"
    )
    private Project project;

    public Task() {
    }

    public Task(String title, String description, Project project) {
        this.title = title;
        this.description = description;
        this.project = project;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }



}
