package com.example.todoappapi.projects;

import com.example.todoappapi.tasks.Task;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<Task> tasks;

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
