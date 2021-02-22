package com.model.utils;

import java.io.IOException;
import java.util.HashMap;
import com.alibaba.fastjson.JSONArray;
import okhttp3.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @classname : OkHttpClientUtils
 * @description: okhttp工具类
 * @author: tianqikai
 * @date : 2021/2/22 22:47
 */
public class OkHttpClientUtils {
    private static Logger log= LogManager.getLogger(OkHttpClientUtils.class);
    public final static int CONNECT_TIMEOUT = 60;
    public final static int READ_TIMEOUT = 100;
    public final static int WRITE_TIMEOUT = 60;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    /**
     * @Method： doOkGet
     * @Description：get方式请求
     * @param url
     * @Return：
     * @Exception：
     * @Date： 2021/2/22 22:48
     * @Author： tianqikai
     * @Version  1.0
     */
    public String doOkGet(String url) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        final Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        if(response.isSuccessful()){
            log.info("okhttp返回信息："+response.toString());
            return response.toString();
        }else{
            return null;
        }

    }
    /**
     * @Method： okFormPost
     * @Description：form表单提交
     * @param url
     * @param resquestMap
     * @Return： String
     * @Exception： IOException
     * @Date： 2021/2/22 22:59
     * @Author： tianqikai
     * @Version  1.0
     */
    public String okFormPost(String url, HashMap<String,String> resquestMap) throws IOException {
        OkHttpClient client = new OkHttpClient();

        // 提交FormData
        FormBody.Builder form = new FormBody.Builder();
        for(String key:resquestMap.keySet()){
            form.add(key,resquestMap.get(key));
        }
        Request request = new Request.Builder()
                .url(url)
                .post(form.build())
                .build();

        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){
            log.info("okhttp返回信息："+response.toString());
            return response.toString();
        }else{
            return null;
        }

    }

    /**
     * @Method： okPostJsonRequest
     * @Description： json保文提交请求
     * @param url
     * @Return：
     * @Exception：IOException
     * @Date： 2021/2/22 23:04
     * @Author： Administrator
     * @Version  1.0
     */
    public String okPostJsonRequest(String url, HashMap<String,String> resquestMap) throws IOException {
        OkHttpClient client = new OkHttpClient();
        JSONArray jsonArray= new JSONArray();
        jsonArray.add(resquestMap);

        RequestBody requestBody = RequestBody.create(jsonArray.toJSONString(),JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
//                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
//                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)//设置写的超时时间
//                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
                .build();

        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){
            log.info("postJsonRequest返回信息："+response.toString());
            return response.toString();
        }else{
            return null;
        }

    }
}
