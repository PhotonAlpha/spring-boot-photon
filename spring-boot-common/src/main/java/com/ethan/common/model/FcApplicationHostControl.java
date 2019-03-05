package com.ethan.common.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @version 1.0
 * @date 05/03/2019
 */
@Entity
@Table(name = "fc_app_host_control")
@Data
@ToString
public class FcApplicationHostControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "app_id")
    private Long appId;

    @Column(name = "host_control_id")
    private Long hostControlId;
}
