package cn.thislx.lambda.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 创建Stream
 *
 * @author lixiang
 * @version V1.0
 * @date 2020/1/2 13:38
 **/
public class TestStreamAPI01 {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        // 1.1第一种方式:通过Collection系列集合提供的Stream或paralleStream
        List<Object> list = new ArrayList<>();
        // 串行流,按顺序
        Stream<Object> streamList1 = list.stream();
        // 并行流,不按顺序
        Stream<Object> parallelStream = list.parallelStream();

        // 1.2第二种方式:通过Arrays中的静态方法获取数组流
        Employee[] employee = new Employee[10];
        Stream<Employee> streamArray = Arrays.stream(employee);

        // 1.3第三种方式:通过Stream类中的of()方法创建流
        Stream<String> stringStream = Stream.of("a", "b", "c");

        // 1.4创建无限流
        // 1.4.1第一种方式:迭代
        Stream.iterate(0, (x) -> (x + 1))
                .limit(20)
                .forEach(System.out::println);
        System.out.println("=================");
        // 1.4.2第二种方式:生成
        Stream.generate(() -> Math.random())
                .limit(10)
                .forEach(System.out::println);
    }

}
