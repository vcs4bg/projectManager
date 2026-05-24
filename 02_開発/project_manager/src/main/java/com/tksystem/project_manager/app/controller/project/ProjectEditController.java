package com.tksystem.project_manager.app.controller.project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tksystem.project_manager.app.constants.ScreenName;
import com.tksystem.project_manager.app.controller.project.form.ProjectEditForm;
import com.tksystem.project_manager.app.dto.ProjectEditDto;
import com.tksystem.project_manager.domain.model.ProjectDetail;
import com.tksystem.project_manager.domain.service.project.ProjectEditService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/project/edit")
/**
 * プロジェクト編集画面の表示・保存を担当するコントローラ。
 */
public class ProjectEditController {

    ProjectEditService service;

    /**
     * コンストラクタ。
     *
     * @param service プロジェクト編集サービス
     */
    public ProjectEditController(ProjectEditService service) {
        this.service = service;
    }

    /**
     * 編集画面を表示する。
     * projectId が未指定の場合は新規作成画面として扱う。
     *
     * @param projectId プロジェクトID（編集時のみ）
     * @param model     ビューモデル
     * @return 表示するビュー名
     */
    @GetMapping("/")
    public String show(@RequestParam(required = false) Long projectId, Model model) {

        ProjectEditForm projectEditForm = new ProjectEditForm();

        if (projectId != null) {

            ProjectDetail projectDetail = service.getProjectDetail(projectId);

            projectEditForm = ProjectEditForm.builder()
                    .projectId(projectDetail.getProjectId())
                    .projectCode(projectDetail.getProjectCode())
                    .projectName(projectDetail.getProjectName())
                    .clientId(projectDetail.getClientId())
                    .clientId(projectDetail.getClientId())
                    .orderDate(projectDetail.getOrderDate().toLocalDate())
                    .deliveryDate(
                            projectDetail.getDeliveryDate() == null ? null
                                    : projectDetail.getDeliveryDate().toLocalDate())
                    .description(projectDetail.getDescription())
                    .build();

        }

        model.addAttribute("projectEditForm", projectEditForm);
        model.addAttribute("pageTitle", resolvePageTitle(projectId));
        model.addAttribute("backPath", resolveBackPath(projectId));

        return ScreenName.PROJECT_EDIT.getViewName();

    }

    /**
     * 画面入力を保存する。
     * バリデーションエラー時は同画面を再表示する。
     *
     * @param form   画面入力フォーム
     * @param result バリデーション結果
     * @param model  ビューモデル
     * @return 遷移先ビュー名
     */
    @PostMapping("/")
    public String upsertProject(
            @Valid @ModelAttribute("projectEditForm") ProjectEditForm form,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("pageTitle", resolvePageTitle(form.getProjectId()));
            model.addAttribute("backPath", resolveBackPath(form.getProjectId()));
            return ScreenName.PROJECT_EDIT.getViewName();
        }

        ProjectEditDto project = ProjectEditDto.builder()
                .projectId(form.getProjectId())
                .projectCode(form.getProjectCode())
                .projectName(form.getProjectName())
                .clientId(form.getClientId())
                .orderDate(form.getOrderDate().atStartOfDay())
                .deliveryDate(form.getDeliveryDate() == null ? null : form.getDeliveryDate().atStartOfDay())
                .description(form.getDescription())
                .build();

        Long projectId = service.upsertProject(project);

        return ScreenName.PROJECT_DETAIL.getRedirectPath() + "?projectId=" + projectId;

    }

    /**
     * 画面モードに応じたページタイトルを返す。
     *
     * @param projectId プロジェクトID
     * @return ページタイトル
     */
    private String resolvePageTitle(Long projectId) {
        return projectId == null ? ScreenName.PROJECT_CREATE.getDisplayName()
                : ScreenName.PROJECT_EDIT.getDisplayName();
    }

    /**
     * 画面モードに応じた戻る先パスを返す。
     *
     * @param projectId プロジェクトID
     * @return 戻る先パス
     */
    private String resolveBackPath(Long projectId) {
        return projectId == null ? ScreenName.PROJECT_LIST.getPath() : "/project/detail/?projectId=" + projectId;
    }

}
