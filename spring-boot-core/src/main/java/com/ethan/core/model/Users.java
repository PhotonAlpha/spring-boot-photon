/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.core.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-17 15:32
 **/
@Entity
@Table(name = "fc_users")
@Data
@Builder
public class Users {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String username;

    @Column(name = "password", length = 1000)
    // @NotNull
    @Size(min = 4, max = 1000)
    private String password;

    @Column(name = "mobile_no", length = 50, unique = true)
    @NotNull
    @Size(min = 11, max = 11)
    private String mobileNo;

    @Column(name = "mobile_code", length = 1000)
    @NotNull
    private String mobileCode;

    @Column(name = "enabled")
    @NotNull
    private Boolean enabled;

    @Column(name = "login_time")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date loginTime;
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date createTime;

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date updateTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "fc_user_authoritys",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    private List<Authoritys> authorities;

}
