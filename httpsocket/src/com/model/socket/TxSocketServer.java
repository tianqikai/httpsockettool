package com.model.socket;

import com.model.conf.ConfigurationManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * TxSocketServer :
 * @description:
 * @author: tianqikai
 * @date : 2021/1/27 0027
 */
public class TxSocketServer {
    private static int serverPort= ConfigurationManager.getConfigValueAsInt("Socket.Server.Port");
    private static int inServerPort= ConfigurationManager.getConfigValueAsInt("in.socket.port");
    private static String inServerIP= ConfigurationManager.getConfigValue("in.socket.ip");

    private static Logger log= LogManager.getLogger(TxSocketServer.class);
    public static void startReceiveServer(){
        ServerSocket serverSocket=null;
        try {
            log.info("The socket server start!");
            log.info("start time:"+ new Date());
            log.info("Port Is:"+ serverPort);
            serverSocket=new ServerSocket(serverPort);
            while(true){
                Socket socket = serverSocket.accept();
                log.info("接收到客户端请求信息！");
                Thread thread=new SocketThread(socket);
                thread.start();
            }

        } catch (Exception e) {
            if(e.getMessage().startsWith("socket closed")){
                log.warn("the socket is closed",e);
            }else{
                log.error("the socket is error",e);
            }
        }
    }
    /**
     * @methodname: closeSocketServer
     * @description: 关闭socket服务
     * @param serverSocket
     * @author: tianqikai
     * @time: 2021/1/27 0027 13:54
     */
    public static void closeSocketServer(ServerSocket serverSocket){
        try {
            serverSocket.close();
        } catch (IOException e) {
            log.error("the socket close error",e);
        }
    };
}

