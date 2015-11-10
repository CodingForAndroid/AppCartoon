package com.jorge.appcartoon.http.protocol;

import com.jorge.appcartoon.bean.CartRecClassify;

import java.util.List;

/**
 * 请求实例
 * @author：Jorge on 2015/11/10 18:26
 */
public class DemoProtocol extends BaseProtocol<List<CartRecClassify>> {
    @Override
    protected String loadFromNet(int index) {
        return null;
    }

    @Override
    protected String getKey() {
        return null;
    }

    @Override
    protected List<CartRecClassify> parseFromJson(String json) {
        return null;
    }
}
