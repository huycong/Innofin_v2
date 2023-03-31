package com.innofin.debug;

public class Debug {
    public static void die(Object obj){
        String s = obj.toString();
        System.out.println(s);
        System.exit(0);
    }
}
