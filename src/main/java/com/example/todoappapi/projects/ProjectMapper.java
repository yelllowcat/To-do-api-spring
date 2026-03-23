package com.example.todoappapi.projects;

import org.springframework.stereotype.Service;

@Service
public class ProjectMapper {

    public ProjectDto toProjectDto(Project project){
        return  new ProjectDto(project.getName());
    }

    public Project toProject(ProjectDto dto){
        return new Project(dto.name());
    }
}
