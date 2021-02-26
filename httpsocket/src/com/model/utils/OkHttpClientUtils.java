package com.model.utils;

import java.io.IOException;
import java.util.HashMap;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
    public static String okPostByMap(String url, HashMap<String, String> resquestMap) throws IOException {
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
            return response.body().string();
        }else{
            return null;
        }

    }
    /**
     * @Method： okPostJsonRequest
     * @Description：json保文提交请求
     * @param url
     * @param object
     * @Exception： IOException
     * @Date： 2021/2/25 22:28
     * @Author： tianqikai
     * @Version  1.0
     */
    public static <T> String okPostJsonStr(String url, T object) throws IOException {
        String jsonStr="";
        OkHttpClient client = new OkHttpClient();
        Class objectClass=object.getClass();
        String classType=objectClass.getName();
        if(classType.equalsIgnoreCase("java.lang.String")){
            jsonStr= (String) object;
        }else if(classType.equalsIgnoreCase("java.util.HashMap")){
            JSONArray jsonArray= new JSONArray();
            jsonArray.add((HashMap)object);
            jsonStr=jsonArray.toJSONString();
        }else{
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(object);
            jsonStr=jsonObject.toJSONString();
        }
        RequestBody requestBody = RequestBody.create(jsonStr,JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){
            log.info("postJsonRequest返回信息："+response.toString());
            return response.body().string();
        }else{
            return null;
        }
    }
    public static void main(String[] args) throws IOException {
        HashMap<String,String> resquestMap=new HashMap<>();
        resquestMap.put("name","tqk");
        resquestMap.put("age","30");
        String resp=okPostByMap("http://127.0.0.1:8800/#/",resquestMap);
        System.out.println("resp1:"+resp);
        resp=okPostJsonStr("http://127.0.0.1:8800/#/","1111111111111");
        System.out.println("resp2:"+resp);
    }
}
