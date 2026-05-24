package com.tksystem.project_manager.app.constants;

public enum ScreenName {

    TOP_MENU("Topメニュー", "menu/top-menu", "/"),
    PROJECT_LIST("プロジェクト一覧", "project/project-list", "/project/"),
    PROJECT_DETAIL("プロジェクト詳細", "project/project-detail", "/project/detail/"),
    PROJECT_CREATE("プロジェクト作成", "project/project-edit", "/project/edit/"),
    PROJECT_EDIT("プロジェクト編集", "project/project-edit", "/project/edit/");

    private final String displayName;
    private final String viewName;
    private final String path;

    ScreenName(String displayName, String viewName, String path) {
        this.displayName = displayName;
        this.viewName = viewName;
        this.path = path;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getViewName() {
        return viewName;
    }

    public String getPath() {
        return path;
    }

    public String getRedirectPath() {
        return "redirect:" + path;
    }

}
