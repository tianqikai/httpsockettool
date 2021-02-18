package com.model.http.server;

import com.model.conf.ConfigurationManager;
import com.sun.net.httpserver.HttpServer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.*;

/**
 * HttpServer
 * @description: 启动http服务
 * @author: tianqikai
 * @date : 2021/1/25 0025
 */
public class TxHttpServer {
    private static String httpServerPort= ConfigurationManager.getConfigValue("httpServerPort");
    private static String httpServerContext= ConfigurationManager.getConfigValue("txHttpContext");
    public static Logger log= LogManager.getLogger(TxHttpServer.class);
    /**
     * @methodname: startTxHttpServer
     * @description: 启动http服务方法
     * @author: tianqikai
     * @time: 2021/1/26 0026 15:28
     */
    public  static  void  startTxHttpServer(){
        HttpServer httpserver;
        try {
            // 绑定地址，端口，请求队列； 队列设置成0则使用默认值：50
            httpserver=HttpServer.create(new InetSocketAddress(Integer.parseInt(httpServerPort)), 0);
            //监听端口8080,
            httpserver.createContext(httpServerContext, new TxHandler());
            //ExecutorService executorService= Executors.newFixedThreadPool(10);
            //https://www.cnblogs.com/dafanjoy/p/9729358.html 线程池资料
            //https://blog.csdn.net/qq_36381855/article/details/79942555
            ExecutorService executorService= new ThreadPoolExecutor(
                    1,
                    5,
                    1000,
                    TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<Runnable>(10),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.AbortPolicy());
            httpserver.setExecutor(executorService);
            httpserver.start();
            log.info("启动http服务端口：[{}]"+httpServerPort);
            log.info("启动http服务上下文根节点：[{}]"+httpServerContext);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
