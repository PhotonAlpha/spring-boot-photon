package com.ethan.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @version 1.0
 * @date 06/03/2019
 */
@Data
public class BaseEntity {
    @JsonIgnore
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @JsonIgnore
    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @JsonIgnore
    @Column(name = "disable_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date disableDate;

    @JsonIgnore
    @Column(name = "enable")
    private Boolean enable = true;

}
