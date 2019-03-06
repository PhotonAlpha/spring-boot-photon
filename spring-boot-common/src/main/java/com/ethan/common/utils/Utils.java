package com.ethan.common.utils;

import java.util.Random;

/**
 * @version 1.0
 * @date 06/03/2019
 */
public class Utils {
    private Utils() {
    }

    public static String digitalGenerator6() {
        Random random = new Random();
        int x = random.nextInt(899999);
        return String.valueOf(x+100000);
    }

}
