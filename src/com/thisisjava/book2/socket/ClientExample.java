package com.thisisjava.book2.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientExample {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket();
            System.out.println("연결 요청");
            socket.connect(new InetSocketAddress("localhost",5001));
            System.out.println("연결 성공");

            byte[] bytes =null;
            String message =null;

            // 데이터 보내기
            OutputStream os = socket.getOutputStream();
            message = "hello server";
            bytes = message.getBytes("UTF-8");
            os.write(bytes);
            os.flush();
            System.out.println("데이터 보내기 성공!");

            // 데이터 받기
            InputStream is = socket.getInputStream();
            bytes = new byte[100];
            int readByteCount = is.read(bytes);
            message = new String(bytes, 0, readByteCount, "UTF-8");
            System.out.println("데이터 받기 성공: "+ message);

            os.close();
            is.close();

        } catch( Exception e ) {}

        if(!socket.isClosed()) {
            try {
                socket.close();
            } catch (Exception e) {}
        }
    }
}
