package com.ethan.common.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @version 1.0
 * @date 05/03/2019
 */
@Entity
@Table(name = "fc_device")
@Data
@ToString
public class FcDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "device_name", length = 50)
    private String deviceName;

    @Column(name = "device_description", length = 500)
    private String deviceDescription;

    @Column(name = "device_nickname", length = 50)
    private String deviceNickname;

    @Column(name = "period_enable")
    private Boolean periodEnable = false;

    @Column(name = "period_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date periodStart;

    @Column(name = "period_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date periodEnd;

    @Column(name = "period_repeatable")
    private Boolean repeatable;
}
