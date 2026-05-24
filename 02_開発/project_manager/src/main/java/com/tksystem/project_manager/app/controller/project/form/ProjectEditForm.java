package com.tksystem.project_manager.app.controller.project.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEditForm {

    /** プロジェクトID */
    private Long projectId;

    /** プロジェクトコード */
    @NotBlank
    @Size(max = 10)
    private String projectCode;

    /** プロジェクト名 */
    @NotBlank
    @Size(max = 200)
    private String projectName;

    /** 顧客ID */
    @NotNull
    @Min(0)
    private Long clientId;

    /** 受注日 */
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate orderDate;

    /** 納品日 */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate deliveryDate;

    /** 概要 */
    @Size(max = 2000)
    private String description;

}
