package com.thisisjava.book2;

/* 메인 스레드만 사용하는 경우 */

// Thread.sleep()을 왜 try catch 내부에서 사용하는 걸까?

import java.awt.*;

public class ThreadExample1 {
    public static void main(String [] args){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for (int i=0; i<5; i++){
            toolkit.beep();
            try {
                Thread.sleep(500);
            } catch (Exception e) { }
        }

        for (int i=0; i<5; i++){
            System.out.println("띵");
            try {
                Thread.sleep(500);
            } catch (Exception e) { }
        }
    }
}
