package com.model.test;

import java.io.*;
import java.util.ArrayList;

import static java.lang.System.*;

public class Test{
   public static void main(String args[])throws IOException{
      byte[] bytes1="我".getBytes();
      out.println(bytes1.length);
      DataInputStream in = new DataInputStream(new FileInputStream("E:\\httpsocket\\src\\com\\model\\test\\test.txt"));
      DataOutputStream out = new DataOutputStream(new  FileOutputStream("E:\\httpsocket\\src\\com\\model\\test\\test1.txt"));
      BufferedReader d  = new BufferedReader(new InputStreamReader(in));

      StringBuffer strb=new StringBuffer();
      StringBuilder sb = new StringBuilder();

      byte[] bytes=new byte[1024];
      byte[] allbytes=new byte[0];
      ArrayList arrayList=new ArrayList<byte[]>();
      int len=0;
      int num=0;
      int size=0;
      BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(in));
      String lineStr=null;
      while((lineStr=bufferedReader.readLine())!=null){
         strb.append(lineStr);
      }
      System.out.println(strb.toString());
//      while ((len = in.read(bytes)) != -1) {
//         //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
//         strb.append(new String(bytes, 0, len));
//         sb.append(new String(bytes, 0, len,"UTF-8"));
//      }
//      System.out.println(strb.toString());
//      System.out.println(sb.toString());

//      while(true){
//         len=in.read(bytes,0,bytes.length);
//         System.out.println("长度:"+len);
////         System.out.println("String(bytes):"+new String(bytes));
//         if(len>0){
//            if(num==0){
//               System.out.println("第一次进入");
//               if(len==5){
//                  allbytes=byteMergerAll(bytes);
//               }else{
//                  byte[] bytes1=subBytes(bytes,0,len);
//                  allbytes=byteMergerAll(bytes1);
//               }
////               System.out.println("allbytes:"+new String(allbytes));
//            } else {
//               System.out.println("第2次进入");
//               if (len==5){
//                  allbytes=byteMergerAll(allbytes,bytes);
//               }else{
//                  byte[] bytes1=subBytes(bytes,0,len);
//                  allbytes=byteMergerAll(allbytes,bytes1);
//               }
//            }
//            size=size+len;
//            num++;
//         }else{
//            System.out.println("allbytes.size:"+size);
//            System.out.println("allbytes.length:"+allbytes.length);
//            break;
//         }
//      }
//      System.out.println("allbytes.length:"+allbytes.length);
//      strb.append(new String(allbytes),0,size);
//      System.out.println("最后:"+strb.toString());

//      String count;
//      while((count = d.readLine()) != null){
//          String u = count.toUpperCase();
//          System.out.println(u);
//          out.writeBytes(u + "  ,");
//      }
//      d.close();
//      out.close();
   }

   /**
    * 合并多个byte
    * @param values
    * @return
    */
   private static byte[] byteMergerAll(byte[]... values) {
      int length_byte = 0;
      for (int i = 0; i < values.length; i++) {
         length_byte += values[i].length;
      }
      byte[] all_byte = new byte[length_byte];
      int countLength = 0;
      for (int i = 0; i < values.length; i++) {
         byte[] b = values[i];
         System.arraycopy(b, 0, all_byte, countLength, b.length);
         countLength += b.length;
      }
      return all_byte;
   }
   public static byte[] subBytes(byte[] src, int begin, int count) {
      byte[] bs = new byte[count];
      System.arraycopy(src, begin, bs, 0, count);
      return bs;
   }
}