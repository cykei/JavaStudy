package com.thisisjava.book2.stream;

/*
Stream 클래스의 병렬처리 Parallel
- 자동으로 작업을 서브작업으로 나누고 분리된 작업들은 스레드에서 병렬적으로 처리한다.
- 여러개의 스레드가 부분적으로 일을 하고 자동으로 결과를 취합해서 최종결과물을 생성한다.
- 아래 예제를 보면 병렬처리 스트림은 main 스레드를 포함해서 ForkJoinPool(스레드풀)의 작업스레드들이 병렬적으로 요소를 처리하는 것을 볼 수 있다.
 */

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ParallelExample {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(
                "홍길동 ", "신용권", "김자바", "람다식", "박병렬");

        // 순차 처리
        Stream<String> stream = list.stream();
        stream.forEach(ParallelExample::print);

        System.out.println();

        // 병렬 처리
        Stream<String> parallelStream = list.parallelStream();
        parallelStream.forEach(ParallelExample :: print);
    }

    public static void print(String str) {
        System.out.println(str+" : "+Thread.currentThread().getName());
    }
}
