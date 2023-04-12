package com.innofin.test.api.pheduyethanmuc;

import com.innofin.api.helper.ExcelUtil;
import com.innofin.api.helper.Log;
import com.innofin.api.helper.RandomData;
import com.innofin.api.request.Request;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.innofin.api.Constants.Constants.CONTENT_TYPE_DATA_FORM_ENCODE;

public class LoginPDHM {
    public String base_uri = "http://143.198.202.175:8080/p/v1/account/login";
    public String exelPath = "src/test/resources/ExelData.xlsx";

    @DataProvider
    public Object[][] Account() throws Exception{
        Log.info("LoginPDHM-->Account");
        Object[][] testObjArray = ExcelUtil.getTableArray(exelPath,"Login",2);
        return (testObjArray);
    }

    @Test(dataProvider = "Account")
    public void LoginTest(String sUserName,String sPassword) throws  Exception {
        Log.info("LoginPDHM-->LoginTest");
        Map<String,Object> body = new HashMap<>();
        body.put("username", sUserName);
        body.put("password",sPassword);
        Response res = Request.send(base_uri,"post", CONTENT_TYPE_DATA_FORM_ENCODE,"",body);
        Assert.assertTrue(res.getStatusCode() == 200);
    }

    @DataProvider
    public Object[][] AccountFail() throws Exception{
        Log.info("LoginPDHM-->AccountFail");
        Object[][] testObjArray = ExcelUtil.getTableArray(exelPath,"Login_Fail",2);
        return (testObjArray);
    }

    @Test(dataProvider = "AccountFail")
    public void LoginTest_Fail(String sUserName,String sPassword) throws  Exception {
        Log.info("LoginPDHM-->LoginTest_Fail");
        Map<String,Object> body = new HashMap<>();
        body.put("username", sUserName);
        body.put("password",sPassword);
        Response res = Request.send(base_uri,"post", CONTENT_TYPE_DATA_FORM_ENCODE,"",body);
        Assert.assertTrue(res.getStatusCode() == 401);
    }

    @DataProvider
    public Object[][] AccountFail2() throws Exception{
        Log.info("LoginPDHM-->AccountFail2");
        String sUserName = null;
        String sPassword = null;
        String[][] testObjArray = new String[300][2];;
        int count = 0;
        for (int i = 1; i < 2; i++) {
            for (int j = 1; j < 255; j++) {
                sUserName = RandomData.randomStringAndNumber(i);
                sPassword = RandomData.randomStringAndNumber(j);
                testObjArray[count][0] = sUserName;
                testObjArray[count][1] = sPassword;
                count++;
            }
        }
        return (testObjArray);
    }
    @Test(dataProvider = "AccountFail2")
    public void LoginTest_Fail2(String sUserName,String sPassword) throws  Exception {
        Log.info("LoginPDHM-->LoginTest_Fail2");
        Map<String,Object> body = new HashMap<>();
        body.put("username", sUserName);
        body.put("password",sPassword);
        Response res = Request.send(base_uri,"post", CONTENT_TYPE_DATA_FORM_ENCODE,"",body);
        Assert.assertTrue(res.getStatusCode() == 401);
    }
}
