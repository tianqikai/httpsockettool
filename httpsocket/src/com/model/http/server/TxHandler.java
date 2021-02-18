package com.model.http.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URI;

/**
 * TxHandler :
 *
 * @description:
 * @author: tianqikai
 * @date : 2021/1/26 0026
 */
public class TxHandler implements HttpHandler {
    private static Logger log= LogManager.getLogger(TxHandler.class);
    private int byteSize=1024;
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String method=httpExchange.getRequestMethod();
        URI url=httpExchange.getRequestURI();
        InputStream requestInputStream=httpExchange.getRequestBody();
        log.info("请求的URL：[{}]"+url);
        String request=getMsg(requestInputStream);

    }
    /**
     * @methodname: getMsg
     * @description: 获取请求消息
     * @author: tianqikai
     * @time: 2021/1/27 0027 14:14
     */
    private String getMsg(InputStream inputStream){
        StringBuilder result= new StringBuilder();
        try {
            char[] info = new char[byteSize];
            int len =0;
            Reader in=new InputStreamReader(inputStream,"UTF-8");
            while((len=in.read(info,0,info.length))>0){
                result.append(info, 0, len );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            return  result.toString();
        }
    }
}
