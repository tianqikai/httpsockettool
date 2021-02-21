package com.model.socket2;

import com.model.conf.ConfigurationManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * SocketServer :
 * @description: socket服务端
 * @author: tianqikai
 * @date : 2021/1/27 0027
 */
public class SocketServer {
    private static int serverPort= ConfigurationManager.getConfigValueAsInt("Socket.Server.Port");
    private static int inServerPort= ConfigurationManager.getConfigValueAsInt("in.socket.port");
    private static String inServerIP= ConfigurationManager.getConfigValue("in.socket.ip");

    private static Logger log= LogManager.getLogger(SocketServer.class);
    public static void main(String[] args) {
        try {
            //创建绑定到特定端口的服务器Socket。
            ServerSocket serverSocket = new ServerSocket(inServerPort);
            //需要接收的客户端Socket
            Socket socket = null;
            //记录客户端数量
            int count = 0;
            System.out.println("服务器启动"+inServerPort);
            log.info("服务器启动"+inServerPort);
            //定义一个死循环，不停的接收客户端连接
            while (true) {
                //侦听并接受到此套接字的连接
                socket = serverSocket.accept();
                //获取客户端的连接
                InetAddress inetAddress=socket.getInetAddress();
                //自己创建的线程类
                ServerThread thread=new ServerThread(socket,inetAddress);
                //启动线程
                thread.start();
                //如果正确建立连接
                count++;
                //打印客户端数量
                System.out.println("客户端数量：" + count);
            }
        } catch (IOException e) {
            if(e.getMessage().startsWith("socket closed")){
                log.warn("the socket is closed",e);
            }else{
                log.error("the socket is error",e);
            }
        }

    }
}