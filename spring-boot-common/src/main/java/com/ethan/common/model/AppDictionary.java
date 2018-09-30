/**
 * Copyright the original author or authors.
 *
 * @author Cao Qiang
 */
package com.ethan.common.model;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-29 22:54
 **/
@Entity
@Table(name = "dictionary")
@Data
@Accessors(chain = true)
@ToString
public class AppDictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "key", unique = true, length = 50)
    private String key;
    @Column(name = "value", length = 255)
    private String value;
    @Column(name = "group_name", length = 50)
    private String groupName;
}
