package com.ethan.common.model;

import com.ethan.common.constant.AppTypeEnum;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @version 1.0
 * @date 05/03/2019
 */
@Entity
@Table(name = "fc_app")
@Data
@ToString
public class FcApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "app_name", length = 50)
    private String appName;

    @Column(name = "app_description", length = 500)
    private String appDescription;

    @Column(name = "app_recommend")
    private Boolean appRecommend;

    @Column(name = "app_installed")
    private Boolean appInstalled;

    @Column(name = "app_type", length = 10)
    @Enumerated(EnumType.STRING)
    private AppTypeEnum appType;

}
