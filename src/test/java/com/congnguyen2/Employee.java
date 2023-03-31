package com.congnguyen2;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class Employee {
    private static final String URL = "http://dummy.restapiexample.com/api/v1";
    public Response response;

    @Test
    public void verifyValidUser() {
        String id = "nguyen";
        response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json").when().get(URL + "/employee/" + id);

        SerenityRest.restAssuredThat(response -> response.statusCode(200)
                .body("data.id", equalTo(1))
                .body("data.employee_salary", equalTo(320800))
                .body("data.employee_name", equalTo("Tiger Nixon"))
                .body("data.employee_age", equalTo(61)).and()
                .body("message", equalTo("Successfully! Record has been fetched.")));

    }

    @Test
    public void verifycreateUser() {
        JSONObject data = new JSONObject();

        data.put("employee_name", "Test");
        data.put("profile_image", "test.png");
        data.put("employee_age", 30);
        data.put("employee_salary", 11111);

        response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
                .body(data.toString()).when().post(URL + "/create");

        SerenityRest.restAssuredThat(response -> response.statusCode(200)
                .body("data.employee_salary", equalTo(11111))
                .body("data.employee_name", equalTo("Shawn Test"))
                .body("data.employee_age", equalTo(30)).and()
                .body("message", equalTo("Successfully! Record has been added.")));

    }
}
