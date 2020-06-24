package com.thisisjava.book2.collection;

import java.util.NavigableSet;
import java.util.TreeSet;

public class TreeSetExample {
    public static void main(String[] args) {
        TreeSet<Integer> scores = new TreeSet<Integer>();
        scores.add(new Integer(87));
        scores.add(new Integer(32));
        scores.add(new Integer(33));
        scores.add(new Integer(37));
        scores.add(new Integer(38));
        scores.add(new Integer(11));

        Integer score = null;
        score = scores.first();
        System.out.println("가장 낮은 점수: "+ score);

        NavigableSet<Integer> descendingSet = scores.descendingSet();
        for (Integer s : descendingSet) {
            System.out.print(s+ " ");
        }

        System.out.println();

    }
}
