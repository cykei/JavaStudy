package com.thisisjava.book2.stream;

import java.util.ArrayList;
import java.util.List;

public class MaleStudent {
    private List<Student> list;

    public MaleStudent(){
        list = new ArrayList<>();
        System.out.println("[" + Thread.currentThread().getName() + "] MaleStudent()");
    }

    public void accumulate(Student student) {
        list.add(student);
        System.out.println("[" +Thread.currentThread().getName() +"] accumulate()");
    }

    public void combine(MaleStudent other) {
        // 병렬처리시에만 호출된다.
        list.addAll(other.getList());
        System.out.println("[" +Thread.currentThread().getName() +"] combine()");
    }

    public List<Student> getList() {
        return list;
    }
}
