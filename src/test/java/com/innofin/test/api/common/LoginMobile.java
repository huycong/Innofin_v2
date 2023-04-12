package com.innofin.test.api.common;

import com.innofin.api.request.Request;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.innofin.api.Constants.Constants.CONTENT_TYPE_JSON;
import static io.restassured.RestAssured.given;

public class LoginMobile {
    public String base_uri = "https://home-asim-dev.innofin.vn/api/account/login";
    public String jsonPath = "src/test/resources/LoginMobile.json";

    public String getCookie() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(jsonPath)));
        Response res = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .body(json)
                .post("https://home-asim-dev.innofin.vn/api/account/login");

        String result = res.header("Set-Cookie");
        System.out.println("Token :" + result);
        return result;
    }


    public String getCookie2() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(jsonPath)));
        Response res = Request.send(base_uri, "post", CONTENT_TYPE_JSON, json, null);
        String result = res.header("Set-Cookie");
        System.out.println("Res :" + res.asString());
        System.out.println("Token :" + result);
        return result;
    }
}
