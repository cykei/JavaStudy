package com.thisisjava.book2.thread;

/* Thread 하위 클래스로부터 생성 */

import java.awt.*;

class BeepThread extends Thread {
    @Override
    public void run() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for (int i=0; i<5; i++) {
            toolkit.beep();
            try { Thread.sleep(500); } catch (Exception e){}
        }
    }
}

public class ThreadExample3 {
    public static void main(String[]args) {
        Thread beepThread = new BeepThread();
        beepThread.start();

        for (int i=0; i<5; i++) {
            System.out.println("띵");
            try { Thread.sleep(500); } catch (Exception e){}
        }
    }
}
