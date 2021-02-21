package com.model.socket;

import com.model.conf.ConfigurationManager;
import com.model.utils.SocketClientUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

/**
 * ReceiveThread :
 * @description: 处理请求信息（转发处理）
 * @author: tianqikai
 * @date : 2021/1/27 0027
 */
public class SocketThread extends Thread{
    private Logger log= Logger.getLogger(SocketThread.class);
    private static int inServerPort= ConfigurationManager.getConfigValueAsInt("in.socket.port");
    private static String inServerIP= ConfigurationManager.getConfigValue("in.socket.ip");

    private Socket socket;
    private int byteSize=1024;

    public SocketThread(Socket clientRequest) {
        this.socket = clientRequest;
    }

    @Override
    public void run(){
        DataOutputStream dos= null;
        DataInputStream in=null;
        try{
            in= new DataInputStream(socket.getInputStream());
            //读取输入流中的请求报文信息
            String requestMsg=getMsg(in);
            //关闭输入流
            socket.shutdownInput();
            log.info("接收第三方的请求信息："+requestMsg);
            dos = new DataOutputStream(socket.getOutputStream());
//            dos.write("服务端返回信息testQQQQ！！".getBytes("utf-8"));
//            dos.flush();
            sendMsg(requestMsg,dos);
        }catch(Exception e){
            log.error("the resquest is error",e);
            log.error(e);
        }finally {
            if(dos!=null){
                try {
                    dos.close();
                    if(socket!=null&&socket.isClosed()==false){
                        socket.close();
                    }
                } catch (IOException e) {
                    log.error("close the resquest object is error",e);
                }
            }
        }
    }
    /**
     * @methodname: getMsg
     * @description: 获取请求消息
     * @author: tianqikai
     * @time: 2021/1/27 0027 14:14
     */
    private String getMsg(DataInputStream in) throws IOException {
        StringBuffer result= new StringBuffer();
        try {
            byte[] info = new byte[byteSize];
            String lineStr =null;
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(in));
            while(true){
                lineStr=bufferedReader.readLine();
                System.out.println("lineStr:"+lineStr);
                if(lineStr!=null){
                    result.append(lineStr);
                }else{
                    break;
                }
            }
        }finally{
            return  result.toString();
        }
    }
    /**
     * @methodname: sendMsg
     * @description: 转发消息第三方并接收返回消息
     * @param request
     * @param dos
     * @author: tianqikai
     * @time: 2021/1/27 0027 14:20
     */
    private void sendMsg(String request,DataOutputStream dos) throws IOException {
        //请求服务器处理请求信息
        log.info("转发内部服务端请求信息："+request);
        String response=new String();
        response=SocketClientUtils.connectSocket(inServerIP,inServerPort,request);
        log.info("发送内部服务器返回信息："+response);
        byte[] bytes=response.getBytes("utf-8");
        dos.write(bytes);
        dos.flush();
    }
}
