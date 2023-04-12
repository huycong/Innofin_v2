package com.innofin.test.api.common;

import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import com.github.dzieciou.testing.curl.Options;
import com.github.dzieciou.testing.curl.Platform;
import com.innofin.api.request.Request;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.innofin.api.Constants.Constants.*;
import static io.restassured.RestAssured.given;

public class Login {
    public String base_uri = "http://143.198.202.175:8080/p/v1/account/login";
    public String username = "ceo";
    public String password = "ceo@123";

    public String getToken() throws IOException {
        Options options = Options
                .builder()
                .targetPlatform(Platform.UNIX)
                .useLongForm()
                .build();
        RestAssuredConfig config = CurlRestAssuredConfigFactory.createConfig(options);

        String res = RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("username", username)
                .formParam("password", password)
                .when()
                .post(base_uri)
                .asString();
        System.out.println("Local admin token: " + res);
        return res;
    }


    public String getToken2() throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);
        Response res = Request.send(base_uri, "post", CONTENT_TYPE_DATA_FORM_ENCODE, "", data);
        String result = res.asString();
        System.out.println("Local admin token: " + result);
       return result;
    }
}
