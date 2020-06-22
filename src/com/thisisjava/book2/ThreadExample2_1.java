package com.thisisjava.book2;

/* Runnable 익명 객체를 이용 해서 beep음을 발생시키면서 동시에 프린팅 하기 */

import java.awt.*;

public class ThreadExample2_1 {
    public static void main(String[]args){
        Thread beepThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                for (int i=0; i<5; i++){
                    toolkit.beep();
                    try{ Thread.sleep(500); }catch (Exception e) {}
                }
            }
        });

        beepThread.start();

        for (int i=0; i<5; i++){
            System.out.println("띵");
            try{ Thread.sleep(500); }catch (Exception e) {}
        }

    }
}
