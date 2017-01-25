package com.isi.appmon.utils;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by samurdiw on 1/9/2017.
 */
public class TCPClientSample {
    public static void main(String argv[]) throws Exception
    {
        Socket socket = null;
        OutputStreamWriter osw;
//        String str = "Hello World";
        try {
            socket = new Socket("127.0.0.1", 8282);
            osw =new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
//            osw.write(str, 0, str.length());
            int count=0;
            String line="";
            while (true){
                count++;
                line="Hello World:"+count+"\n";
                System.out.println("\n writing:"+line);
                osw.write(line, 0, line.length());
                osw.flush();
                if(count==5){
                    Thread.sleep(10000);
                }else{
                    Thread.sleep(1000);
                }
                if(count>6){
                    break;
                }

            }
        } catch (IOException e) {
            System.err.print(e);
        } catch (Exception e) {
            System.err.print(e);
        } finally {
            socket.close();
        }
    }
}
