package com.thisisjava.book2.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ChatClientNoUI {
    Socket socket;

    ChatClientNoUI(Socket socket) {
        this.socket = socket;
        receive();
    }

    void startClient() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress("localhost", 5001));
                    System.out.println("연결 완료: " + socket.getRemoteSocketAddress());
                } catch (Exception e) {
                    System.out.println("서버 통신 안됨");
                    if(!socket.isClosed()) {
                        stopClient();
                    }
                    return;
                }
                // 서버에서 보낸 데이터 받기
                receive();
            }
        };
        thread.start();
    }

    void stopClient() {
        try {
            System.out.println("연결 끊음");
            if(socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {

        }
    }

    // 데이터 받기
    void receive() {
        while(true) {
            try {
                byte[] bytes = new byte[100];
                InputStream inputStream = socket.getInputStream();
                int readByCount = inputStream.read(bytes);

                // 서버가 정상적으로 Socket의 close()를 호출했을 경우
                if(readByCount == -1) {
                    throw new IOException();
                }

                String data = new String(bytes, 0, readByCount, "UTF-8");
                System.out.println("받기 완료");
            } catch (Exception e) {
                System.out.println("서버 통신 안됨");
                stopClient();
                break;
            }
        }
    }

    // 데이터 전송
    void send(String data) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    byte[] bytes = data.getBytes("UTF-8");
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write(bytes);
                    outputStream.flush();
                    System.out.println("보내기 완료");
                } catch (Exception e) {
                    System.out.println("서버 통신 안됨");
                    stopClient();
                }
            }
        };
        thread.start();
    }
}
