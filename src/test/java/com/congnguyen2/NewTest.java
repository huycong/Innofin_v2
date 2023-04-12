package com.congnguyen2;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
public class NewTest {

    //data provider annotation
    @Test(dataProvider="Title")
    void dataProvPayLoad(String title, String body, String abc) {
        System.out.println("congnguyen22 :" + title + ":" + body + ":" + abc);
        //base URL
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        //input details
        given().header("Content-type", "application/json")

                //adding post method with parameterization from data provider
                .body(Payload.postBody(title, body)).
                when().post("/posts").then()

                //verify status code as 201
                .assertThat().statusCode(201);
    }

    //data provider method
    @DataProvider(name="Title")
    public Object[][] getData() {
        //multi-dimension element collection with two data sets
        return new Object[][]
                {{"Cypress","JavaScript"},{"Selenium","Python"}, {"Selenium2","Python2"}};
    }
}
