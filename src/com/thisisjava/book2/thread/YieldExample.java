package com.thisisjava.book2.thread;

/*
yield()를 이용하면 다른 대기중인 스레드에게 cpu를 양보할 수 있다.
 */

class ThreadA extends  Thread {
    boolean work = true;
    boolean stop = false;

    public void run() {
        while(!stop) {
            if(work) {
                System.out.println("ThreadA 작업중");
            } else {
                Thread.yield();
                // 야, 나 무한 루프 돌면서 일감 기다려야 되니까 다른 애들 아무나 cpu 써!
            }
        }
        System.out.println("ThreadA 종료");
    }
}

class ThreadB extends  Thread {
    boolean work = true;
    boolean stop = false;

    public void run() {
        while(!stop) {
            if(work) {
                System.out.println("ThreadB 작업중");
            } else {
                Thread.yield();
                // 야, 나 무한 루프 돌면서 일감 기다려야 되니까 다른 애들 아무나 cpu 써!
            }
        }
        System.out.println("ThreadB 종료");
    }
}

public class YieldExample {
    public static void main (String[] args) {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();

        threadA.start();
        threadB.start();

        try { Thread.sleep(3000); } catch (Exception e) {}
        System.out.println("[1] 3초지남!");
        threadA.work = false;

        try { Thread.sleep(3000); } catch (Exception e) {}
        System.out.println("[2] 3초지남!");
        threadA.work = true;

        try { Thread.sleep(3000); } catch (Exception e) {}
        System.out.println("[3] 3초지남!");
        threadA.stop = true;
        threadB.stop = true;
    }
}
