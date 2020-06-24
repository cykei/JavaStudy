package com.thisisjava.book2.lambda;

/*

람다식?
- 함수형 프로그래밍
- java 8 부터 도입
- (매개변수) -> {실행코드};
- 함수(x) 인터페이스의 익명 구현 객체(o)

 */

@FunctionalInterface // 두 개 이상의 추상메소드가 선언되면 컴파일 오류를 발생시킨다.
interface MyFunctionalInterface {
    public void method();
    //public void otherMehthod(); // 오류가 난다.
}

@FunctionalInterface
interface HasReturnRambda {
    public int method(int x, int y);
}

public class LambdaBasicExample {
    public static void main(String[] args) {

        // 아주 기초적인 람다식. 파라미터도 없고, 리턴값도 없다.
        MyFunctionalInterface fi;

        fi = () -> {
            String str = "method call 1";
            System.out.println(str);
        };
        fi.method();

        fi = () -> {
            System.out.println("method call 2");
        };
        fi.method();

        // 리턴값도 있고 파라미터도 있는 람다식
        HasReturnRambda hrr;
        hrr = (x, y) -> x+y;
        hrr = (x, y) -> { return x+y; };

        int a=3,b=5;
        int sum = hrr.method(a,b);
    }
}
