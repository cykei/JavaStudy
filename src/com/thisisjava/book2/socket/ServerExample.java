package com.thisisjava.book2.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerExample {
    public static void main(String[] args ){
        ServerSocket serverSocket = null;

        try{
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost",5001));
            while(true) {
                System.out.println("연결을 기다린다");
                Socket socket = serverSocket.accept();
                InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
                System.out.println("연결 수락함 " + isa.getHostName());

                byte[] bytes = null;
                String message = null;

                InputStream is = socket.getInputStream();
                bytes = new byte[100];
                int readByCount = is.read(bytes);
                message = new String(bytes, 0, readByCount, "UTF-8");
                System.out.println("데이터 받기 성공: " + message);

                OutputStream os = socket.getOutputStream();
                message = "hello Client!";
                bytes = message.getBytes("UTF-8");
                os.write(bytes);
                os.flush();
                System.out.println("데이터 보내기 성공"); // 성공을 했다는 걸 어떻게 알아? 제대로 전송이 안될수도 있는거 아닌가?

                is.close();
                os.close();
                socket.close();
            }
        } catch (Exception e) {}

        if(!serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (Exception e) {}
        }
    }
}
