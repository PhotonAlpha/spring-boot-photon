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
@Table(name = "fc_host_control")
@Data
@ToString
public class FcHostControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "device_id")
    private Long deviceId;

    @Column(name = "host_name", length = 50)
    private String hostName;

    @Column(name = "host_address", length = 1000)
    private String hostAddress;

    @Column(name = "accessible")
    private Boolean accessible;

}
