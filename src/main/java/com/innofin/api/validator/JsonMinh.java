package com.innofin.api.validator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class JsonMinh {
    public static boolean jsonHaveKey(JSONObject x,String y) throws JSONException
    {
        JSONArray keys =  x.names();
        for(int i=0;i<keys.length();i++)
        {
            String current_key = keys.get(i).toString();

            if(current_key.equals(y))
            {
                return true;
            }

            if(x.get(current_key).getClass().getName().equals("org.json.JSONObject"))
            {
                jsonHaveKey((JSONObject) x.get(current_key),y);
            }
            else if(x.get(current_key).getClass().getName().equals("org.json.JSONArray"))
            {
                for(int j=0;j<((JSONArray) x.get(current_key)).length();j++)
                {
                    if(((JSONArray) x.get(current_key)).get(j).getClass().getName().equals("org.json.JSONObject"))
                    {
                        jsonHaveKey((JSONObject)((JSONArray) x.get(current_key)).get(j),y);
                    }
                }
            }
        }
        return false;
    }
    public static boolean jsonHaveKeys(String[] keys, JSONObject jobj){
        if (keys.length>0){
            boolean all_has = true;
            for (String k: keys) {
                if (!jobj.has(k)){
                    all_has = false;
                    break;
                }
            }
            return all_has;
        }
        else{
            return false;
        }
    }
    public static boolean jsonHaveValue(String[] values, JSONObject jobj){
        if (values.length>0){
            boolean all_has = true;
            for (String k: values) {
                for (String keyStr : jobj.keySet()) {
                    Object keyvalue = jobj.get(keyStr);
                    if (k == keyvalue.toString())
                        break;
                    all_has = false;
                }
            }
            return all_has;
        }
        else{
            return false;
        }
    }

    public static void jsonlackKeys( String[] keys, JSONObject  jobj){
        if (keys.length>0){
            String lackKeys="";
            List<String> ls = new ArrayList<>();
            for (String k: keys) {
                if (!jobj.has(k)){
//                    lackKeys += k+",";
                    ls.add(k);
                }
            }
            System.out.println("response json lack Keys: "+ String.join(",", ls));
        }
    }


    public static boolean jsonCompare(String lvl, JSONObject json_exp, JSONObject json_real){
        List<String> kstr_exp = jsonKeysToString(json_exp);
        List<String> kstr_real = jsonKeysToString(json_real);
        boolean summary = true;
        boolean isEqual = kstr_real.equals(kstr_exp);
        if (isEqual){
            System.out.println(lvl+" all keys are same ");
        }
        else{
            summary = false;
            System.out.println(lvl+" all keys are different ");
            System.out.println("json_exp keys: "+String.join(",", kstr_exp));
            System.out.println("json_real keys: "+String.join(",", kstr_real));
        }

        for (String ke: kstr_exp) {
            if (kstr_real.contains(ke)){
                boolean isSame = jsonCompare(ke, new JSONObject(json_exp.get(ke)), new JSONObject(json_real.get(ke)));
                if (!isSame){
                    summary = false;
                }
            }
            else{
                System.out.println("kstr_real does not contain key: "+ke);
            }
        }

        return summary;

    }

    public static List<String> jsonKeysToString(JSONObject json) throws JSONException {
        Iterator<?> keys = json.keys();
        List<String> kstr = new ArrayList<>();
        while( keys.hasNext() ){
            String key = (String)keys.next();
            kstr.add(key);
        }
        return kstr;
    }

}
