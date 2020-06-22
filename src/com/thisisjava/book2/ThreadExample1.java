package com.thisisjava.book2;

/*
메인 스레드만 사용하는 경우
- 소리가 다 울린다음에 글씨가 나온다.
*/

// Thread.sleep()을 왜 try catch 내부에서 사용하는 걸까?
//  --> throws가 붙어있기 때문 (근본적으로는 Interrupted Exception이 날 수 있기 때문)

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
