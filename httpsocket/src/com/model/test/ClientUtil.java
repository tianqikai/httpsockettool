package com.model.test;



import java.io.*;
import java.net.Socket;

public class ClientUtil {

   public static String connectSocket(String ip ,int port,String message){
      Socket socket=null;
      String respMsg="";
      try {
         socket=new Socket(ip, port);
         //构建IO
         InputStream is = socket.getInputStream();
         OutputStream os = socket.getOutputStream();
         //发送请求信息
         sendResquestMsg(os,message);
         //获取返回信息
         getReturnMsg(is);
      } catch (IOException e) {
         e.printStackTrace();
      }finally {

      }
      return null;
   }

   public static void sendResquestMsg(OutputStream os,String message) throws IOException {
      BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os));
      bufferedWriter.write(message);
   }
   public static String getReturnMsg(InputStream is) throws IOException {
//      StringBuilder resp=null;
//
//      BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is));
//      while(bufferedReader.read()!=-1){
//         resp.append()
//      }
      return null;
   }
   public static void main(String[] args) {
      StringBuffer respMessage = null;
      BufferedWriter bw=null;
      InputStream is=null;
      OutputStream os=null;
      Socket s=null;
      try {
         s = new Socket("127.0.0.1", 8800);

         //构建IO
          is = s.getInputStream();
          os = s.getOutputStream();

         bw = new BufferedWriter(new OutputStreamWriter(os));
         //向服务器端发送一条消息
         bw.write("测试客户端和服务器通信，发送客户端消息！\n");
         bw.flush();
         //读取服务器返回的消息
         respMessage = new StringBuffer();
         byte[] resp = new byte[1024];
         int len = 0;
         while ((len = is.read(resp)) != -1) {
            System.out.println("len:"+len);
            respMessage.append(new String(resp, "UTF-8"), 0, len);
         }
         System.out.println("服务器返回信息：" + respMessage.toString());
      } catch (IOException e) {
         System.out.println("服务器返回信息111：" + respMessage.toString());

         e.printStackTrace();
      } finally {
         try {
//            is.close();
            os.close();
            bw.close();
            s.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
         System.out.println("服务器返回信息222：" + respMessage.toString());
      }
   }
}