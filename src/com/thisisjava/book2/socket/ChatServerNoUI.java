package com.thisisjava.book2.socket;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServerNoUI {
    ExecutorService executorService;
    ServerSocket serverSocket;
    List<ChatClientNoUI> connections = new Vector<ChatClientNoUI>();
    void startServer(){
        // 스레드 풀에서 스레드를 하나 데려옴
        executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );

        // 서버소켓 생성 및 포트바인딩
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost",5001));
        } catch (Exception e) {
            if(!serverSocket.isClosed()){
                stopServer();
            }
            return;
        }

        // 연결 수락
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("서버 시작");
                while(true) {
                    try {
                        Socket socket = serverSocket.accept();
                        String message = "연결 수락 : " + socket.getRemoteSocketAddress() + " : " + Thread.currentThread().getName();

                        ChatClientNoUI client = new ChatClientNoUI(socket);
                        connections.add(client);
                    }catch (Exception e) {
                        if(!serverSocket.isClosed()){
                            stopServer();
                        }
                        break;
                    }
                }
            }
        };
        executorService.submit(runnable);

    }
    void stopServer(){
        try {
            // 모든 소켓 닫기
            Iterator<ChatClientNoUI> iterator = connections.iterator();
            while(iterator.hasNext()){
                ChatClientNoUI client = iterator.next();
                client.socket.close();
                iterator.remove();
            }

            // 서버 소켓 닫기
            if(serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }

            // 스레드 종료
            if(executorService != null && !executorService.isShutdown()) {
                executorService.shutdown();
            }

            System.out.println("서버 종료");
        } catch (Exception e) {

        }
    }
}
