package com.isi.appmon.agent.basic.sender;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by samurdiw on 1/24/2017.
 */
public class TCPSenderBasic {
    private Socket clientSocket;
//    private ObjectOutputStream outToServer;
    private OutputStream serverOut;
//    private PrintWriter serverOut;
//    private static Queue outMsgQueue;
    private boolean isConnected;
    private String host;
    private int port;
    private Charset charset=Charset.forName("UTF-8");
    public TCPSenderBasic(String host,int port){
        this.host=host;
        this.port=port;
        init(host,port);
    }

    private void init(){
        init(host,port);
    }

    private void init(String ip,int port){
        try {
            clientSocket = new Socket(ip,port);
//            serverOut = new ObjectOutputStream(clientSocket.getOutputStream());
            serverOut = clientSocket.getOutputStream();
//            serverOut=new PrintWriter(clientSocket.getOutputStream());
//        outMsgQueue=new LinkedBlockingQueue();
            isConnected=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void addToSendQueue(String msg){
//        outMsgQueue.add(msg);
//    }

    public boolean send(String msg){
        try {
            if(isConnected){
//                serverOut.write(new String((msg+"\n").getBytes(),"UTF-8"));
                serverOut.write((msg+"\n").getBytes("UTF-8"));
                serverOut.flush();
                return true;
            }else{
                init();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
