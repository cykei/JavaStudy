package com.thisisjava.book2.stream;

/*
Stream
- 자바 8부터 추가
- 컬렉션의 저장요소를 하나씩 참조해서 람다식으로 처리할 수 있도록 해주는 반복자

 */
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class IteratorVsStreamExample {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("홍길동", "신용권", "감자바");

        // Iterator 이용
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String name = iterator.next();
            System.out.println(name);
        }

        System.out.println();

        // Stream 이용
        Stream<String> stringStream = list.stream();
        stringStream.forEach( name -> System.out.println(name));
    }
}
