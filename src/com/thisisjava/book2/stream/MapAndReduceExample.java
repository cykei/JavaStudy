package com.thisisjava.book2.stream;

/*
Stream은 중간처리와 최종처리를 할 수 있다.
 */

import java.util.Arrays;
import java.util.List;

public class MapAndReduceExample {
    public static void main(String[] args) {
        List<Student> studentList = Arrays.asList(
                new Student("홍길동", 10),
                new Student("신용권", 20),
                new Student("신용권", 35)
        );

        double avg = studentList.stream() // 오리지날 스트림
                .mapToInt(Student::getScore) // 중간 처리
                .average() // 최종 처리

                .getAsDouble();

        System.out.println("평균 점수: "+avg);
    }
}

class Student{
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}