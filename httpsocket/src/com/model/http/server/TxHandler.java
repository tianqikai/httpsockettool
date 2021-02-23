package com.model.http.server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
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
        //请求地址
        InetSocketAddress inetSocketAddress=httpExchange.getRemoteAddress();
        log.info("请求ip地址："+inetSocketAddress);
        log.info("请求host："+inetSocketAddress.getHostName());
        log.info("请求port:"+inetSocketAddress.getPort());
        String requestMethod=httpExchange.getRequestMethod();
        log.info("请求方式:"+requestMethod);
        URI url=httpExchange.getRequestURI();
        log.info("http请求的URL："+url);
        InputStream requestInputStream=httpExchange.getRequestBody();
        String request=getMsg(requestInputStream);
        log.info("http请求的信息："+request);
        //返回报文
        String resmsg="恭喜你成功了!";
        httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,resmsg.getBytes("UTF-8").length );
        OutputStream outputStream=httpExchange.getResponseBody();
        outputStream.write(resmsg.getBytes("UTF-8"));
        outputStream.close();
        log.info("http通讯结束!");
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
