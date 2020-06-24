package com.thisisjava.book2.lambda;

/*
JAVA에서 제공해주는 Lambda용 인터페이스들
- Consumer : 매개값 O, 리턴값 X
- Supplier : 매개값 X, 리턴값 O
- Function : 매개값 O, 리턴값 O (타입변환)
- Operator : 매개값 O, 리턴값 O (연산)
- Predicate : 매개값 O, 리턴값 boolean
 */

import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class LambdaInterfacePackageExample {

    /////////////Function 보조///////////////////////
    private static List<Student> list = Arrays.asList(
            new Student("홍길동",90, 96),
            new Student("신용권",95, 93)
    );
    private static void printString(Function<Student, String> function ) {
        for (Student student : list) {
            System.out.println(function.apply(student) + " ");
        }
        System.out.println();
    }

    /////////////Operator 보조///////////////////////
    private static int[] scores = {92, 95, 32};
    public static int maxOrMin(IntBinaryOperator operator){
        int result = scores[0];
        for (int score : scores ) {
            result = operator.applyAsInt(result, score);
        }

        return result;
    }

    /////////////Predicate 보조///////////////////////
    private static List<Student> mathScores = Arrays.asList(
            new Student("홍길동", 90, "남자"),
            new Student("김순희", 95, "여자"),
            new Student("이갑자", 97, "남자"),
            new Student("갑순이", 85, "여자")
    );
    public static double mathAvg(Predicate<Student> predicate) {
        int count = 0, sum= 0;
        for (Student student : mathScores) {
            if (predicate.test(student)) {
                count++;
                sum += student.getMathScore();
            }
        }
        return (double) sum/count;
    }

    public static void main(String[] args) {

        ////////////////// CONSUMER ///////////////////
        Consumer<String> consumer = t -> System.out.println("Consumer 예제: "+t+" 8");
        consumer.accept("java");

        BiConsumer<String, String> biConsumer = (t, u) -> System.out.println(t + u);
        biConsumer.accept("BiConsumer 예제: java"," 8");

        ////////////////// SUPPLIER ///////////////////
        Supplier<Integer> supplier = () -> {
            int num = (int) (Math.random() * 6)+1;
            return num;
        };
        System.out.println("Supplier 예제: " +supplier.get());

        ////////////////// FUNCTION ///////////////////
        System.out.println("Function 예제: ");
        printString ( t-> t.getName() );

        ////////////////// OPERATOR ///////////////////
        int max = maxOrMin(
            (a,b) -> {
                if(a>=b) return a;
                else return b;
            }
        );
        System.out.println("Operator 예제: 최대값: " + max);

        ////////////////// PREDICATE ///////////////////
        double maleAvg = mathAvg(t->t.getSex().equals("남자"));
        System.out.println("Predicate 예제: 남자 수학 평균 점수 : "+maleAvg);

    }
}

class Student{
    private String name;
    private int englishScore;
    private int mathScore;
    private String sex;

    public Student(String name, int englishScore, int mathScore) {
        this.name = name;
        this.englishScore = englishScore;
        this.mathScore = mathScore;
    }

    public Student(String name, int mathScore, String sex) {
        this.name = name;
        this.mathScore = mathScore;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public int getEnglishScore() {
        return englishScore;
    }

    public int getMathScore() {
        return mathScore;
    }

    public String getSex() {
        return sex;
    }
}
