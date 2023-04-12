package com.congnguyen2;

public class Payload {
    public static String postBody(String title, String body) {
        //request payload
        String b = "{" +
                //Parameterizing title and body fields
                "\"title\": \"" +title+ " \"," + "\"body\": \"" +body+ " \"," + " \"userId\": \"34\" }";
        return b;
    }
}
