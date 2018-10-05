/**
 * Copyright the original author or authors.
 *
 * @author Cao Qiang
 */
package com.ethan.core.providers;

import com.ethan.core.constant.ConfigsEnum;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector;

/**
 * @program: spring-boot
 * @description: TODO
 * @author: 411084090@qq.com
 * @creat_date: 2018-10-05 10:02
 **/
public class CustomerEncryptablePropertyDetector implements EncryptablePropertyDetector {
    @Override
    public boolean isEncrypted(String property) {
        if (property != null) {
            return (property.startsWith(ConfigsEnum.PREFFIX.value()) && property.endsWith(ConfigsEnum.SUFFIX.value()));
        }
        return false;
    }

    @Override
    public String unwrapEncryptedValue(String property) {
        return property.substring(ConfigsEnum.PREFFIX.value().length(), (property.length() - ConfigsEnum.SUFFIX.value().length()));
    }
}
