package com.tksystem.project_manager.domain.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProjectDetail {

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
	private LocalDateTime orderDate;

	/** 納品日 */
	private LocalDateTime deliveryDate;

	/** 概要 */
	private String description;

}
