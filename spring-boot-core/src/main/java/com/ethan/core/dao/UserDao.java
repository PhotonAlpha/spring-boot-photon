/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.core.dao;

import com.ethan.core.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-17 15:40
 **/
public interface UserDao extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}
