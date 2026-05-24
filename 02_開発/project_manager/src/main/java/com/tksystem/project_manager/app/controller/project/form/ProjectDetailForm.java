package com.tksystem.project_manager.app.controller.project.form;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectDetailForm {

    /** プロジェクトID */
    private Long projectId;

    /** プロジェクトコード */
    private String projectCode;

    /** プロジェクト名 */
    private String projectName;

    /** 顧客ID */
    private Long clientId;

    /** 顧客名 */
    private String clientName;

    /** 受注日 */
    private LocalDate orderDate;

    /** 納品日 */
    private LocalDate deliveryDate;

    /** 概要 */
    private String description;

}
