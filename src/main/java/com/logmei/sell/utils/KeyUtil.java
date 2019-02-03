package com.logmei.sell.utils;

import java.util.Random;

public class KeyUtil {
    public static String getUniqueKey(){
        Random random = new Random();
        return System.currentTimeMillis()+String.valueOf(random.nextInt(900000)+100000);
    }

}
