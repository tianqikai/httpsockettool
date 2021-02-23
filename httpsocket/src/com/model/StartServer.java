package com.model;

import com.model.http.server.TxHttpServer;
import com.model.socket.TxSocketServer;

/**
 * StartServer :
 * @description: 启动服务主程序
 * @author: tianqikai
 * @date : 2021/1/28 0028
 */
public class StartServer {
    public static void main(String[] args) {

//        TxSocketServer.startReceiveServer();

        TxHttpServer.startTxHttpServer();
    }
}
