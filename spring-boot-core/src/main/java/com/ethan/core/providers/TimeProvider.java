package com.ethan.core.providers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @version 1.0
 * @date 06/03/2019
 */
public class TimeProvider {

    private TimeProvider() {
    }
    public static Date now() {
        return new Date();
    }
    public static String getCurrentTimeStamp() {
        SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MM-dd HH24:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return dateFormatLocal.format(timestamp);
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static String digitalGenerator6() {
        Random random = new Random();
        int x = random.nextInt(899999);
        return String.valueOf(x+100000);
    }

    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactory.INSTANCE.getPasswordEncoder();
    }
}

enum PasswordEncoderFactory {
    INSTANCE;

    private PasswordEncoder passwordEncoder;

    PasswordEncoderFactory() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
}
