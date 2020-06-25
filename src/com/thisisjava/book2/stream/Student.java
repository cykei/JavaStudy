package com.thisisjava.book2.stream;

public class Student {

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public Sex getSex() {
        return sex;
    }

    public enum Sex {MALE, FEMALE};
    private String name;
    private int score;
    private Sex sex;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
    public Student(String name, int score, Sex sex) {
        this(name, score);
        this.sex = sex;
    }


}
