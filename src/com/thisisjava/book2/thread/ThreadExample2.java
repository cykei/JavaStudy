package com.thisisjava.book2.thread;

/*
 beep음을 발생시키면서 동시에 프린팅 하기
 - 단, 원자적으로 이뤄지는 일은 아니다.
 - 그저 시간을 적절히 맞췄을 뿐.
 */

import java.awt.*;

class BeepTask implements Runnable {
    @Override
    public void run() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for (int i=0; i<5; i++) {
            toolkit.beep();
            try { Thread.sleep(500); } catch(Exception e) {}
        }
    }
}

public class ThreadExample2 {
    public static void main(String[]args) {
        Runnable beepTask = new BeepTask();
        Thread thread = new Thread(beepTask);
        thread.start();

        for (int i=0; i<5; i++){
            System.out.println("띵");
            try { Thread.sleep(500); } catch(Exception e) {}
        }
    }
}
