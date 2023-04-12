package com.innofin.api.Constants;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final String FOLDER_ROOT= System.getProperty("user.dir")+"/";

    public static final String METHOD_GET = "get";
    public static final String METHOD_POST = "post";
    public static final String METHOD_PUT = "put";
    public static final String METHOD_PATCH = "patch";
    public static final String METHOD_DELETE = "delete";

    public static final String TOKEN_NULL = "";

    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_DATA_FORM = "multipart/form-data";
    public static final String CONTENT_TYPE_DATA_FORM_ENCODE = "application/x-www-form-urlencoded; charset=utf-8";

    public static final Integer STATUS_CODE_200 = 200;
    public static final Integer STATUS_CODE_201 = 201;
    public static final Integer STATUS_CODE_400 = 400;
    public static final Integer STATUS_CODE_401 = 401;
    public static final Integer STATUS_CODE_403 = 403;
    public static final Integer STATUS_CODE_405 = 405;
    public static final Integer STATUS_CODE_4004009 = 4004009;
    public static final Boolean VALIDATE_BODY_RES_YES = true;

    public static String[] METHODS = {METHOD_GET, METHOD_POST, METHOD_PUT, METHOD_PATCH, METHOD_DELETE};

    public static String[] DEFAULT_RESPONSE_KEYS = {"code", "data"};
    public static String INPUT_JSON_NULL = "";

    public static Map<String, Object> MAP_PARAMS_NULL = new HashMap<>();
}

