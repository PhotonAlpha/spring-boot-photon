/**
 * Copyright the original author or authors.
 *
 * @author Cao Qiang
 */
package com.ethan.common.model;

import com.ethan.common.constant.UserActionEnum;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-29 22:54
 **/
@Entity
@Table(name = "fc_user_audit")
@Data
@Accessors(chain = true)
@ToString
public class FcUserAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", unique = true, length = 50)
    private Long userId;

    @Column(name = "action_type")
    @Enumerated(EnumType.STRING)
    private UserActionEnum actionType;

    @Column(name = "login_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginTime;

    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;


}
