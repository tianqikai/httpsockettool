package com.model.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

/**
 * @className:MySocketClient
 * @description:
 * @author: tianqikai
 * @time: 2021/1/28 0028 9:38
 */
public class MySocketClient {
    private static Logger log= LogManager.getLogger(SocketClientUtils.class);
    private Socket socket;
    private int port ;
    private String hostIp ;

    /**
     * @description:
     * @author: tianqikai
     * @time: 2021/2/2 0002 11:27
     */
    public MySocketClient(String hostIp,int port){
        super();
        this.hostIp = hostIp;
        this.port=port;
        try {
            this.socket = new Socket(hostIp,port);
        } catch (Exception e) {
            log.error("创建MySocketClient构造函数失败！");
        }
    }

    /**
    * @methodname: sendMsgToServer
    * @description:给服务器发送消息
    * @param msg
    * @author: tianqikai
    * @time: 2021/1/27 0027 15:55
    */
    public void sendMsgToServer(String msg) throws IOException {
        OutputStream out=null;
        try {
            out = this.socket.getOutputStream();
            out.write(msg.getBytes());
            out.flush();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }finally {
            out.close();
        }
    }

    /**
     * @methodname: sendMsgToServer
     * @description:从服务器接收消息
     * @author: tianqikai
     * @time: 2021/1/27 0027 15:55
     */
    public void recMsgFromServer() {
        byte[] b = null;
        StringBuilder text = null;
        String response;
        response= text.toString();
        while (true) {
            try {
                InputStream in = this.socket.getInputStream();
                b = new byte[1024];
                int len = 0;

                while ((len = in.read(b)) != -1) {
//                    String strText = new String(b,0,len);
                    text.append(new String(b, 0, len));
//                    writeDataToTxt(strText);
                }

            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
//        response= text.toString();
//        if (text != null) {
//            response = text.toString();
//        }
    }

    public void writeDataToTxt(String strText){
       String fileName = "E:\\Desktop\\Temp\\01.txt";
       File f = new File(fileName);
       FileOutputStream out = null;
       try {
          out = new FileOutputStream(f,f.exists());
          strText = "\n" + strText;
          out.write(strText.getBytes());
          out.close();
       } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
            try {
                out.close();
            } catch (IOException e1) {
                // TODO 自动生成的 catch 块
                e1.printStackTrace();
            }
       }
    }

    public static void main(String[] args) {
        MySocketClient mySocketClient=new MySocketClient("127.0.0.1", 8800);
        try {
            mySocketClient.sendMsgToServer("mySocketClienttest哈哈哈哈！");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mySocketClient.recMsgFromServer();
    }
}