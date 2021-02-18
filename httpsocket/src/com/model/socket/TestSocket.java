package com.model.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: httpsocket
 * @description:
 * @author: tianqikai
 * @create: 2021-02-07 21:20
 **/
public class TestSocket {
    public static void main(String[] args)  {
        try{
            int len=0;
            byte[] bytes=new byte[1024];
            StringBuilder sb=new StringBuilder();
            ServerSocket serverSocket=new ServerSocket(8899);
            System.out.println("端口已启动！"+8899);
            Socket socket= serverSocket.accept();
            System.out.println("客户端连接成功："+socket.getInetAddress().getHostAddress());
            DataInputStream dataInputStream=new DataInputStream(socket.getInputStream()) ;
            len=dataInputStream.read(bytes,0,bytes.length);
            sb.append(new String(bytes));
//            while((len=dataInputStream.read(bytes,0,bytes.length))!=-1){
//                sb.append(new String(bytes));
//            }
            System.out.println("接受信息："+sb.toString());
            DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());
            dataOutputStream.write("服务端返回响应\n".getBytes());
            dataOutputStream.flush();
            if(dataInputStream!=null){
                dataInputStream.close();
            }
            if(dataOutputStream!=null){
                dataOutputStream.close();
            }
            socket.close();
            serverSocket.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
