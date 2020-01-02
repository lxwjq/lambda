package cn.thislx.lambda.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * parallelStream的注意事项：线程安全问题导致的数据错误
 * 解决办法：
 * 1、Collections.synchronizedList(new ArrayList<>())
 * 2、最后调用collect(Collectors.tolist())，这种收集起来所有元素到新集合是线程安全的。
 *
 * @author lixiang
 * @version V1.0
 * @date 2020/1/2 15:40
 **/
public class TestStreamAPI05 {

    public static void main(String[] args) {
        collectFun1();
    }

    /**
     * parallelStream 线程安全问题导致的数据不一致
     */
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

    /**
     * 解决办法：最后调用collect(Collectors.tolist())，这种收集起来所有元素到新集合是线程安全的。
     */
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
