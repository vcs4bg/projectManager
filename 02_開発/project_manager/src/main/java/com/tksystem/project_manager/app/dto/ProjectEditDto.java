package com.tksystem.project_manager.app.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectEditDto {

    /** プロジェクトID */
    private Long projectId;

    /** プロジェクトコード */
    private String projectCode;

    /** プロジェクト名 */
    private String projectName;

    /** 顧客ID */
    private Long clientId;

    /** 受注日 */
    private LocalDateTime orderDate;

    /** 納品日 */
    private LocalDateTime deliveryDate;

    /** 概要 */
    private String description;

}
