package com.thisisjava.book2.thread;

/*
스레드의 상태를 출력해보자
- 자바 5부터 Thread클래스에 getState()가 추가됨
- new(생성) -> runnable(실행중) -> timed_waiting(시간 기다림) -> runnable(실행중) -> terminated(종료)
 */

class StatePrintThread extends Thread{
    private Thread targetThread;

    public StatePrintThread(Thread targetThread) {
        this.targetThread = targetThread;
    }

    public void run() {
        while (true) {
            Thread.State state = targetThread.getState();
            System.out.println("타겟 스레드 상태: " + state);

            if (state == Thread.State.NEW) {
                targetThread.start();
            }
            if (state == State.TERMINATED) {
                break;
            }
            try {
                // 0.5초간 일시 정지
                Thread.sleep(500);
            } catch (Exception e) {}
        }
    }
}

class TargetThread extends Thread {
    public void run() {
        for (long i=0; i<1000000000; i++) {}

        try {
            // 1.5초간 일시 정지
            Thread.sleep(1500);
        } catch (Exception e) {}

        for (long i=0; i<1000000000; i++) {}
    }
}

public class ThreadStateExample {
    public static void main(String[] args) {
        StatePrintThread statePrintThread = new StatePrintThread(new TargetThread());
        statePrintThread.start();
    }
}
