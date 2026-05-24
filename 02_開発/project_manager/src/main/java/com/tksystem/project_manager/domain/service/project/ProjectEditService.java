package com.tksystem.project_manager.domain.service.project;

import org.springframework.stereotype.Service;

import com.tksystem.project_manager.app.dto.ProjectEditDto;
import com.tksystem.project_manager.domain.entity.Project;
import com.tksystem.project_manager.domain.model.ProjectDetail;
import com.tksystem.project_manager.domain.repository.ProjectRepository;

@Service
public class ProjectEditService {

    private ProjectRepository repository;

    public ProjectEditService(ProjectRepository repository) {
        this.repository = repository;
    }

    public ProjectDetail getProjectDetail(long projectId) {
        return repository.getProjectDetail(projectId);
    }

    public Long upsertProject(ProjectEditDto projectDto) {
        Project project = Project.builder()
                .projectId(projectDto.getProjectId())
                .projectCode(projectDto.getProjectCode())
                .projectName(projectDto.getProjectName())
                .clientId(projectDto.getClientId())
                .orderDate(projectDto.getOrderDate())
                .deliveryDate(projectDto.getDeliveryDate())
                .description(projectDto.getDescription())
                .build();

        if (projectDto.getProjectId() == null) {
            return repository.insertProject(project);
        } else {
            return repository.updateProject(project);
        }

    }

}
