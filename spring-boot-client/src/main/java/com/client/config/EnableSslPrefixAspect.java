/**
 * describe: copy right by @author
 *
 * @author xxx
 * @date 2019/05/16
 */
package com.client.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EnableSslPrefixAspect {

    @Pointcut("@within(enableSslPrefix) || @annotation(enableSslPrefix)")
    public void enableSsl(EnableSslPrefix enableSslPrefix) {
    }

    @Around("enableSsl(enableSslPrefix)")
    public Object execute(ProceedingJoinPoint point, EnableSslPrefix enableSslPrefix) throws Throwable {
        System.out.println("execute::" + enableSslPrefix.value());
        Object result = point.proceed();
        if (result instanceof String) {
            boolean isGetter = point.getSignature().getName().startsWith("get");
            if (isGetter && enableSslPrefix.value()) {
                return "http" + result;
            } else if (isGetter && !enableSslPrefix.value()) {
                return "https" + result;
            }
        }
        return result;
    }
}
