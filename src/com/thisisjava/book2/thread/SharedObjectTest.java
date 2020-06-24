package com.thisisjava.book2.thread;

/*
여러 사람이 하나의 계산기를 쓰는 상황
- user1 은 100을 저장했는데도 50이 저장 됐다고 나온다.
  > user1이 공유변수 memroy에 100을 넣고 2초간 정지한 사이에 user2가 50을 넣는다.
  > memory변수는 공유변수이기 때문에 user1은 바뀐 50이라는 값을 출력하게된다.

- 심지어 user2가 먼저 나올때도 있다.
*/

// 공유 객체
class Calculator {
    private int memory;

    public int getMemory() {
        return memory;
    }
    public void setMemory(int memory) {
        this.memory = memory;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}

        System.out.println(Thread.currentThread().getName() + ":" + this.memory);
    }
}

class User1 extends Thread {
    private Calculator calculator;

    public void setCalculator(Calculator calculator){
        this.calculator = calculator;
        this.setName("user1"); // 스레드에 이름 붙이기
    }

    @Override
    public void run() {
        calculator.setMemory(100); // 계산기에 100을 저장
    }
}

class User2 extends Thread {
    private Calculator calculator;

    public void setCalculator(Calculator calculator){
        this.calculator = calculator;
        this.setName("user2"); // 스레드에 이름 붙이기
    }

    @Override
    public void run() {
        calculator.setMemory(50); // 계산기에 50을 저장
    }
}


public class SharedObjectTest {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        //User1 스레드 생성
        User1 user1 = new User1();
        user1.setCalculator(calculator);
        user1.start();

        //User2 스레드 생성
        User2 user2 = new User2();
        user2.setCalculator(calculator);
        user2.start();
    }
}
