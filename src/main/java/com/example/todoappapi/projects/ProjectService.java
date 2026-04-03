package com.example.todoappapi.projects;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public List<ProjectDto> findAll(){
        return projectRepository.findAll()
                .stream()
                .map(projectMapper::toProjectDto)
                .collect(Collectors.toList());
    }

    public ProjectDto findById(Integer id){
        return projectRepository.findById(id)
                .map(projectMapper::toProjectDto)
                .orElse(null);
    }

    public ProjectDto saveProject(ProjectDto dto){
        var project = projectMapper.toProject(dto);
        var savedProject = projectRepository.save(project);
        return projectMapper.toProjectDto(savedProject);
    }

    public void deleteProject(Integer id){
        projectRepository.deleteById(id);
    }

    public ProjectDto updateProject(Integer id, ProjectDto dto){
        var project = projectMapper.toProject(dto);
        project.setId(id);
        project.setName(dto.name());
        projectRepository.save(project);
        return projectMapper.toProjectDto(project);
    }

}
