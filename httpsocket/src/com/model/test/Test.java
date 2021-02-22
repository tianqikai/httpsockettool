package com.model.test;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

import static java.lang.System.*;

public class Test{
   public static void main(String args[])throws IOException{




//      byte[] bytes1="我".getBytes();
//      out.println(bytes1.length);
//      DataInputStream in = new DataInputStream(new FileInputStream("E:\\httpsocket\\src\\com\\model\\test\\test.txt"));
//      DataOutputStream out = new DataOutputStream(new  FileOutputStream("E:\\httpsocket\\src\\com\\model\\test\\test1.txt"));
//      BufferedReader d  = new BufferedReader(new InputStreamReader(in));
//
//      StringBuffer strb=new StringBuffer();
//      StringBuilder sb = new StringBuilder();
//
//      byte[] bytes=new byte[1024];
//      byte[] allbytes=new byte[0];
//      ArrayList arrayList=new ArrayList<byte[]>();
//      int len=0;
//      int num=0;
//      int size=0;
//      BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(in));
//      String lineStr=null;
//      while((lineStr=bufferedReader.readLine())!=null){
//         strb.append(lineStr);
//      }
//      System.out.println(strb.toString());
      String test=null;
      String resp= (String) Optional.ofNullable(test).map(item->{
         out.println("test1:"+item);
         return null;
      }).orElse("11111111111");
      out.println("resp1:"+resp);
      out.println("test1:"+test);
      test=resp;
      resp= (String) Optional.ofNullable(test).map(item->{out.println("test2:"+item);
         return item;
      }).orElse("222222");
      out.println("test2:"+test);
      out.println("resp2:"+resp);
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