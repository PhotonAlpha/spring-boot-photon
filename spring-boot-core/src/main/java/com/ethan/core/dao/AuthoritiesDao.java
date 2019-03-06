/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.core.dao;

import com.ethan.core.model.AuthorityName;
import com.ethan.core.model.Authoritys;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-17 15:40
 **/
public interface AuthoritiesDao extends JpaRepository<Authoritys, Long> {
    Authoritys findByName(AuthorityName role);

}
