package com.model.utils;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * @classname : HttpClientUtils
 * @description: httpclient工具类
 * @author: tianqikai
 * @date : 2021/2/22 23:19
 */
public class HttpClientUtils {
    private static Logger log= LogManager.getLogger(HttpClientUtils.class);
    /**
     * @Method： HttpPostWithJson
     * @Description： postjson报文
     * @param url
     * @Return：
     * @Date： 2021/2/22 23:33
     * @Author： tianqikai
     * @Version  1.0
     */
    public static String HttpPostWithJson(String url, String json) throws IOException {
        String returnValue = "这是默认返回值，接口调用失败";

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try{
            //第一步：创建HttpClient对象
            httpClient = HttpClients.createDefault();
            //第二步：创建httpPost对象
            HttpPost httpPost = new HttpPost(url);
            //第三步：给httpPost设置JSON格式的参数
            StringEntity  requestEntity = new StringEntity (json,"utf-8");
            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(requestEntity);
            //第四步：发送HttpPost请求，获取返回值;//调接口获取返回值时，必须用此方法
            returnValue = httpClient.execute(httpPost,responseHandler);

        } catch(Exception e) {
            log.error("httpClient error："+e);
            throw e;
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                log.error("httpClient error："+e);
                throw e;
            }
        }
        //第五步：处理返回值
        log.info("HttpClient返回信息："+new String(returnValue.getBytes(),"utf-8"));
        return returnValue;
    }

    public static void main(String[] args) throws IOException {
        HttpPostWithJson("http://127.0.0.1:8800/#/","{ name:\"zhou\",age:30}");
    }

}
