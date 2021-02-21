package com.model.test;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 10068);
            //得到一个输出流，用于向服务器发送数据
            OutputStream outputStream = socket.getOutputStream();
            //将写入的字符编码成字节后写入一个字节流
            OutputStreamWriter writer=new OutputStreamWriter(outputStream,"UTF-8");
            System.out.println("请输入数据：");
            Scanner sc = new Scanner(System.in);
            String data = sc.nextLine();
            writer.write(data);
            writer.flush();//刷新缓冲
            socket.shutdownOutput();//只关闭输出流而不关闭连接
            //获取服务器端的响应数据
            //得到一个输入流，用于接收服务器响应的数据
            InputStream inputStream = socket.getInputStream();
            //将一个字节流中的字节解码成字符
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            //为输入流添加缓冲
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String info = null;
            System.out.println("客户端IP地址:"+socket.getInetAddress().getHostAddress());
            //输出服务器端响应数据
            while ((info = bufferedReader.readLine()) != null) {
                System.out.println("客户端接收：" + info);
            }
            //关闭资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            writer.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}