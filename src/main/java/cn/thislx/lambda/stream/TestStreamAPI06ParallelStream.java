package cn.thislx.lambda.stream;

import java.util.stream.LongStream;

/**
 * @author lixiang
 * @version V1.0
 * @date 2020/1/2 14:29
 **/
public class TestStreamAPI06ParallelStream {

    public static void main(String[] args) {
        fn1();
        fn2();
    }

    // 并行流
    public static void fn1(){
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0, 1000)
                .parallel()
                .sum();
        long end = System.currentTimeMillis();
        System.out.println(sum + "=======================" + (end - start));
    }

    // 串行流
    public static void fn2(){
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0, 1000)
                .sequential()
                .sum();
        long end = System.currentTimeMillis();
        System.out.println(sum + "=======================" + (end - start));
    }
}