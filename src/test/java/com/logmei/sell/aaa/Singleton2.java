package com.logmei.sell.aaa;

public class Singleton2 {
    private static Singleton2 singleton;

    public Singleton2() {
    }

    public static final Singleton2 getInstance(){
        if(singleton == null) {
            singleton = new Singleton2();
            return singleton;
        }else {
            return singleton;
        }
    }
}
