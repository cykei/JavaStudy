package com.thisisjava.book1;

/*  중첩 인터페이스 예제 - 버튼 만들기 */

import jdk.nashorn.internal.codegen.CompilerConstants;

class Button{

    OnClickListener listener;

    interface OnClickListener{
        void onClick();
    }

    void setOnClickListener(OnClickListener listener){
        this.listener = listener;
    }

    void touch() {
        listener.onClick();
    }
}

class CallListener implements Button.OnClickListener {
    @Override
    public void onClick() {
        System.out.println("전화를 겁니다.");
    }
}

class MessageListener implements Button.OnClickListener{
    @Override
    public void onClick() {
        System.out.println("메시지를 보냅니다.");
    }
}

public class NestedInterfaceTest {
    public static void main(String[]args) {
        Button btn = new Button();

        btn.setOnClickListener(new CallListener());
        btn.touch();

        btn.setOnClickListener(new MessageListener());
        btn.touch();

    }

}
