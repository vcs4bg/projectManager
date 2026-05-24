package com.tksystem.project_manager.app.controller.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tksystem.project_manager.app.constants.ScreenName;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/project")
public class ProjectListController {

    @GetMapping("/")
    public String showList() {
        return ScreenName.PROJECT_LIST.getViewName();
    }

}
