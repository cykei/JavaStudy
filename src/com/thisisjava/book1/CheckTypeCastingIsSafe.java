package com.thisisjava.book1;

public class CheckTypeCastingIsSafe {
    public static void main(String[]args) {
        int i= 128;

        if(i<Byte.MIN_VALUE || i>Byte.MAX_VALUE) {
           System.out.println("byte 타입으로 변환할 수 없습니다.");
        } else {
            byte b = (byte)i;
            System.out.println(b);
        }
    }
}
