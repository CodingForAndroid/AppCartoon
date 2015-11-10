package com.jorge.appcartoon.http.protocol;


import com.jorge.appcartoon.util.FileUtils;
import com.jorge.appcartoon.util.IOUtils;
import com.jorge.appcartoon.util.LogUtils;
import com.jorge.appcartoon.util.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author：Jorge on 2015/9/14 18:29
 */
public abstract class BaseProtocol<Data> {

    /** 加载协议 */
    public Data load(int index) {
        return load(index,true);
    }

    /** 加载协议 */ // 首先  Boolean localFetchFirst ： 取本地缓存  默认为true
    public Data load(int index,boolean localFetchFirst) {
//        SystemClock.sleep(1000);// 休息1秒，防止加载过快，看不到界面变化效果
        String json = null;
        // 1.从本地缓存读取数据，查看缓存时间
        if(localFetchFirst){
          json = loadFromLocal(index);
        }
        // 2.如果缓存时间过期，从网络加载
        if (StringUtils.isEmpty(json)) {
            json = loadFromNet(index);
            if (json == null) {
                // 网络出错
                return null;
            } else {
                // 3.把数据保存到本地保存到本地
                saveToLocal(json, index);
            }
        }
        return parseFromJson(json);
    }

    /** 从本地加载协议 */
    protected String loadFromLocal(int index) {
        String path = FileUtils.getCacheDir();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path + getKey() + "_" + index));
            String line = reader.readLine();// 第一行是时间
            Long time = Long.valueOf(line);
            if (time > System.currentTimeMillis()) {//如果时间未过期
                StringBuilder sb = new StringBuilder();
                String result;
                while ((result = reader.readLine()) != null) {
                    sb.append(result);
                }
                return sb.toString();
            }
        } catch (Exception e) {
            LogUtils.e(e);
        } finally {
            IOUtils.close(reader);
        }
        return null;
    }

    /** 从网络加载协议 */
    protected  abstract String loadFromNet(int index) ;

    /** 保存到本地 */
    protected void saveToLocal(String str, int index) {
        String path = FileUtils.getCacheDir();
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(path + getKey() + "_" + index ));
            long time = System.currentTimeMillis() + 1000 * 60;//先计算出过期时间，写入第一行  1minute 过期时间
            writer.write(time + "\r\n");
            writer.write(str.toCharArray());
            writer.flush();
        } catch (Exception e) {
            LogUtils.e(e);
        } finally {
            IOUtils.close(writer);
        }
    }

    /** 该协议的访问地址 */
    protected abstract String getKey();

    /** 从json中解析 */
    protected abstract Data parseFromJson(String json);

    /***
     *  联网 加载中动画。。。
     */
    public interface LoadingAnimation {
          void  onLoading();
         void  afterLoading();
    }
}

