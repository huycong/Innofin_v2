package com.innofin.test.api.pheduyethanmuc;

import com.innofin.test.api.common.Login;
import com.innofin.api.Constants.Constants;
import com.innofin.api.request.Request;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetRequestLimit {
    public String base_url = "http://143.198.202.175:8080/b/v1/request-limit";
    public String _method = Constants.METHOD_GET;
    public String id_request_limit;
    Map<String, Object> map_params = new HashMap<>();

    @BeforeMethod
    public void init() throws IOException {
        Login a = new Login();
        String token = a.getToken();
        map_params.put("Authorization", "Bearer "+token);
    }

    @Test
    public void validateResponseCode(){
        Response res = Request.send(base_url, _method, map_params);
        Assert.assertTrue(res.getStatusCode()==200);
    }

    @Test
    public void validateResponseValue(){
        Response res = Request.send(base_url, _method, map_params);
        Assert.assertTrue(res.getStatusCode()==200);
        String data = res.jsonPath().getString("data");
        id_request_limit = res.jsonPath().getString("data.id[0]");
        Assert.assertTrue(data.contains("74"));
    }

    @Test
    public void validateResponseCodeGetDetail(){
        Response res = Request.send(base_url, _method, map_params);
        String data = res.jsonPath().getString("data");
        id_request_limit = res.jsonPath().getString("data.id[0]");
        base_url = base_url + "/" + id_request_limit;
        res = Request.send(base_url, _method, map_params);
        data = res.asString();
        Assert.assertTrue(res.getStatusCode()==200);
        Assert.assertTrue(data.contains("Nguyễn  Phạm Minh Hoàng"));
    }

}
