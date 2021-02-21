package com.model.utils;

import com.model.conf.ConfigurationManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
  
public class SocketClientUtils {
   private static Logger log= LogManager.getLogger(SocketClientUtils.class);
   private static int serverPort= ConfigurationManager.getConfigValueAsInt("Socket.Server.Port");
   public static String connectSocket(String ip,int port ,String sendmsg) throws IOException {
       Socket socket = null;
       String respMsg="";
       log.info("发送报文sendmsg："+sendmsg);
       try {
           socket=new Socket(ip, port);
           socket.setSoTimeout(6000);
       } catch (IOException e) {
           log.error(String.format("连接服务器[%s;%d]失败",ip,port));
       }
       DataOutputStream out=new DataOutputStream(socket.getOutputStream());
       DataInputStream in=new DataInputStream(socket.getInputStream());
       try {
           byte[] sendBytes = sendmsg.getBytes();
           out.write(sendBytes);
           //刷新缓冲
           out.flush();
           //只关闭输出流而不关闭连接
           socket.shutdownOutput();
           //获取服务端返回信息
           respMsg= getMessage(in);
       } catch (IOException e) {
           log.error("系统异常：",e);
       }finally {
           in.close();
           out.close();
           socket.close();
       }
       return respMsg;
   }

    /**
     * @description: 获取服务端信息
     * @author: tianqikai
     * @param in
     * @return
     */
   public  static String  getMessage(DataInputStream in) throws IOException {
       int length=0;
       StringBuilder resp= new StringBuilder();
       byte[] respBody = new byte[1024];
       String lineStr=null;
       BufferedReader bufferedReader=null;
       try{
           bufferedReader=new BufferedReader(new InputStreamReader(in));
           while((lineStr=bufferedReader.readLine())!=null){
               resp.append(lineStr);
           }
       }catch (Exception e){
           log.error("获取返回信息系统异常：",e);
           throw e;
       }finally {
           if(bufferedReader!=null){
               bufferedReader.close();
           }
       }
       log.info("resp:"+resp.toString());
       return resp.toString();
   }
    public static void main(String[] args) throws IOException {
       args= new String[]{"127.0.0.1","8899", "发送内容11发送内容111testeof发送内容111testeof发送内容111testeof发送内容111testeof发送内容111testeof发送内容111testeof发送内容111testeof发送内容111testeof发送内容111testeof发送内容111testeof发送内容111testeof发送内容111testeof发送内容111testeof发送内容111testeof发送内容111testeof发送内容111testeof发送内容111testeof1testeof\n"};
        System.out.println(args[0]);
        System.out.println(args[1]);
        System.out.println(args[2]);
        String responseMsg=connectSocket(args[0],Integer.parseInt(args[1]),args[2]);
        System.out.println("responseMsg:"+responseMsg);
    }
}  