package com.thisisjava.book2.thread;

/*
스레드의 작업 처리 결과를 받아 보자.
작업 요청 순이 아니라, 작업 완료된 순으로 결과를 받게된다.
 */

import java.util.concurrent.*;

public class CompletionServiceExample extends Thread{
    public static void main(String[] args) {
        // 스레드풀을 만든다.
        ExecutorService executorService =
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        // 작업완료 순으로 통보받을 객체
        CompletionService<Integer> completionService =
                new ExecutorCompletionService<Integer>(executorService);

        System.out.println("[작업 처리 요청]");
        for (int i=0; i<3; i++) {
            // 스레드 풀에 작업처리 요청. 작업은 Callable을 이용해서 만듦
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int sum =0;
                    for (int i=1; i<=10; i++) {
                        sum+=i;
                    }
                    return sum;
                }
            });
        }

        System.out.println("[처리 완료된 작업 확인]");

        // 작업을 완료했다는 것을 확인하기위해 리턴값이 필요없는 스레드를 만들어서 또 작업을 하는군.
        // 작업 완료를 하기위한 작업 프로세스라니...
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Future<Integer> future = completionService.take();
                        int value = future.get();
                        System.out.println("[처리 결과] " + value);
                    } catch (Exception e) {
                        break;
                    }
                }
            }
        });

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {}
        executorService.shutdownNow();
    }
}
