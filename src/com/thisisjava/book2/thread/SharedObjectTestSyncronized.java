package com.thisisjava.book2.thread;

/*
SharedObjectTest의 문제를 syncronized 메소드로 해결해보자
- synchronized
  > 임계구역을 지정해주는 메소드.
  > 이 메소드가 실행되는 동안 다른 스레드는 all stop.
  > 일반 메소드는 실행이 가능하다.
  > 이 블록 내부의 코드는 한번에 한 스레드만 사용할 수 있다.

- 먼저 user3가 100을 내놓고 2초후에 user4가 50을 내놓는다
- user3가 먼저 실행되는 일은 이제 일어나지 않는다.
 */

// 공유 객체
class CalculatorSync {
    private int memory;

    public int getMemory() {
        return memory;
    }
    public synchronized void setMemory(int memory) {
        this.memory = memory;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}

        System.out.println(Thread.currentThread().getName() + ":" + this.memory);
    }
}

class User3 extends Thread {
    private CalculatorSync calculator;

    public void setCalculator(CalculatorSync calculator){
        this.calculator = calculator;
        this.setName("user3"); // 스레드에 이름 붙이기
    }

    @Override
    public void run() {
        calculator.setMemory(100); // 계산기에 100을 저장
    }
}

class User4 extends Thread {
    private CalculatorSync calculator;

    public void setCalculator(CalculatorSync calculator){
        this.calculator = calculator;
        this.setName("user4"); // 스레드에 이름 붙이기
    }

    @Override
    public void run() {
        calculator.setMemory(50); // 계산기에 50을 저장
    }
}

public class SharedObjectTestSyncronized {
    public static void main(String[] args) {
        CalculatorSync calculator = new CalculatorSync();

        //User1 스레드 생성
        User3 user3 = new User3();
        user3.setCalculator(calculator);
        user3.start();

        //User2 스레드 생성
        User4 user4 = new User4();
        user4.setCalculator(calculator);
        user4.start();
    }
}
