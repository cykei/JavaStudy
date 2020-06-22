package com.thisisjava.book1;
enum MyState{
    HOME,
    COMPANY,
    LUNCH,
    MEETING,
    OVERTIME,
    DAY_OFF
}
public class EnumTest {

    static int sum1(int ... values){
        int sum=0;
        for (int v : values){
            sum+=v;
        }
        return sum;
    }
    public static void main(String[] args){
        MyState ms = MyState.COMPANY;

        System.out.println("현재 내 상태는 : " + ms.name());
        System.out.println("현재 내 상태는 : " + ms.ordinal());
        System.out.println(sum1(3,4,54,6,6,6,7,7,78,8));

    }
}
