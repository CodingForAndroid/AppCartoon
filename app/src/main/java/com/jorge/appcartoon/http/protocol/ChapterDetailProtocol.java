package com.jorge.appcartoon.http.protocol;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.jorge.appcartoon.bean.CartInstruction;
import com.jorge.appcartoon.bean.ChapterDetail;
import com.jorge.appcartoon.http.ApiUtil;
import com.jorge.appcartoon.http.HttpUtil;
import com.jorge.appcartoon.util.LogUtils;

/**
 * @author：Jorge on 2015/11/20 15:27
 */
public class ChapterDetailProtocol  extends BaseProtocol<ChapterDetail>{
    boolean waitflag=true;
    String  result ="";
    private String  url="";
    public ChapterDetailProtocol(String url){
        LogUtils.e(url);
        this.url=url;
    }

    @Override
    protected String loadFromNet(int index) {
        StringRequest chapterDetailRequest=new StringRequest(url, new Response.SuccessListener<String>() {
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
        HttpUtil.getRequestQueue().add(chapterDetailRequest);
        while(waitflag){

        }
        return result;
    }

    @Override
    protected String getKey() {
        return "ChapterDetailProtocol";
    }

    @Override
    protected ChapterDetail parseData(String json) {
        Gson gson = new Gson();
        return   gson.fromJson(json,ChapterDetail.class);
    }

}
