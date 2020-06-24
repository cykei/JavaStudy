package com.thisisjava.book2.thread;

/*
우선순위는 1이 제일 낮고, 10이 제일 높다.
대체적으로 "thread10"의 우선순위가 높아서 일찍 끝나긴 하는데..
항상 그런건 아니다.
 */

class CalcThread extends Thread {
    public CalcThread(String name) {
        setName(name);
    }

    public void run() {
        for (int i=0; i<200000000; i++) {}

        System.out.println(getName());
    }
}
public class PriorityThreadTest {
    public static void main(String[] args) {
        for (int i=0; i<=10; i++) {
            Thread calcThread = new CalcThread("thread" + i);
            if(i != 10) {
                calcThread.setPriority(Thread.MIN_PRIORITY);
            } else {
                calcThread.setPriority(Thread.MAX_PRIORITY);
            }

            calcThread.start();
        }
    }
}
