package com.innofin;

import com.innofin.api.helper.ExcelUtil;
import com.innofin.api.helper.Log;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Main {

    public static Response doGetRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;

        return
                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().get(endpoint).
                        then().contentType(ContentType.JSON).extract().response();
    }
    public static void main(String[] args) throws Exception {
        String exelPath = "src/test/resources/ExelData.xlsx";
        Object[][] testObjArray = ExcelUtil.getTableArray(exelPath,"Login",2);
        Response response = doGetRequest("https://jsonplaceholder.typicode.com/users");

        List<String> jsonResponse = response.jsonPath().getList("$");

        Log.info(jsonResponse.size());

        String usernames = response.jsonPath().getString("username");
        //System.out.println(usernames);

        //response = doGetRequest("https://jsonplaceholder.typicode.com/users/1");
        int a = jsonResponse.size()-1;

        Map<String, String> company = response.jsonPath().getMap("company[9]");
        //System.out.println(company.get("name"));
        Log.info(company.get("name"));
    }

}
