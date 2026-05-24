package com.tksystem.project_manager.app.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tksystem.project_manager.app.constants.ScreenName;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
public class TopMenuController {

    @GetMapping("/")
    public String showTopPage() {
        return ScreenName.TOP_MENU.getViewName();
    }

}
