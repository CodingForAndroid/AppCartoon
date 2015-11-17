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
    public CartInsProtocol(int comic_id){
        url=    ApiUtil.CART_INS.replace(ApiUtil.COMIC_ID,String.valueOf(comic_id) );
        LogUtils.e(url);
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

       if(mListener!=null) mListener.onLoading();
        while(waitflag){

        }
        return result;
    }

    @Override
    protected String getKey() {
        return "CartInsProtocol";
    }

    @Override
    protected CartInstruction parseFromJson(String json) {
        LogUtils.e(json.toString());
        Gson gson = new Gson();
        CartInstruction cartIns=  gson.fromJson(json, CartInstruction.class);
        LogUtils.e(cartIns.toString());
        if(mListener!=null) mListener.hasFinishLoading();
        return cartIns;
    }

    private CompleteListener mListener;
    public void setOnCompleteListener(CompleteListener listener){
        mListener=listener;
    }
    public interface  CompleteListener{
       void hasFinishLoading();
        void onLoading();
    }

}
