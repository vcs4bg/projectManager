package com.tksystem.project_manager.app.controller.project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tksystem.project_manager.app.constants.ScreenName;
import com.tksystem.project_manager.app.controller.project.form.ProjectDetailForm;
import com.tksystem.project_manager.domain.model.ProjectDetail;
import com.tksystem.project_manager.domain.service.project.ProjectDetailService;

@Controller
@RequestMapping("/project/detail")
public class ProjectDetailController {

    ProjectDetailService service;

    public ProjectDetailController(ProjectDetailService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String show(@RequestParam Long projectId, Model model) {

        ProjectDetail projectDetail = service.geProjectDetail(projectId);

        ProjectDetailForm projectDetailForm = ProjectDetailForm.builder()
                .projectId(projectDetail.getProjectId())
                .projectCode(projectDetail.getProjectCode())
                .projectName(projectDetail.getProjectName())
                .clientId(projectDetail.getClientId())
                .clientName(projectDetail.getClientName())
                .orderDate(projectDetail.getOrderDate().toLocalDate())
                .deliveryDate(
                        projectDetail.getDeliveryDate() == null ? null : projectDetail.getDeliveryDate().toLocalDate())
                .description(projectDetail.getDescription())
                .build();

        model.addAttribute("projectDetailForm", projectDetailForm);
        model.addAttribute("projectDetail", projectDetailForm);

        return ScreenName.PROJECT_DETAIL.getViewName();
    }

}
