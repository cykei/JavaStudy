package com.thisisjava.book2.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
Iterator 보다 향상된 For문으로 조회하는게 훨씬 빠르긴하군...
객체수정이 있을땐 Iterator로 조회, 그냥 조회하기만 할거면 For문으로.
 */
public class SetCollectionLoopingTest {
    static void testIterator() {
        Set<String> set = new HashSet<>();
        set.add("감자");
        set.add("고구마");
        set.add("바나나");
        set.add("초코파이");
        set.add("넘쳐요");
        set.add("그대잔에");
        set.add("초코파이");
        set.add("따라주고");
        set.add("싶은데");

        int size = set.size();
        System.out.println("총 객체수" + size);

        long start = System.nanoTime();
        Iterator<String> iterator = set.iterator();
        while(iterator.hasNext()) {
            String element = iterator.next();
            System.out.println("[" + size-- + "] " + element);
        }
        long end = System.nanoTime();
        long distance = end-start;
        System.out.println("Test Iterator Loop : " + distance);
    }

    static void testSimpleForLoop() {
        Set<String> set = new HashSet<>();
        set.add("감자");
        set.add("고구마");
        set.add("바나나");
        set.add("초코파이");
        set.add("넘쳐요");
        set.add("그대잔에");
        set.add("초코파이");
        set.add("따라주고");
        set.add("싶은데");

        int size = set.size();
        System.out.println("총 객체수" + size);

        long start = System.nanoTime();

        for(String str: set) {
            System.out.println("[" + size-- + "] " + str);
        }

        long end = System.nanoTime();
        long distance = end-start;
        System.out.println("Test For Loop : " + distance);
    }
    public static void main(String[] args) {
        testIterator();
        testSimpleForLoop();
    }
}
