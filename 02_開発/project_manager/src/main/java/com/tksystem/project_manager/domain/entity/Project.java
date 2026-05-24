package com.tksystem.project_manager.domain.entity;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {

    /** プロジェクトID */
    Long projectId;

    /** プロジェクトコード */
    String projectCode;

    /** プロジェクト名 */
    String projectName;

    /** 顧客ID */
    long clientId;

    /** 受注日 */
    LocalDateTime orderDate;

    /** 納品日 */
    LocalDateTime deliveryDate;

    /** 概要 */
    String description;

    /** 登録日時 */
    LocalDateTime createDate;

    /** 登録ユーザー */
    long createdBy;

    /** 更新日 */
    LocalDateTime updateDate;

    /** 更新ユーザー */
    long updatedBy;

}
