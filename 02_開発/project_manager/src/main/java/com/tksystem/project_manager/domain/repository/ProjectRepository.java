package com.tksystem.project_manager.domain.repository;

import org.apache.ibatis.annotations.Mapper;

import com.tksystem.project_manager.domain.entity.Project;
import com.tksystem.project_manager.domain.model.ProjectDetail;

@Mapper
public interface ProjectRepository {

    public ProjectDetail getProjectDetail(long projectId);

    public Long insertProject(Project project);

    public Long updateProject(Project project);

}
