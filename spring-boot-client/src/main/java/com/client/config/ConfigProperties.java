/**
 * describe: copy right by @author
 *
 * @author xxx
 * @date 2019/05/16
 */
package com.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "bean")
@EnableSslPrefix
public class ConfigProperties {
    private String name;
    private Integer age;
    private String gender;

}
