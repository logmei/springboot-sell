package com.logmei.sell.aaa;

public class Singleton {
    private static int x=0;
    private static int y;
    //在类中定义好实例
    private static final Singleton instance = new Singleton();

    static {
        System.out.println("-------------------------------------------");
    }

    private Singleton(){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        x++;
        y++;
    }

    public static Singleton getInstance(){
        System.out.println("+++++++++++++++++++++++++++");
        return instance;
    }

    public static void main(String[] args){
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.x);
        System.out.println(singleton.y);
    }
}
