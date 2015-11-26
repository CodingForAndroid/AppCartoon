package com.jorge.appcartoon.http.protocol;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.jorge.appcartoon.bean.CartInstruction;
import com.jorge.appcartoon.http.ApiUtil;
import com.jorge.appcartoon.http.HttpUtil;
import com.jorge.appcartoon.util.LogUtils;

/**
 * 动漫介绍页，请求数据
 * @author：Jorge on 2015/11/16 12:21
 */
public class CartInsProtocol extends BaseProtocol<CartInstruction>{
    boolean waitflag=true;
    String  result ="";
    private String  url="";
    public CartInsProtocol(String url){
        LogUtils.e(url);
        this.url=url;
    }
    @Override
    protected String loadFromNet(int index) {
        StringRequest cartInsRequest=new StringRequest(url, new Response.SuccessListener<String>() {
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

        HttpUtil.getRequestQueue().add(cartInsRequest);

//        if(mListener!=null) mListener.onLoading();
        while(waitflag){

        }
        return result;
    }

    @Override
    protected String getKey() {
        return url;
    }

    @Override
    protected CartInstruction parseData(String json) {
        Gson gson = new Gson();
        CartInstruction cartIns=  gson.fromJson(json, CartInstruction.class);
//        if(mListener!=null) mListener.hasFinishLoading();
        return cartIns;
    }


}
