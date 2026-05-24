package com.tksystem.project_manager.domain.service.project;

import org.springframework.stereotype.Service;

import com.tksystem.project_manager.domain.model.ProjectDetail;
import com.tksystem.project_manager.domain.repository.ProjectRepository;

@Service
public class ProjectDetailService {

    ProjectRepository repository;

    public ProjectDetailService(ProjectRepository repository) {
        this.repository = repository;
    }

    public ProjectDetail geProjectDetail(long projectId) {
        return repository.getProjectDetail(projectId);
    }

}
