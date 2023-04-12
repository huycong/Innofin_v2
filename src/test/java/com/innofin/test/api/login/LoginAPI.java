package com.innofin.test.api.login;

import com.innofin.api.request.Request;
import com.innofin.api.validator.JsonMinh;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class LoginAPI {
    public String base_uri = "http://143.198.202.175:8080";
    public String token;
    public String username = "ceo";
    public String password = "ceo@123";


    @Test
    public void requestLimit(){
        String url = "http://143.198.202.175:8080/b/v1/request-limit/44";
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjZW8iLCJyb2xlcyI6WyJJTkZfQ0VPIl0sImV4cCI6MTY4MDkzNzYwOCwidXNlcklkIjoiMTIzNCIsImlhdCI6MTY4MDA3MzYwOH0.vGITF_AYydH9l1Ks3FyZqTRYyrIR0Wid5w_4lgXWLjfkR_1IjIC0hWFULXFI6_0WYiSXNigU60OdBhAekfoICA");
        //body.put("Password", password);
        Response res = Request.send(url, "get", body);
        Assert.assertEquals(200,res.getStatusCode());
        JSONObject json_res = new JSONObject(res.asString());
        String[] keys = {"approvals"};
        System.out.println(JsonMinh.jsonHaveKey(json_res, "level"));
        JsonPath j = new JsonPath(res.asString());
        String p = j.getString("files.fileName[1]");
        System.out.println("File name is : " + p);
    }

    @Test
    public void jsonValue() {

        //base URI with Rest Assured class
        RestAssured.baseURI = "https://run.mocky.io/v3";

        //obtain Response from GET request
        Response res = given()
                .when()
                .get("/8ec8f4f7-8e68-4f4b-ad18-4f0940d40bb7");

        //convert JSON to string
        JsonPath j = new JsonPath(res.asString());

        //get a field value from nested JSON
        String p = j.getString("Items.Price");
        System.out.println("Price is: " + p);
    }

}
