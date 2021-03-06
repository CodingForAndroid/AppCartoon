package com.jorge.appcartoon.http.protocol;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.jorge.appcartoon.bean.CartRecClassify;
import com.jorge.appcartoon.http.ApiUtil;
import com.jorge.appcartoon.http.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * 请求实例
 * @author：Jorge on 2015/11/10 18:26
 */
public class DemoProtocol extends BaseProtocol<List<CartRecClassify>> {
    boolean waitflag=true;
    String  result ="";
    @Override
    protected String loadFromNet(int index) {
        StringRequest stringRequest=new StringRequest(ApiUtil.CART_RECOMMEND_URL, new Response.SuccessListener<String>() {
            @Override
            public void onResponse(String s) {
                result =s;
                waitflag=false;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                result =null;
                waitflag=false;
            }
        });
        HttpUtil.getRequestQueue().add(stringRequest);
        while(waitflag){

        }
        return result;
    }

    @Override
    protected String getKey() {
        return "DemoProtocol";
    }

    @Override
    protected List<CartRecClassify> parseData(String json) {
        Gson gson = new Gson();
        ArrayList<CartRecClassify> resultList = new ArrayList<CartRecClassify>();
        try {
            JSONArray array=new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                CartRecClassify item=    gson.fromJson(array.optString(i), CartRecClassify.class);
                resultList.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}