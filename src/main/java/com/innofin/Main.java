package com.innofin;

import com.innofin.helper.FileHelper;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Hello world!");
        System.out.println(FileHelper.file_content("hello world"));
    }
}