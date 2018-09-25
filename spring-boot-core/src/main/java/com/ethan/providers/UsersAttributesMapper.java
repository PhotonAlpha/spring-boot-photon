/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.providers;


import com.ethan.model.LdapUsers;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;

import javax.naming.NamingException;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-23 16:37
 **/
public class UsersAttributesMapper implements ContextMapper {
    @Override
    public Object mapFromContext(Object ctx) throws NamingException {
        DirContextAdapter context = (DirContextAdapter)ctx;
        return new LdapUsers()
                .setDn(context.getNameInNamespace())
                .setUsername(context.getStringAttribute("uid"))
                .setEmail(context.getStringAttribute("mail"))
                .setPassword(mapPassword(context.getObjectAttribute("userPassword")))
                .setCommonname(context.getStringAttribute("cn"))
                .setSurname(context.getStringAttribute("sn"));

    }

    /**
     * Extension point to allow customized creation of the user's password from the
     * attribute stored in the directory.
     *
     * @param passwordValue the value of the password attribute
     * @return a String representation of the password.
     */
    protected String mapPassword(Object passwordValue) {

        if (!(passwordValue instanceof String)) {
            // Assume it's binary
            passwordValue = new String((byte[]) passwordValue);
        }

        return (String) passwordValue;

    }
}
