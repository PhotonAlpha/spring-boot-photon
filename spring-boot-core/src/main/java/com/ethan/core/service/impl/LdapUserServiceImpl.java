/**
 * Copyright the original author or authors.
 *
 * @author Ethen Cao
 */
package com.ethan.core.service.impl;

import com.ethan.core.constant.ServiceConstant;
import com.ethan.core.model.LdapUsers;
import com.ethan.core.security.jwt.JwtUserFactory;
import com.ethan.core.service.UserService;
import com.ethan.core.providers.UsersAttributesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import java.text.MessageFormat;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-09-23 17:28
 **/
@Service(ServiceConstant.LDAP_SERVICE)
public class LdapUserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private LdapTemplate ldapTemplate;

    @Override
    public List<LdapUsers> getUsers() {
        List<LdapUsers> users = ldapTemplate.search("", "objectClass=person", new UsersAttributesMapper());
        System.out.println(users);

        String[] args = {"ethan"};
        MessageFormat fmt = new MessageFormat("uid={0},ou=people");
        LdapUsers ldapuser = (LdapUsers)ldapTemplate.lookup(fmt.format(args), new UsersAttributesMapper());
        LdapQuery roleQuery = query().base("ou=groups")
                .where("objectClass").is("groupOfUniqueNames")
                .and("uniqueMember").is(ldapuser.getDn());

        return users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] args = {username};
        MessageFormat fmt = new MessageFormat("uid={0},ou=people");
        LdapUsers ldapuser = (LdapUsers)ldapTemplate.lookup(fmt.format(args), new UsersAttributesMapper());

        LdapQuery roleQuery = query().base("ou=groups")
                .where("objectClass").is("groupOfUniqueNames")
                .and("uniqueMember").is(ldapuser.getDn());
        List<String> roles = ldapTemplate.search(roleQuery, (AttributesMapper<String>) attrs -> (String) attrs.get("cn").get());
        try {
            System.out.println("--------------");
            ldapTemplate.getContextSource().getReadOnlyContext().getEnvironment()
                    .entrySet().stream()
                    .forEach(key -> System.out.println(key.getKey()+":"+key.getValue()));
        } catch (NamingException e) {
            e.printStackTrace();
        }
        if (ldapuser != null) {
            if (roles != null && !roles.isEmpty()) {
                ldapuser.setAuthorities(roles);
            }
            return JwtUserFactory.create(ldapuser);
        } else {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
    }
}
