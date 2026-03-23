package com.example.todoappapi.projects;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
