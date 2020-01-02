package cn.thislx.lambda.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 性能分析
 *
 * @author lixiang
 * @version V1.0
 * @date 2020/1/2 15:40
 **/
public class TestStreamAPI05 {
    public static void main(String[] args) {
        collectFun1();
    }

    public static void collectFun() {
        List<Integer> listOfIntegers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            listOfIntegers.add(i);
        }
//        List<Integer> parallelStorage = Collections.synchronizedList(new ArrayList<>());
        List<Integer> parallelStorage = new ArrayList<>();

        listOfIntegers.stream().filter(i -> i % 2 == 0).forEach(e -> System.out.print(e + " "));

        listOfIntegers
                .parallelStream()
                .filter(i -> i % 2 == 0)
                .forEach(i -> parallelStorage.add(i));
        System.out.println();

        parallelStorage
                .stream()
                .forEachOrdered(e -> System.out.print(e + " "));

//        System.out.println();
//        System.out.println("Sleep 5 sec");
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        parallelStorage
//                .stream()
//                .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println();
    }

    public static void collectFun1() {
        List<Integer> listOfIntegers =
                new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            listOfIntegers.add(i);
        }

        List<Integer> parallelStorage = listOfIntegers
                .parallelStream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toList());


        System.out.println();

        parallelStorage
                .stream()
                .forEachOrdered(e -> System.out.print(e + " "));

        System.out.println();
        System.out.println("Sleep 5 sec");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        parallelStorage
                .stream()
                .forEachOrdered(e -> System.out.print(e + " "));

        System.out.println();
    }
}
